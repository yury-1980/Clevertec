package ru.clevertec.model;

public class Check {
    private int number;
    private String title;
    private double price;
    private double totalSumOfOneItem;// Общая сумма 1-го
    private static double sumTotal;// Общая стоимость всего товара без скидки
    private static double discountTotal;// Общая стоимость всего скидочного товара
    private static double totalCostOfAllNon_discountItem;// Общая стоимость всего нескидочного товара
    private static double discontSum;// Сумма скидки
    private static double finalAmount; // Окочательная сумма

    public Check(int number, String title, double price, double totalSumOfOneItem) {
        this.number = number;
        this.title = title;
        this.price = price;
        this.totalSumOfOneItem = totalSumOfOneItem;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalSumOfOneItem() {
        return totalSumOfOneItem;
    }

    public void setTotalSumOfOneItem(double totalSumOfOneItem) {
        this.totalSumOfOneItem = totalSumOfOneItem;
    }

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

    @Override
    public String toString() {
        return "Check{" +
                "number=" + number +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", totalSumOfOneItem=" + totalSumOfOneItem +
                '}';
    }
}
