package ru.clevertec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private Connection connection;

    private static ConnectionDB connectionDB = new ConnectionDB();

    private ConnectionDB() {
        String jdbc_URL = "jdbc:postgresql://localhost:5432/Control_DB";
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
