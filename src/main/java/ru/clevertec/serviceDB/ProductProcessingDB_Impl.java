package ru.clevertec.serviceDB;

import ru.clevertec.model.Product;

import java.sql.*;
import java.util.Map;

public class ProductProcessingDB_Impl implements ProcessingDB {

    private static final String CREATE_PRODUCT = "INSERT INTO product " +
            "(id_product, name) VALUES ( ?, ?);";
    private static final String CREATE_PRICE = "INSERT INTO product_price " +
            "(price, product_id) VALUES (?, ?);";
    private static final String CREATE_DISCOUNT = "INSERT INTO discount  " +
            "(discount, product_id) VALUES (?, ?);";

    private static final String READ_DB = "select " +
            "id_product, pr.name, PP.price, D.discount from product pr " +
            "         join product_price PP on pr.id_product = PP" +
            ".product_id " +
            "         join discount D on pr.id_product = D.product_id" +
            " order by ?";

    private static final String UPDATE_PRODUCT_PRICE = "UPDATE product_price " +
            "SET price = ? WHERE product_id = ?";
    private static final String UPDATE_DISCOUNT = "UPDATE discount " +
            "SET discount = ? WHERE product_id = ?";
    private static final String UPDATE_PRODUCT = "UPDATE product " +
            "SET name = ? WHERE id_product = ?";

    private static final String DELETE_PRODUCT_PRICE = "DELETE FROM product_price " +
            "WHERE product_id = ?";
    private static final String DELETE_DISCOUNT = "DELETE FROM discount " +
            "WHERE product_id = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM product " +
            "WHERE id_product = ?";

    private static final String READ_DB_RETURN = "select " +
            "id_product, pr.name, PP.price, D.discount from product pr " +
            "         join product_price PP on pr.id_product = PP" +
            ".product_id " +
            "         join discount D on pr.id_product = D.product_id" +
            " WHERE id_product = ?";

    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    public static Connection connection;

    public static void connection() {
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

    @Override
    public Product create(int id, String name, double price, boolean discount) {
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(CREATE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(CREATE_PRICE);
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(CREATE_DISCOUNT);
            preparedStatement.setBoolean(1, discount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(READ_DB_RETURN);
            preparedStatement.setInt(1, id);

            connection.commit();

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_p = resultSet.getInt("id_product");
                String name_p = resultSet.getString("name");
                double price_p = resultSet.getDouble("price");
                boolean discount_p = resultSet.getBoolean("discount");
                product = new Product(id_p, name_p, price_p, discount_p);
            }
        } catch (SQLException throwables) {
            System.out.println("Adding failed!");
//            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public Map<Integer, Product> read_DB() {
        try {
            preparedStatement = connection.prepareStatement(READ_DB);
            preparedStatement.setString(1, "pr.id");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            System.out.println("Connection not found.");
//            throwables.printStackTrace();
        }
        return ReaderDB.getProduct(resultSet);
    }

    @Override
    public Product update(int id, String name, double price, boolean discount) {
            Product product = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_PRICE);
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(UPDATE_DISCOUNT);
            preparedStatement.setBoolean(1, discount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            connection.commit();

            preparedStatement = connection.prepareStatement(READ_DB_RETURN);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_p = resultSet.getInt("id_product");
                String name_p = resultSet.getString("name");
                double price_p = resultSet.getDouble("price");
                boolean discount_p = resultSet.getBoolean("discount");
                product = new Product(id_p, name_p, price_p, discount_p);
            }
        } catch (SQLException throwables) {
            System.out.println("Update failed!");
//            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public Product delete(int id) {
        try {
            preparedStatement =
                    connection.prepareStatement(DELETE_PRODUCT_PRICE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(DELETE_DISCOUNT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException throwables) {
            System.out.println("Delete failed!");
//            throwables.printStackTrace();
        }
        return null;
    }
}
