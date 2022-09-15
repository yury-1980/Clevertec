/*
package ru.clevertec.service;

import org.springframework.stereotype.Service;
import ru.clevertec.dao.CrudDB;
import ru.clevertec.dao.ProductCrudDB;
import ru.clevertec.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.clevertec.validation.StringValidation.quantityOfGoods;
import static ru.clevertec.validation.StringValidation.regId;

@Service
public class ProposedPurchase {

    CrudDB crudDB = new ProductCrudDB();
    // Массив товара исходный
    Map<Integer, Product> products = crudDB.readAllDB();

    // Массив предпологаемой покупки
    static List<Product> masProducts = new ArrayList<>();
    static String[] str = new String[2];// id и кол-во

    public void masProducts(String[] args) {

        for (String arg : args) {
            str = arg.split("-");

            int id;// консоль
            int num;// консоль

            if (!(str[0].equals("card"))) {

                if ((!str[0].matches(regId))
                        || (!str[1].matches(quantityOfGoods))) {
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
            */
/* Кол-во товара *//*

            masProducts.get(masProducts.size() - 1).setNumber(num);
        } else {

            // Прибавление кол-ва товара к существующему, предпологаемую покупку
            masProducts.stream()
                    .filter(p -> p.getId() == products.get(id).getId())
                    .peek(p -> p.setNumber(p.getNumber() + num)).count();
        }
    }
}
*/
