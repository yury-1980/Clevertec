package ru.clevertec.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.clevertec.SpringConfig;
import ru.clevertec.orm.CrudDB;

import java.util.HashMap;
import java.util.Map;

public class CommandType {

    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        CrudDB crudDB = context.getBean("productCrudDB", CrudDB.class);
        crudDB.createTablesExamination();

        commandMap.put("addItem_product", context.getBean("addItemProduct",
                Command.class));
        commandMap.put("delete_product", context.getBean("deleteItemProduct",
                Command.class));
        commandMap.put("find_all", context.getBean("getProductAll",
                Command.class));
        commandMap.put("update_item_product", context.getBean("updateItemProduct",
                Command.class));
        commandMap.put("find_quantity", context.getBean("getProductQuantity",
                Command.class));
        commandMap.put("purchase", context.getBean("purchase",
                Command.class));
    }
//    private CommandType(){}
    public static Map<String, Command> getCommandMap() {
        return commandMap;
    }
}
