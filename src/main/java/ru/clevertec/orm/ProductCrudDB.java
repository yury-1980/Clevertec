package ru.clevertec.orm;

import ru.clevertec.dao.ConnectionDB;
import ru.clevertec.dao.Requests;
import ru.clevertec.model.Product;

import java.sql.*;
import java.util.Map;

public class ProductCrudDB implements CrudDB {

    ConnectionDB connectionDB = ConnectionDB.getInstance();
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    @Override
    public Product create(int id, String name, double price, boolean discount) {
        Product product = null;
        try {
            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.CREATE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();

            preparedStatement =
                    connectionDB.getConnection()
                            .prepareStatement(Requests.CREATE_PRICE);
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    connectionDB.getConnection()
                            .prepareStatement(Requests.CREATE_DISCOUNT);
            preparedStatement.setBoolean(1, discount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    connectionDB.getConnection()
                            .prepareStatement(Requests.READ_DB_RETURN);
            preparedStatement.setInt(1, id);

            connectionDB.getConnection().commit();

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
    public Map<Integer, Product> readAllDB() {

        int page_size = 10;

        try {
            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.READ_DB_ALL);
            preparedStatement.setInt(1, page_size);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            System.out.println("Connection not found.");
//            throwables.printStackTrace();
        }
        return ReaderDB.getAllProducts(resultSet);
    }

    @Override
    public Map<Integer, Product> readAllDB(int page_size) {

        try {
            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.READ_DB_QUANTITY);
            preparedStatement.setInt(1, page_size);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            System.out.println("Connection not found.");
//            throwables.printStackTrace();
        }
        return ReaderDB.getAllProducts(resultSet);
    }

    @Override
    public Product update(int id, String name, double price, boolean discount) {
        Product product = null;
        try {
            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.UPDATE_PRODUCT);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    connectionDB.getConnection()
                            .prepareStatement(Requests.UPDATE_PRODUCT_PRICE);
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    connectionDB.getConnection()
                            .prepareStatement(Requests.UPDATE_DISCOUNT);
            preparedStatement.setBoolean(1, discount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            connectionDB.getConnection().commit();

            preparedStatement =
                    connectionDB.getConnection()
                            .prepareStatement(Requests.READ_DB_RETURN);
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
            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.DELETE_PRODUCT_PRICE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.DELETE_DISCOUNT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            connectionDB.getConnection().commit();
        } catch (SQLException throwables) {
            System.out.println("Delete failed!");
//            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void createTablesExamination() {

        try {
            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.CREATE_TABLE_PRODUCT);
            preparedStatement.executeUpdate();


            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.CREATE_TABLE_PRODUCT_PRICE);
            preparedStatement.executeUpdate();

            preparedStatement = connectionDB.getConnection()
                    .prepareStatement(Requests.CREATE_TABLE_DISCOUNT);
            preparedStatement.executeUpdate();

            connectionDB.getConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }
}
