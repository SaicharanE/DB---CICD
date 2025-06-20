package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Insert {
    public static void main(String[] args) {
        String excelPath = "/Users/saicharanepparapally/Desktop/book2.xlsx";

        try {
            Connection conn = ConnectionFactory.getConnection();

            int eno = new Random().nextInt(10000);
            double esal = 5000 + (10000 - 5000) * new Random().nextDouble();
            double roundedSal = Math.round(esal * 100.0) / 100.0;

            String sql = "INSERT INTO emp1 (ENO, ESAL) VALUES (?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, eno);
                pstmt.setDouble(2, roundedSal);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ DB Inserted: ENO=" + eno + ", ESAL=" + roundedSal);

                    // Excel Write
                    File file = new File(excelPath);
                    Workbook workbook;
                    Sheet sheet;

                    if (file.exists()) {
                        FileInputStream fis = new FileInputStream(file);
                        workbook = new XSSFWorkbook(fis);
                        sheet = workbook.getSheetAt(0);
                        fis.close();
                    } else {
                        workbook = new XSSFWorkbook();
                        sheet = workbook.createSheet("Employees");
                        Row header = sheet.createRow(0);
                        header.createCell(0).setCellValue("ENO");
                        header.createCell(1).setCellValue("ESAL");
                    }

                    int lastRow = sheet.getLastRowNum();
                    Row row = sheet.createRow(lastRow + 1);
                    row.createCell(0).setCellValue(eno);
                    row.createCell(1).setCellValue(roundedSal);

                    FileOutputStream fos = new FileOutputStream(file);
                    workbook.write(fos);
                    fos.close();
                    workbook.close();

                    System.out.println("📘 Excel updated at: " + excelPath);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
