/*
package ru.clevertec.service;

import ru.clevertec.dto.Check;
import ru.clevertec.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class CheckProductServiceImpl implements CheckProductService {

    // Массив предпологаемой покупки
    public static List<Product> masProducts = ProposedPurchase.masProducts;

    public static String[] str = ProposedPurchase.str;

    // Итоговый массив - чек
    public static List<Check> checks = new ArrayList<>();

    public List<Check> service() {

        // Общая стоимость всего товара по скидке, добавление в чек
        setDiscountTotal(str);

        // Общая стоимость всего нескидочного товара, добавление в чек
        setTotalCostOfAllNon_discountItem(str);

        // Полная стоимость всего товара (без скидки)
        setSumTotal();

        // Сумма всей скидки
        setDiscontSum();
        // Окончательная сумма (с учётом скидок)
        setFinalAmount();

        return checks;
    }

    //    Общая сумма 1-го типа
    double totalSumOfOneItem(Product product) {
        return product.getNumber() * product.getPrice();
    }

    // Подсчёт кол-ва дисконтных товаров
    int getProductNumDiscont(List<Product> masProducts) {
        return masProducts.stream().filter(x -> x.isDiscount())
                .map(x -> x.getNumber()).mapToInt(x -> x).sum();
    }

    boolean getIsDiscountCard(String[] str) {
        return getProductNumDiscont(masProducts) > 5 && str[0].equals(
                "card");
    }


    public void setDiscountTotal(String[] str) {

        // Общая стоимость всего товара по скидке, добавление в чек
        Check.setDiscountTotal(masProducts.stream()
                .filter(p -> p.isDiscount() && getIsDiscountCard(str))
                .peek(p ->
                        checks.add(new Check(p.getNumber(), p.getTitle(), p.getPrice(),
                                totalSumOfOneItem(p) * 0.9))
                ).map(p -> (totalSumOfOneItem(p) * 0.9))
                .mapToDouble(p -> p).sum());
    }

    // Общая стоимость всего нескидочного товара, добавление в чек
    public void setTotalCostOfAllNon_discountItem(String[] str) {

        Check.setTotalCostOfAllNon_discountItem(masProducts.stream()
                .filter(p -> !(p.isDiscount() && getIsDiscountCard(str)))
                .peek(p ->
                        checks.add(new Check(p.getNumber(), p.getTitle(), p.getPrice(),
                                totalSumOfOneItem(p)))
                ).map(p -> totalSumOfOneItem(p))
                .mapToDouble(p -> p).sum());
    }

    // Полная стоимость всего товара (без скидки)
    public void setSumTotal() {
        Check.setSumTotal(masProducts.stream().map(p -> totalSumOfOneItem(p))
                .mapToDouble(p -> p).sum());
    }

    // Сумма всей скидки
    public void setDiscontSum() {
        Check.setDiscontSum(Check.getSumTotal()
                - Check.getTotalCostOfAllNon_discountItem() - Check.getDiscountTotal());
    }

    // Окончательная сумма
    public void setFinalAmount() {
        Check.setFinalAmount(Check.getDiscountTotal()
                + Check.getTotalCostOfAllNon_discountItem());
    }
}*/
