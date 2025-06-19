package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static Connection connection;

    static {
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/testdb"; // Replace 'testdb' with your database name
            String username = "root"; // Replace with your MySQL username
             String password = "Sai@9505";// replace with actual
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
