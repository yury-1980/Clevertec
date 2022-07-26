package ru.clevertec.service;

import ru.clevertec.serviceDB.ProcessingDB;
import ru.clevertec.serviceDB.ProductProcessingDB_Impl;
import ru.clevertec.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//3-15.5 200-500 2-5 2-5 1-15 11-15 12-10 card-6 7-10
public class ProposedPurchase {

    ProcessingDB productProcessingDB = new ProductProcessingDB_Impl();
    // Массив товара исходный
    Map<Integer, Product> products = productProcessingDB.read_DB();

    // Массив предпологаемой покупки
    static List<Product> masProducts = new ArrayList<>();
    static String[] str = new String[2];// id и кол-во

    public void masProducts(String[] args) {
        String regex_0 = "^([1-9]\\d?|100)$"; // id товара
        String regex_1 = "^([1-9]|1[0-9]|20)$";// Колличество товара

        for (String arg : args) {
            str = arg.split("-");

            int id;// консоль
            int num;// консоль

            if (!(str[0].equals("card"))) {

                if ((!str[0].matches(regex_0))
                        || (!str[1].matches(regex_1))) {
                    continue;
                }
                try {
                    id = Integer.parseInt(str[0]);// id из консоли
                    num = Integer.parseInt(str[1]);// кол-во из консоли
                } catch (NumberFormatException e) {
                    System.out.println("Wrong data!!!");
                    id = 0;
                    num = 0;
                }

                // Проверка наличия товара в магазине
                // и добавление в Массив предпологаемой покупки
                if (products.containsKey(id)) {
                    addProduct(id, num);
                } else {
                    System.out.println("Product not found!!!");
                }
            } else {// При предъявлении карты
                break;
            }
        }
    }

    void addProduct(int id, int num) {
        if (!(masProducts.contains(products.get(id)))) {
            // Добавление товара в  предпологаемую покупку
            masProducts.add(products.get(id));
            /* Кол-во товара */
            masProducts.get(masProducts.size() - 1).setNumber(num);
        } else {

            // Прибавление кол-ва товара к существующему, предпологаемую покупку
            masProducts.stream()
                    .filter(p -> p.getId() == products.get(id).getId())
                    .peek(p -> p.setNumber(p.getNumber() + num)).count();
        }
    }
}
