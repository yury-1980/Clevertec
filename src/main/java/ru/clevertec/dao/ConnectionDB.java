package ru.clevertec.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

    private Connection connection;

    private static final ConnectionDB connectionDB = new ConnectionDB();

    private final String CONNECTION_DB_PROPERTIES = "connectionDB.yaml";

    private Properties properties = new Properties();

    private ConnectionDB() {

        try {
            properties.load(ConnectionDB.class.getClassLoader()
                    .getResourceAsStream(CONNECTION_DB_PROPERTIES));
        } catch (IOException e) {
            System.out.println("File not found properties!");
            e.printStackTrace();
        }

        try {
            Class.forName(properties.getProperty("DRIVER"));
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found.");
        }

        try {
            connection = DriverManager.getConnection(properties
                    .getProperty("jdbc_URL"), properties
                    .getProperty("jdbc_USER"), properties.getProperty("jdbc_PSW"));
            System.out.println("Connection DB.");
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            System.out.println("Connection not found.");
        }
    }

    public static ConnectionDB getInstance() {
        return connectionDB;
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
