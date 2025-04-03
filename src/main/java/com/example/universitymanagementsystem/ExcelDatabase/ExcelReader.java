package com.example.universitymanagementsystem.ExcelDatabase;

import com.example.universitymanagementsystem.CourseManagement.Course;
import com.example.universitymanagementsystem.EventManagement.Event;
import com.example.universitymanagementsystem.FacultyManagement.FacultyManagement;
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
                    Cell photoPathCell = row.getCell(7);
                    Cell subjectRegisteredCell = row.getCell(8);
                    Cell thesisTitleCell = row.getCell(9);
                    Cell progressCell = row.getCell(10);
                    Cell passwordCell = row.getCell(11);

                    if (studentIdCell != null && nameCell != null && addressCell != null && phoneCell != null && emailCell != null && academicLevelCell != null && currentSemesterCell != null && photoPathCell != null && subjectRegisteredCell != null && thesisTitleCell != null && progressCell != null && passwordCell != null) {
                        String studentid = studentIdCell.getStringCellValue().trim();
                        String name = nameCell.getStringCellValue().trim();
                        String email = emailCell.getStringCellValue().trim();
                        String address = addressCell.getStringCellValue().trim();
                        String phone = phoneCell.getStringCellValue().trim();
                        String academicLevel = academicLevelCell.getStringCellValue().trim();
                        String currentSemester = currentSemesterCell.getStringCellValue().trim();
                        String photoPath = photoPathCell.getStringCellValue().trim();
                        String subjectRegistered = subjectRegisteredCell.getStringCellValue().trim();
                        String thesisTitle = thesisTitleCell.getStringCellValue().trim();
                        String progress = progressCell.getStringCellValue().trim();
                        String password= passwordCell.getStringCellValue().trim();




                        // Add subject to the list
                        studentList.add(new Student(studentid, name, email, address, phone, academicLevel, currentSemester, photoPath, subjectRegistered, thesisTitle, progress, password));
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
    public static void readExcelEvent(ObservableList<Event> events, String filePath) {
        try (
                FileInputStream fis = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fis)
        ) {
            Sheet sheet = workbook.getSheet("Events");
            int lastRow = findLastNonEmptyRow(sheet); // Get last non-empty row index

            // Reading from row index 1 to skip headers
            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell eventCodeCell = row.getCell(0);
                    Cell eventNameCell = row.getCell(1);
                    Cell descriptionCell = row.getCell(2);
                    Cell locationCell = row.getCell(3);
                    Cell dateTimeCell = row.getCell(4);
                    Cell capacityCell = row.getCell(5);
                    Cell costCell = row.getCell(6);
                    Cell headerImageCell = row.getCell(7);
                    Cell registeredStudentsCell = row.getCell(8);

                    // Ensure all required cells are present
                    if (eventCodeCell != null && eventNameCell != null && descriptionCell != null &&
                            locationCell != null && dateTimeCell != null && capacityCell != null &&
                            costCell != null && headerImageCell != null && registeredStudentsCell != null) {

                        String eventCode = eventCodeCell.getStringCellValue().trim();
                        String eventName = eventNameCell.getStringCellValue().trim();
                        String description = descriptionCell.getStringCellValue().trim();
                        String location = locationCell.getStringCellValue().trim();
                        String dateTime;

                        // Handle date format for event date/time
                        if (dateTimeCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateTimeCell)) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            dateTime = dateFormat.format(dateTimeCell.getDateCellValue());
                        } else {
                            dateTime = dateTimeCell.getStringCellValue().trim();
                        }

                        int capacity = (int) capacityCell.getNumericCellValue(); // Convert numeric to int
                        String cost = costCell.getStringCellValue().trim();
                        String headerImages = headerImageCell.getStringCellValue().trim();
                        String registeredStudents = registeredStudentsCell.getStringCellValue().trim();

                        // Add event to the list
                        events.add(new Event(eventCode, eventName, description, location, dateTime,
                                String.valueOf(capacity), cost, headerImages, registeredStudents));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean readExcelStudentIDs(String filePath, String username, String password) {
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
                    Cell passwordCell = row.getCell(11);

                    if (studentIdCell != null) {
                        String studentid = studentIdCell.getStringCellValue().trim();
                        String passwordid = passwordCell.getStringCellValue().trim();

                        if(studentid.equals(username) && passwordid.equals(password)){
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found or read errors)
            // prints details about an exception (error) that occurred in a program.
            e.printStackTrace();
        }
        return false;
    }

    public static boolean readExcelFaculty(String filePath, String username, String password) {
        try (
                // Open the file as an input stream
                FileInputStream fis = new FileInputStream(filePath);
                // Create a workbook object to access the Excel file. Workbook representing an entire Excel ile
                Workbook workbook = new XSSFWorkbook(fis)
        ) {
            // Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(3);

            int lastRow = findLastNonEmptyRow(sheet); // Get last non-empty row index

            // Loop through each row in the sheet
            for (int i = 0; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                // Loop through each cell in the row
                if (row != null) {
                    Cell facultyIdCell = row.getCell(0);
                    Cell passwordCell = row.getCell(7);

                    if (facultyIdCell != null) {
                        String facultyid = facultyIdCell.getStringCellValue().trim();
                        String passwordid = passwordCell.getStringCellValue().trim();

                        if(facultyid.equals(username) && passwordid.equals(password)){
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found or read errors)
            // prints details about an exception (error) that occurred in a program.
            e.printStackTrace();
        }
        return false;
    }

    public static void readExcelFaculty(ObservableList<FacultyManagement> facultyList, String filePath) {
        try (
                FileInputStream fis = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fis)
        ) {
            // Get the faculty sheet (page 4 in Excel)
            Sheet sheet = workbook.getSheetAt(3); // 0-based index (0=first sheet, 3=fourth sheet)

            int lastRow = findLastNonEmptyRow(sheet);

            // Start from row 1 to skip header row (row 0)
            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell facultyIdCell = row.getCell(0);  // Column A
                    Cell nameCell = row.getCell(1);      // Column B
                    Cell degreeCell = row.getCell(2);     // Column C
                    Cell researchCell = row.getCell(3);   // Column D
                    Cell emailCell = row.getCell(4);      // Column E
                    Cell officeCell = row.getCell(5);     // Column F
                    Cell coursesCell = row.getCell(6);    // Column G
                    Cell passwordCell = row.getCell(7);   // Column H

                    // Check required cells (facultyId and name are mandatory)
                    if (facultyIdCell != null && nameCell != null) {
                        String facultyId = getCellStringValue(facultyIdCell).trim();
                        String name = getCellStringValue(nameCell).trim();
                        String degree = degreeCell != null ? getCellStringValue(degreeCell).trim() : "";
                        String research = researchCell != null ? getCellStringValue(researchCell).trim() : "";
                        String email = emailCell != null ? getCellStringValue(emailCell).trim() : "";
                        String office = officeCell != null ? getCellStringValue(officeCell).trim() : "";
                        String courses = coursesCell != null ? getCellStringValue(coursesCell).trim() : "";
                        String password = passwordCell != null ? getCellStringValue(passwordCell).trim() : "default123";

                        // Add faculty to the list
                        facultyList.add(new FacultyManagement(
                                facultyId,
                                name,
                                degree,
                                research,
                                email,
                                office,
                                password
                        ));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to safely get string value from any cell type
    private static String getCellStringValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int)cell.getNumericCellValue()); // For IDs
            default:
                return "";
        }
    }

    // Method to find the last non-empty row (same as your existing implementation)
    private static int findLastNonEmptyRow1(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        for (int i = lastRowNum; i >= 0; i--) {
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null && cell.getCellType() != CellType.BLANK) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }

}


