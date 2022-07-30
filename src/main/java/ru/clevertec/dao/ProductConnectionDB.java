package ru.clevertec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductConnectionDB {

    public static final String CREATE_PRODUCT = "INSERT INTO product " +
            "(id_product, name) VALUES ( ?, ?);";
    public static final String CREATE_PRICE = "INSERT INTO product_price " +
            "(price, product_id) VALUES (?, ?);";
    public static final String CREATE_DISCOUNT = "INSERT INTO discount  " +
            "(discount, product_id) VALUES (?, ?);";

    public static final String READ_DB = "select " +
            "id_product, pr.name, PP.price, D.discount from product pr " +
            "         join product_price PP on pr.id_product = PP" +
            ".product_id " +
            "         join discount D on pr.id_product = D.product_id" +
            " order by ?";

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
            "         join product_price PP on pr.id_product = PP" +
            ".product_id " +
            "         join discount D on pr.id_product = D.product_id" +
            " WHERE id_product = ?";

    private Connection connection;

    private static ProductConnectionDB productConnectionDB =
            new ProductConnectionDB();

    private ProductConnectionDB() {
        String jdbc_URL = "jdbc:postgresql://localhost:5432" +
                "/Control_DB";
        String jdbc_USER = "postgres";
        String jdbc_PSW = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found.");
        }

        try {
            connection = DriverManager.getConnection(jdbc_URL, jdbc_USER, jdbc_PSW);
            System.out.println("Connection DB.");
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            System.out.println("Connection not found.");
        }
    }

    public static ProductConnectionDB getInstance() {
        return productConnectionDB;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection close.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        connection = null;
    }
}
