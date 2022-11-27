package ru.clevertec.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Check {
    private int number;
    private String title;
    private double price;
    private double totalSumOfOneItem;// Общая сумма 1-го типа
    private static double sumTotal;// Общая стоимость всего товара без скидки
    private static double discountTotal;// Общая стоимость всего скидочного товара
    private static double totalCostOfAllNon_discountItem;// Общая стоимость всего нескидочного товара
    private static double discontSum;// Сумма скидки
    private static double finalAmount; // Окочательная сумма

    public static double getSumTotal() {
        return sumTotal;
    }

    public static void setSumTotal(double sumTotal) {
        Check.sumTotal = sumTotal;
    }

    public static double getDiscountTotal() {
        return discountTotal;
    }

    public static void setDiscountTotal(double discountTotal) {
        Check.discountTotal = discountTotal;
    }

    public static double getTotalCostOfAllNon_discountItem() {
        return totalCostOfAllNon_discountItem;
    }

    public static void setTotalCostOfAllNon_discountItem(double totalCostOfAllNon_discountItem) {
        Check.totalCostOfAllNon_discountItem = totalCostOfAllNon_discountItem;
    }

    public static double getDiscontSum() {
        return discontSum;
    }

    public static void setDiscontSum(double discontSum) {
        Check.discontSum = discontSum;
    }

    public static double getFinalAmount() {
        return finalAmount;
    }

    public static void setFinalAmount(double finalAmount) {
        Check.finalAmount = finalAmount;
    }
}
