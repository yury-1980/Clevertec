package fabric;

import model.Product;

import java.io.*;
import java.util.*;

public class Reader {

    public static Map<Integer, Product> getProduct() {

        Map<Integer, Product> productMap = new HashMap<>();
        String title;
        int id;
        double price;
        boolean discount;
        String path = new File("").getAbsolutePath();

        try (FileReader reader = new FileReader(path + "/products.txt");
             Scanner scanner = new Scanner(reader)) {
            scanner.useLocale(Locale.ENGLISH);

            while (scanner.hasNext()) {
                title = scanner.next();
                id = scanner.nextInt();
                price = scanner.nextDouble();
                discount = scanner.nextBoolean();
                productMap.put(id, new Product(title, id, price, discount));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Если массив не прочитается из файла. Для работы программы
        productMap.put(11, new Product("Ball", 11, 10.5, true));
        productMap.put(12, new Product("Slippers", 12, 7.3, false));
        productMap.put(13, new Product("Pen", 13, 0.8, false));
        productMap.put(14, new Product("Pencil", 14, 0.9, true));
        productMap.put(15, new Product("WristWatch", 15, 45, true));
        productMap.put(16, new Product("Stool", 16, 25, false));
        productMap.put(17, new Product("Pasta", 17, 1.2, true));

        return productMap;
    }
}
