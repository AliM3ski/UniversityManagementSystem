package com.example.universitymanagementsystem.ExcelDatabase;

import com.example.universitymanagementsystem.SubjectManagement.Subject;

import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    // method for finding the last non empty row in  excel file
    public static int findLastNonEmptyRow(Sheet sheet) {
        int lastRow = sheet.getLastRowNum(); // Highest row index
        while (lastRow >= 0) {
            Row row = sheet.getRow(lastRow);
            if (row != null && !isRowEmpty(row)) {
                return lastRow; // Found the actual last row with data
            }
            lastRow--;
        }
        return -1; // No data found
    }

    // Helper method to check if a row is empty
    private static boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.BLANK && cell.getCellType() != CellType._NONE) {
                return false; // Found a non-empty cell
            }
        }
        return true; // Entire row is empty
    }


    // Reads an Excel (.xlsx) file and prints its content to the console.
    //@param filePath The path to the Excel file.
    // List used cause dynamic, and ordered
    public static void readExcelSubject(ObservableList<Subject> subjects, String filePath) {
        try (
                // Open the file as an input stream
                FileInputStream fis = new FileInputStream(filePath);
                // Create a workbook object to access the Excel file. Workbook representing an entire Excel ile
                Workbook workbook = new XSSFWorkbook(fis)
        ) {
            // Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            int lastRow = findLastNonEmptyRow(sheet); // Get last non-empty row index

            // Loop through each row in the sheet
            for (int i = 0; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                // Loop through each cell in the row
                if (row != null) {
                    Cell codeCell = row.getCell(0); // Subject code located in column A
                    Cell nameCell = row.getCell(1); // Subject code located in column B

                    if (codeCell != null && nameCell != null) {
                        String code = codeCell.getStringCellValue().trim();
                        String name = nameCell.getStringCellValue().trim();

                        // Add subject to the list
                        subjects.add(new Subject(code, name));
                    }
                }
            }
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found or read errors)
            // prints details about an exception (error) that occurred in a program.
            e.printStackTrace();
        }
    }

}