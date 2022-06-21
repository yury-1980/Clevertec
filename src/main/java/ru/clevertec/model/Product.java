package ru.clevertec.model;

public class Product {
    private int id;
    private String title;
    private double price;
    private boolean discount;
    private int number;

    public Product(int id, String title, double price, boolean discount) {
        this.title = title;
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.number = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", number=" + number +
                '}';
    }
}
