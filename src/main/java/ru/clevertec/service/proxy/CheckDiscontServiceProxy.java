package ru.clevertec.service.proxy;

import ru.clevertec.model.Check;
import ru.clevertec.service.CheckDiscontService;
import ru.clevertec.service.ProductService;
import ru.clevertec.service.handler.CheckDiscontServiceHandler;

import java.lang.reflect.Proxy;
import java.util.List;

public class CheckDiscontServiceProxy implements ProductService {
    private static ProductService productService;

    {
        productService = new CheckDiscontService();
        ClassLoader productServiceClassLoader = productService.getClass().getClassLoader();
        Class<?>[] productServiceInterfaces = productService.getClass().getInterfaces();
        productService =
                (ProductService) Proxy.newProxyInstance(productServiceClassLoader,
                        productServiceInterfaces,
                        new CheckDiscontServiceHandler(productService));
    }

    @Override
    public List<Check> service() {
        return productService.service();

    }

    @Override
    public void setDiscountTotal(String[] str) {
        productService.setDiscountTotal(str);
    }

    @Override
    public void setTotalCostOfAllNon_discountItem(String[] str) {
        productService.setTotalCostOfAllNon_discountItem(str);
    }

    @Override
    public void setSumTotal() {
        productService.setSumTotal();
    }

    @Override
    public void setDiscontSum() {
        productService.setDiscontSum();
    }

    @Override
    public void setFinalAmount() {
        productService.setFinalAmount();
    }
}
