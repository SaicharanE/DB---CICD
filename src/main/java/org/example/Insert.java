package org.example;



import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Insert {
    public static void main(String[] args) throws SQLException {
        DatabaseMetaData ConnectionFactory = null;
        Connection conn = ConnectionFactory.getConnection();

        // Generate random data for each run
        int eno = new Random().nextInt(10000); // Random employee number
        double esal = 5000 + (10000 - 5000) * new Random().nextDouble(); // Salary between 5000 and 10000

        String sql = "INSERT INTO emp1 (ENO, ESAL) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, eno);
            pstmt.setDouble(2, Math.round(esal * 100.0) / 100.0); // Rounding to 2 decimal places
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ New employee inserted: ENO = " + eno + ", ESAL = " + esal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
