package ru.clevertec.serviceDB;

import ru.clevertec.model.Product;

import java.util.Map;

public interface ProcessingDB {

    Product create(int id, String name, double price, boolean discount);
    Map<Integer, Product> read_DB();
    Product update(int id, String name, double price, boolean discount);
    Product delete(int id);
}
