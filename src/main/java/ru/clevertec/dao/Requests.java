package ru.clevertec.dao;

public class Requests {

    public static final String CREATE_PRODUCT = "INSERT INTO product " +
            "(id_product, name) VALUES ( ?, ?);";
    public static final String CREATE_PRICE = "INSERT INTO product_price " +
            "(price, product_id) VALUES (?, ?);";
    public static final String CREATE_DISCOUNT = "INSERT INTO discount  " +
            "(discount, product_id) VALUES (?, ?);";

    public static final String READ_DB_ALL = "select " +
            "id_product, pr.name, PP.price, D.discount from product pr " +
            "         join product_price PP on pr.id_product = PP" +
            ".product_id " +
            "         join discount D on pr.id_product = D.product_id" +
            " WHERE pr.id_product >= 1 AND pr.id_product <=?" +
            " order by pr.name";

    public static final String READ_DB_QUANTITY = "select " +
            "id_product, pr.name, PP.price, D.discount from product pr " +
            "         join product_price PP on pr.id_product = PP" +
            ".product_id " +
            "         join discount D on pr.id_product = D.product_id" +
            " WHERE pr.id_product <= ?";

    public static final String UPDATE_PRODUCT_PRICE = "UPDATE product_price " +
            "SET price = ? WHERE product_id = ?";
    public static final String UPDATE_DISCOUNT = "UPDATE discount " +
            "SET discount = ? WHERE product_id = ?";
    public static final String UPDATE_PRODUCT = "UPDATE product " +
            "SET name = ? WHERE id_product = ?";

    public static final String DELETE_PRODUCT_PRICE = "DELETE FROM product_price " +
            "WHERE product_id = ?";
    public static final String DELETE_DISCOUNT = "DELETE FROM discount " +
            "WHERE product_id = ?";
    public static final String DELETE_PRODUCT = "DELETE FROM product " +
            "WHERE id_product = ?";

    public static final String READ_DB_RETURN = "select " +
            "id_product, pr.name, PP.price, D.discount from product pr " +
            "         join product_price PP on pr.id_product = PP.product_id " +
            "         join discount D on pr.id_product = D.product_id" +
            " WHERE id_product = ?";

    public static final String CREATE_TABLE_PRODUCT = "CREATE TABLE if not " +
            "exists product (id SERIAL PRIMARY KEY, " +
            "id_product INTEGER NOT NULL, name VARCHAR(30) NOT NULL)";
    public static final String CREATE_TABLE_PRODUCT_PRICE = "CREATE TABLE if " +
            "    not exists productprice (id SERIAL PRIMARY KEY, " +
            "    price      DECIMAL(5, 2) NOT NULL, " +
            "    product_id INTEGER NOT NULL, FOREIGN KEY (product_id) " +
            "        REFERENCES product (id_product))";
    public static final String CREATE_TABLE_DISCOUNT = "CREATE TABLE if not" +
            " exists discount (id SERIAL PRIMARY KEY, " +
            "    discount   VARCHAR(5) NOT NULL, " +
            "    product_id INTEGER    NOT NULL, " +
            "    FOREIGN KEY (product_id) REFERENCES product (id_product))";

}
