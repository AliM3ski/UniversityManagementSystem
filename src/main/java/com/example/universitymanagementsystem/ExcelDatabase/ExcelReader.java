package com.example.universitymanagementsystem.ExcelDatabase;

import com.example.universitymanagementsystem.CourseManagement.Course;
import com.example.universitymanagementsystem.SubjectManagement.Subject;

import com.example.universitymanagementsystem.Users.Student;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

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

    public static void readExcelStudent(ObservableList<Student> studentList, String filePath) {
        try (
                // Open the file as an input stream
                FileInputStream fis = new FileInputStream(filePath);
                // Create a workbook object to access the Excel file. Workbook representing an entire Excel ile
                Workbook workbook = new XSSFWorkbook(fis)
        ) {
            // Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(2);

            int lastRow = findLastNonEmptyRow(sheet); // Get last non-empty row index

            // Loop through each row in the sheet
            for (int i = 0; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                // Loop through each cell in the row
                if (row != null) {
                    Cell studentIdCell = row.getCell(0);
                    Cell nameCell = row.getCell(1);
                    Cell addressCell = row.getCell(2);
                    Cell phoneCell = row.getCell(3);
                    Cell emailCell = row.getCell(4);
                    Cell academicLevelCell = row.getCell(5);
                    Cell currentSemesterCell = row.getCell(6);

                    if (studentIdCell != null && nameCell != null && addressCell != null && phoneCell != null && emailCell != null && academicLevelCell != null && currentSemesterCell != null) {
                        String studentid = studentIdCell.getStringCellValue().trim();
                        String name = nameCell.getStringCellValue().trim();
                        String email = emailCell.getStringCellValue().trim();
                        String address = addressCell.getStringCellValue().trim();
                        String phone = phoneCell.getStringCellValue().trim();
                        String academicLevel = academicLevelCell.getStringCellValue().trim();
                        String currentSemester = currentSemesterCell.getStringCellValue().trim();

                        // Add subject to the list
                        studentList.add(new Student(studentid, name, email, address, phone, academicLevel, currentSemester));
                    }
                }
            }
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found or read errors)
            // prints details about an exception (error) that occurred in a program.
            e.printStackTrace();
        }
    }

    public static void readExcelCourse(ObservableList<Course> courses, String filePath) {
        try (
                FileInputStream fis = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fis)
        ) {
            Sheet sheet = workbook.getSheet("Courses");
            int lastRow = findLastNonEmptyRow(sheet); // Get last non-empty row index

            // reading from 1 to avoid reading headers
            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell courseCodeCell = row.getCell(0);
                    Cell nameCell = row.getCell(1);
                    Cell subjectCodeCell = row.getCell(2);
                    Cell sectionCell = row.getCell(3);
                    Cell capacityCell = row.getCell(4);
                    Cell timeCell = row.getCell(5);
                    Cell examTimeCell = row.getCell(6);
                    Cell locationCell = row.getCell(7);
                    Cell teacherCell = row.getCell(8);



                    if (courseCodeCell != null && nameCell != null && subjectCodeCell != null && sectionCell != null && capacityCell != null && timeCell != null && examTimeCell != null && locationCell != null && teacherCell != null) {
                        int courseCode = (int) courseCodeCell.getNumericCellValue(); // Convert numeric to int
                        String name = nameCell.getStringCellValue().trim();
                        String subjectCode = subjectCodeCell.getStringCellValue().trim();
                        String section = sectionCell.getStringCellValue().trim();
                        int capacity = (int) capacityCell.getNumericCellValue(); // Convert numeric to int
                        String time = timeCell.getStringCellValue().trim();
                        String examTime;

                        // Handle date format for Final Exam Date/Time
                        if (examTimeCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(examTimeCell)) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            examTime = dateFormat.format(examTimeCell.getDateCellValue());
                        } else {
                            examTime = examTimeCell.getStringCellValue().trim();
                        }

                        String location = locationCell.getStringCellValue().trim();
                        String teacher = teacherCell.getStringCellValue().trim();

                        // Add subject to the list
                        courses.add(new Course(courseCode, name, subjectCode, section, capacity, time, examTime, location, teacher));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


