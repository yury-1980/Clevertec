package ru.clevertec.service.proxy;

import org.springframework.stereotype.Component;
import ru.clevertec.model.Check;
import ru.clevertec.service.CheckProductServiceImpl;
import ru.clevertec.service.CheckProductService;
import ru.clevertec.service.handler.CheckProductServiceHandler;

import java.lang.reflect.Proxy;
import java.util.List;

public class CheckProductServiceProxy implements CheckProductService {
    private static CheckProductService checkProductService;

    {
        checkProductService = new CheckProductServiceImpl();
        ClassLoader productServiceClassLoader = checkProductService.getClass().getClassLoader();
        Class<?>[] productServiceInterfaces = checkProductService.getClass().getInterfaces();
        checkProductService =
                (CheckProductService) Proxy.newProxyInstance(productServiceClassLoader,
                        productServiceInterfaces,
                        new CheckProductServiceHandler(checkProductService));
    }

    @Override
    public List<Check> service() {
        return checkProductService.service();

    }

    @Override
    public void setDiscountTotal(String[] str) {
        checkProductService.setDiscountTotal(str);
    }

    @Override
    public void setTotalCostOfAllNon_discountItem(String[] str) {
        checkProductService.setTotalCostOfAllNon_discountItem(str);
    }

    @Override
    public void setSumTotal() {
        checkProductService.setSumTotal();
    }

    @Override
    public void setDiscontSum() {
        checkProductService.setDiscontSum();
    }

    @Override
    public void setFinalAmount() {
        checkProductService.setFinalAmount();
    }
}
