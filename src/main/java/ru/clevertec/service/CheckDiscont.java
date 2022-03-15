package ru.clevertec.service;

import ru.clevertec.fabric.Reader;
import ru.clevertec.model.Check;
import ru.clevertec.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckDiscont {

    public static List<Check> checkList;

    public static void masProducts(String[] args) {
        Map<Integer, Product> products = Reader.getProduct();// Массив товара исходный
        List<Product> masProducts = new ArrayList<>();// Массив предпологаемой покупки
        String[] str = new String[2];// id и кол-во

        for (String arg : args) {
            str = arg.split("-");

            int id;// консоль
            int num;// консоль

            if (!(str[0].equals("card"))) {

                if ((!str[0].matches("^([1-9]\\d?|100)$"))
                        || (!str[1].matches("^([1-9]|1[0-9]|20)$"))) {
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

                if (products.containsKey(id)) {// Проверка наличия товара в магазине

                    if (!(masProducts.contains(products.get(id)))) {
                        masProducts.add(products.get(id));// Добавление товара в чек
                        /* Кол-во товара */
                        masProducts.get(masProducts.size() - 1).
                                setNumber(masProducts.get(masProducts.size() - 1).
                                        getNumber() + num);
                    } else {

                        for (Product m : masProducts) {

                            if (m.getId() == products.get(id).getId()) {
                                // Прибавление кол-ва товара в чек, к существующему
                                m.setNumber(m.getNumber() + num);
                            }
                        }
                    }

                } else {
                    System.out.println("Product not found!!!");
                }
            } else {
                break;
            }
        }


        double totalSumOfOneItem;// Общая сумма 1-го товара
        int productNumDiscont = 0;// Кол-во дисконтного товара
        List<Check> checks = new ArrayList<>();

        for (Product p : masProducts) {// Подсчёт кол-ва дисконтных товаров

            if (p.isDiscount()) {
                productNumDiscont = productNumDiscont + p.getNumber();
            }
        }

        for (Product p : masProducts) {
            // Общая сумма 1-го товара со скидкой
            if (p.isDiscount() && productNumDiscont > 5 && str[0].equals("card")) {
                totalSumOfOneItem = p.getNumber() * p.getPrice()
                        - p.getNumber() * p.getPrice() * 0.1;
                checks.add(new Check(p.getNumber(), p.getTitle(), p.getPrice(),
                        totalSumOfOneItem));
                Check.setDiscountTotal(Check.getDiscountTotal() + totalSumOfOneItem);
            } else {// Общая сумма 1-го типа товара без скидки
                totalSumOfOneItem = p.getNumber() * p.getPrice();
                checks.add(new Check(p.getNumber(), p.getTitle(), p.getPrice(),
                        totalSumOfOneItem));
                // Общая стоимость всего нескидочного товара
                Check.setTotalCostOfAllNon_discountItem(Check.getTotalCostOfAllNon_discountItem()
                        + totalSumOfOneItem);
            }
            // Общая стоимость всего товара без скидки
            Check.setSumTotal(Check.getSumTotal() + (p.getNumber() * p.getPrice()));
        }
        // Сумма всей скидки
        Check.setDiscontSum(Check.getSumTotal() - Check.getTotalCostOfAllNon_discountItem()
                - Check.getDiscountTotal());
        // Окончательная сумма
        Check.setFinalAmount(Check.getDiscountTotal() + Check.getTotalCostOfAllNon_discountItem());

        checkList = checks;
    }
}