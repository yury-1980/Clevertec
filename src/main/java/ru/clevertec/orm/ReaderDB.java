package ru.clevertec.orm;

import ru.clevertec.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReaderDB {
    public static List<String> listInvalidData = new ArrayList<>();

    public static Map<Integer, Product> getAllProducts(ResultSet resultSet) {

        Map<Integer, Product> productMap = new HashMap<>();
        int id;
        String title;
        double price;
        boolean discount;
        String regId = "^([1-9]\\d?|100)$";
        String regTitle = "^([A-Z][a-z]{2,29})|([А-Я][а-я]{2,29})$";
        String regPrice = "^([1-9]\\d?\\.\\d\\d|100\\.00)$";
        String regDiscont = "^true|false$";
        String str;

        try {

            while (resultSet.next()) {
                int flag = 0;

                str = String.valueOf(resultSet.getInt("id_product"));
                if (!str.matches(regId)) {
                    listInvalidData.add(str);
                    str = "0";
                    flag++;
                }
                id = Integer.parseInt(str);

                str = resultSet.getString("name");
                if (!str.matches(regTitle)) {
                    listInvalidData.add(str);
                    str = null;
                    flag++;
                }
                title = str;

                str = String.valueOf(resultSet.getString("price"));
                if (!str.matches(regPrice)) {
                    listInvalidData.add(str);
                    str = "0.0";
                    flag++;
                }
                price = Double.parseDouble(str);

                str = String.valueOf(resultSet.getString("discount"));
                if (!str.matches(regDiscont)) {
                    listInvalidData.add(str);
                    str = "false";
                    flag++;
                }
                discount = Boolean.parseBoolean(str);

                if (flag != 0) {// Для выбора конкретных не валидных данных
                    continue;
                }

                productMap.put(id, new Product(id, title, price, discount));

            }

        } catch (NoSuchElementException e) {
            System.out.println("Была считана пустая строка!!!");
        } catch (SQLException throwables) {
            System.out.println("Not reader DB!");
        }

        return productMap;
    }
}
