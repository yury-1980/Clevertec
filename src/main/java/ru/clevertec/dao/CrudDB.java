package ru.clevertec.dao;

import ru.clevertec.model.Product;

import java.util.Map;

public interface CrudDB {

    Product create(int id, String name, double price, boolean discount);
    Map<Integer, Product> readAllDB();
    Map<Integer, Product> readAllDB(int num);
    Product update(int id, String name, double price, boolean discount);
    Product delete(int id);
    void createTablesExamination();
}
