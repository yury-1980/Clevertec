package ru.clevertec.fabric;

import ru.clevertec.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReaderFile {
    static List<String> listInvalidData = new ArrayList<>();

    public static Map<Integer, Product> getProduct() {

        Map<Integer, Product> productMap = new HashMap<>();
        String title;
        int id;
        double price;
        boolean discount;
        String regId = "^([1-9]\\d?|100)$";
        String regTitle = "^([A-Z][a-z]{2,29})|([А-Я][а-я]{2,29})$";
        String regPrice = "^([1-9]\\d?\\.\\d\\d|100\\.00)$";
        String regDiscont = "^true|false$";
        String str;

        String path = new File("").getAbsolutePath();
        final String PRODUCTS = "/src/main/resources/products.txt";

        try (FileReader reader = new FileReader(path
                + PRODUCTS);
             Scanner scanner = new Scanner(reader)) {
            scanner.useLocale(Locale.ENGLISH);

            while (scanner.hasNextLine()) {
                int flag = 0;

                str = scanner.next();
                if (!str.matches(regId)) {
                    listInvalidData.add(str);
                    str = "0";
                    flag++;
                }
                id = Integer.parseInt(str);
                str = scanner.next();
                if (!str.matches(regTitle)) {
                    listInvalidData.add(str);
                    str = null;
                    flag++;
                }
                title = str;

                str = scanner.next();
                if (!str.matches(regPrice)) {
                    listInvalidData.add(str);
                    str = "0.0";
                    flag++;
                }
                price = Double.parseDouble(str);

                str = scanner.next();
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productMap;
    }
}
