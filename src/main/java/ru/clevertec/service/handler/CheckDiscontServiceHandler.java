package ru.clevertec.service.handler;

import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.service.ProductService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CheckDiscontServiceHandler implements InvocationHandler {
    private static final String EMPTY_STRING = "";
    private final ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(CheckDiscontServiceHandler.class);

    public CheckDiscontServiceHandler(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("Worked - Handler!");

        Object invoke = method.invoke(productService, args);
        Gson parser = new Gson();

        if (method.getName().equals("service") || method.getName().equals("setDiscountTotal")
                || method.getName().equals("setTotalCostOfAllNon_discountItem")
                || method.getName().equals("setSumTotal") || method.getName().equals("setDiscontSum")
                || method.getName().equals("setFinalAmount")) {
            String arguments = EMPTY_STRING;

            if (args != null) {
                arguments = parser.toJson(args);
            }

            String result = EMPTY_STRING;

            if (invoke != null) {
                result = parser.toJson(invoke);
            }
            log.info(method.getName() + " - " + arguments);
            log.info(method.getName() + " - " + result);
        }
        return invoke;
    }
}
