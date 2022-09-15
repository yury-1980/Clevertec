package ru.clevertec.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.clevertec.configuration.SpringConfig;
import ru.clevertec.controller.impl.*;
import ru.clevertec.repository.DiscountRepoitory;
import ru.clevertec.repository.ProductReository;

import java.util.HashMap;
import java.util.Map;

public class CommandType {

    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

      /*  CrudDB crudDB = context.getBean("productCrudDB", CrudDB.class);
        crudDB.createTablesExamination();*/

        commandMap.put("addItem_product", new AddItemProduct(context.getBean(
                ProductReository.class), context.getBean(DiscountRepoitory.class)));
        commandMap.put("delete_product",
                new DeleteIdProduct(context.getBean(ProductReository.class)));
        commandMap.put("find_all", new GetProductAll(context.getBean(
                ProductReository.class)));
        commandMap.put("find_id", new GetProductId(context.getBean(
                ProductReository.class)));
        commandMap.put("update_item_product", new UpdateItemProduct(context.getBean(
                ProductReository.class),
                context.getBean(DiscountRepoitory.class)));
        commandMap.put("find_quantity", new GetProductQuantity( context.getBean(
                ProductReository.class)));
       /* commandMap.put("purchase", context.getBean("purchase",
                Command.class));*/
    }

    public static Map<String, Command> getCommandMap() {
        return commandMap;
    }
}
