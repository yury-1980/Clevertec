package ru.clevertec.orm;

import ru.clevertec.dao.ProductConnectionDB;
import ru.clevertec.model.Product;

import java.sql.*;
import java.util.Map;

public class ProductCrudDB implements CrudDB {

    ProductConnectionDB productConnectionDB = ProductConnectionDB.getInstance();
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    @Override
    public Product create(int id, String name, double price, boolean discount) {
        Product product = null;
        try {
            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.CREATE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();

            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.CREATE_PRICE);
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.CREATE_DISCOUNT);
            preparedStatement.setBoolean(1, discount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.READ_DB_RETURN);
            preparedStatement.setInt(1, id);

            productConnectionDB.getConnection().commit();

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
            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.READ_DB);
            preparedStatement.setString(1, "pr.id");
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
            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.UPDATE_PRODUCT);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.UPDATE_PRODUCT_PRICE);
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.UPDATE_DISCOUNT);
            preparedStatement.setBoolean(1, discount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            productConnectionDB.getConnection().commit();

            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.READ_DB_RETURN);
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
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.DELETE_PRODUCT_PRICE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.DELETE_DISCOUNT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement =
                    productConnectionDB.getConnection()
                            .prepareStatement(ProductConnectionDB.DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            productConnectionDB.getConnection().commit();
        } catch (SQLException throwables) {
            System.out.println("Delete failed!");
//            throwables.printStackTrace();
        }
        return null;
    }
}
