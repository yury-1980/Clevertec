package ru.clevertec.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

}
