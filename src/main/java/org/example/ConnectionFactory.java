package org.example;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection connection;
    static String jdbcURL = "jdbc:mysql://localhost:3306/testdb"; // Replace 'testdb' with your database name
    static String username = "root"; // Replace with your MySQL username
    static String password = "Sai@9505"; // Replace with your MySQL password
    static {
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }
}
