package ru.clevertec.service;

import ru.clevertec.fabric.Reader;
import ru.clevertec.model.Check;
import ru.clevertec.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                        masProducts.get(masProducts.size() - 1).setNumber(num);
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


        int productNumDiscont = 0;// Кол-во дисконтного товара
        List<Check> checks = new ArrayList<>();

        // Подсчёт кол-ва дисконтных товаров
        productNumDiscont = masProducts.stream().filter(x -> x.isDiscount())
                .map(x -> x.getNumber()).mapToInt(x -> x).sum();


        int finalProductNumDiscont = productNumDiscont;
        String[] finalStr = str;

        masProducts.stream()
                .filter(p -> p.isDiscount() && (finalProductNumDiscont > 5)
                        && finalStr[0].equals("card"))
                .peek(p -> {
            checks.add(new Check(p.getNumber(), p.getTitle(), p.getPrice(),
                    p.getNumber() * p.getPrice() * 0.9));

            // Общая стоимость всего скидочного товара
            Check.setDiscountTotal((Check.getDiscountTotal()
                    + p.getNumber() * p.getPrice() * 0.9));
        }).collect(Collectors.toList());

        masProducts.stream()
                .filter(p -> !p.isDiscount())
                .peek(p -> {
                    checks.add(new Check(p.getNumber(), p.getTitle(), p.getPrice(),
                            p.getNumber() * p.getPrice()));
                    // Общая стоимость всего нескидочного товара
                    Check.setTotalCostOfAllNon_discountItem(Check.getTotalCostOfAllNon_discountItem()
                            + p.getNumber() * p.getPrice());
                }).collect(Collectors.toList());

        // Общая стоимость всего товара без скидки
        Check.setSumTotal(masProducts.stream().map(p -> p.getNumber() * p.getPrice())
                .mapToDouble(p -> p).sum());


        // Сумма всей скидки
        Check.setDiscontSum(Check.getSumTotal() - Check.getTotalCostOfAllNon_discountItem()
                - Check.getDiscountTotal());
        // Окончательная сумма
        Check.setFinalAmount(Check.getDiscountTotal() + Check.getTotalCostOfAllNon_discountItem());

        checkList = checks;
    }
}