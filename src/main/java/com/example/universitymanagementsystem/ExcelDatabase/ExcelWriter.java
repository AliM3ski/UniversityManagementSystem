package com.example.universitymanagementsystem.ExcelDatabase;

import com.example.universitymanagementsystem.SubjectManagement.Subject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import javafx.collections.ObservableList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    public static void writeToExcelSubject(ObservableList<Subject> subjects, String filePath) {
        // Open the existing workbook
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            // Get the existing sheet (by name, for example, "Subjects")
            Sheet sheet = workbook.getSheet("Subjects");

            // Get the last row number to continue writing after existing data
            int rowNum = sheet.getPhysicalNumberOfRows();

            // gets the last element of the list which contains the new subject we want to add
            int lastelement = subjects.size() -1;
            Subject subject = subjects.get(lastelement);
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(subject.getCode());  // Write subject code
            row.createCell(1).setCellValue(subject.getName());  // Write subject name

            // Auto resize columns for the first two columns(only ones with data) to fit content
            for (int i = 0; i < 2; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the changes back to the file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
