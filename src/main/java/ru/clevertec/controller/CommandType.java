package ru.clevertec.controller;

import ru.clevertec.controller.impl.*;
import ru.clevertec.orm.CrudDB;
import ru.clevertec.orm.ProductCrudDB;

import java.util.HashMap;
import java.util.Map;

public class CommandType {

    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        CrudDB crudDB = new ProductCrudDB();
        crudDB.createTablesExamination();
        commandMap.put("addItem_product", new AddItemProduct());
        commandMap.put("delete_product", new DeleteItemProduct());
        commandMap.put("find_all", new GetProductAll());
        commandMap.put("update_item_product", new UpdateItemProduct());
        commandMap.put("find_quantity", new GetProductQuantity());
    }

//    private CommandType(){}

    public static Map<String, Command> getCommandMap() {
        return commandMap;
    }
}
