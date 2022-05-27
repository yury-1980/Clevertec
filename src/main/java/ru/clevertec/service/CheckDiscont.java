package ru.clevertec.service;

import ru.clevertec.fabric.Reader;
import ru.clevertec.model.Check;
import ru.clevertec.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckDiscont {

    // Массив товара исходный
    static Map<Integer, Product> products = Reader.getProduct();

    // Массив предпологаемой покупки
    static List<Product> masProducts = new ArrayList<>();

    // Итоговый массив - чек
    public static List<Check> checks = new ArrayList<>();

    public static void masProducts(String[] args) {
        String[] str = new String[2];// id и кол-во
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
                if (products.containsKey(id)) {
                    addProduct(id, num);
                } else {
                    System.out.println("Product not found!!!");
                }
            } else {// При предъявлении карты
                break;
            }
        }

        // Общая стоимость всего товара по скидке, добавление в чек
        setDiscountTotal(str);

        // Общая стоимость всего нескидочного товара, добавление в чек
        setTotalCostOfAllNon_discountItem();

        // Полная стоимость всего товара (без скидки)
        setSumTotal();

        // Сумма всей скидки
        setDiscontSum();
        // Окончательная сумма (с учётом скидок)
        setFinalAmount();
    }

    static void addProduct(int id, int num) {
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

    //    Общая сумма 1-го типа
    static double totalSumOfOneItem(Product product) {
        return product.getNumber() * product.getPrice();
    }

    // Подсчёт кол-ва дисконтных товаров
    static int getProductNumDiscont(List<Product> masProducts) {
        int productNumDiscont = masProducts.stream().filter(x -> x.isDiscount())
                .map(x -> x.getNumber()).mapToInt(x -> x).sum();
        return productNumDiscont;
    }

    static void setDiscountTotal(String[] str) {
        boolean isDiscountCard = getProductNumDiscont(masProducts) > 5 && str[0].equals(
                "card");

        // Общая стоимость всего товара по скидке, добавление в чек
        Check.setDiscountTotal(masProducts.stream()
                .filter(p -> p.isDiscount() && isDiscountCard)
                .peek(p ->
                        checks.add(new Check(p.getNumber(), p.getTitle(), p.getPrice(),
                                totalSumOfOneItem(p) * 0.9))
                ).map(p -> (totalSumOfOneItem(p) * 0.9))
                .mapToDouble(p -> p).sum());
    }

    // Общая стоимость всего нескидочного товара, добавление в чек
    static void setTotalCostOfAllNon_discountItem() {
        Check.setTotalCostOfAllNon_discountItem(masProducts.stream()
                .filter(p -> !p.isDiscount())
                .peek(p ->
                        checks.add(new Check(p.getNumber(), p.getTitle(), p.getPrice(),
                                totalSumOfOneItem(p)))
                ).map(p -> totalSumOfOneItem(p))
                .mapToDouble(p -> p).sum());
    }

    // Полная стоимость всего товара (без скидки)
    static void setSumTotal() {
        Check.setSumTotal(masProducts.stream().map(p -> totalSumOfOneItem(p))
                .mapToDouble(p -> p).sum());
    }

    // Сумма всей скидки
    static void setDiscontSum() {
        Check.setDiscontSum(Check.getSumTotal()
                - Check.getTotalCostOfAllNon_discountItem() - Check.getDiscountTotal());
    }

    // Окончательная сумма
    static void setFinalAmount() {
        Check.setFinalAmount(Check.getDiscountTotal()
                + Check.getTotalCostOfAllNon_discountItem());
    }
}