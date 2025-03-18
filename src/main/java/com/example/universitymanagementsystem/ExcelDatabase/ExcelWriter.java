package com.example.universitymanagementsystem.ExcelDatabase;

import com.example.universitymanagementsystem.SubjectManagement.Subject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.collections.ObservableList;
import java.io.*;

public class ExcelWriter {

    public static void writeToExcelSubject(ObservableList<Subject> subjects, String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            // Get the sheet where subjects are stored
            Sheet sheet = workbook.getSheet("Subjects");
            // variable to store number of filled rows
            int rowNum = sheet.getPhysicalNumberOfRows();
            // variable to store position of the last element of the sheet
            int lastElement = subjects.size() - 1;
            // gets the last element of the sheet
            Subject subject = subjects.get(lastElement);
            // creates a new row
            Row row = sheet.createRow(rowNum++);
            // adds the new subject to the new row
            row.createCell(0).setCellValue(subject.getCode());
            row.createCell(1).setCellValue(subject.getName());

            // fixes sizing in the column if need be
            for (int i = 0; i < 2; i++) {
                sheet.autoSizeColumn(i);
            }

            // sends updates to the excel file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ** Edit Subject in Excel **
    public static void editSubjectInExcel(String filePath, String oldsSubjectCode, Subject selected) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Subjects");
            // Loop through each row to find the row with the given subject code
            for (Row row : sheet) {
                Cell codeCell = row.getCell(0);
                // Since excel file has not been updated yet, it still has the pre edit code and subject. So when it find the column with the old subject code it replaces its data with the new name and code
                if (codeCell != null && codeCell.getStringCellValue().equals(oldsSubjectCode)) {
                    row.getCell(1).setCellValue(selected.getName()); // Update subject name
                    row.getCell(0).setCellValue(selected.getCode());
                    break;
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  Delete Subject from Excel
    public static void deleteSubjectFromExcel(String filePath, String subjectCode) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Subjects");
            // Variable to store the index of the row to delete
            int rowIndex = -1;

            // Loop through each row to find the row with the given subject code
            for (Row row : sheet) {
                // get column of the subject codes
                Cell codeCell = row.getCell(0);
                if (codeCell != null && codeCell.getStringCellValue().equals(subjectCode)) {
                    // Store the row number of the matching subject
                    rowIndex = row.getRowNum();
                    // Stop searching once we find the subject
                    break;
                }
            }
            if (rowIndex != -1) {
                // Get the last row number
                int lastRowNum = sheet.getLastRowNum();

                // Remove the row from the sheet
                sheet.removeRow(sheet.getRow(rowIndex));

                // If the deleted row is not the last row, shift remaining rows up
                if (rowIndex < lastRowNum) {
                    sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
