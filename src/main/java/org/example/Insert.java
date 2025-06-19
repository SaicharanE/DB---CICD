package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Insert {
    public static void main(String[] args) {
        try {
            // Get connection from your factory class
            Connection conn = ConnectionFactory.getConnection();

            // Generate random data
            int eno = new Random().nextInt(10000);
            double esal = 5000 + (10000 - 5000) * new Random().nextDouble();

            String sql = "INSERT INTO emp1 (ENO, ESAL) VALUES (?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, eno);
                pstmt.setDouble(2, Math.round(esal * 100.0) / 100.0);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ New employee inserted: ENO = " + eno + ", ESAL = " + esal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
