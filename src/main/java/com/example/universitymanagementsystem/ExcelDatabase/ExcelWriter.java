package com.example.universitymanagementsystem.ExcelDatabase;

import com.example.universitymanagementsystem.CourseManagement.Course;
import com.example.universitymanagementsystem.FacultyManagement.FacultyManagement;
import com.example.universitymanagementsystem.SubjectManagement.Subject;
import com.example.universitymanagementsystem.Users.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.Random;

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


    public static void writeToExcelStudent(ObservableList<Student> studentlist, String filePath, String studentIdNumber) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            // Get the sheet where subjects are stored
            Sheet sheet = workbook.getSheet("Student");
            // variable to store number of filled rows
            int rowNum = sheet.getPhysicalNumberOfRows();
            // variable to store position of the last element of the sheet
            int lastElement = studentlist.size() - 1;
            // gets the last element of the sheet
            Student student = studentlist.get(lastElement);
            // creates a new row
            Row row = sheet.createRow(rowNum++);
            // adds the new subject to the new row
            row.createCell(0).setCellValue(studentIdNumber);
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getAddress());
            row.createCell(3).setCellValue(student.getPhone());
            row.createCell(4).setCellValue(student.getEmail());
            row.createCell(5).setCellValue(student.getAcademicLevel());
            row.createCell(6).setCellValue(student.getCurrentSemester());
            row.createCell(7).setCellValue(student.getPhotoPath());
            row.createCell(8).setCellValue(student.getSubjectRegistered());
            row.createCell(9).setCellValue(student.getThesisTitle());
            row.createCell(10).setCellValue(student.getProgress());
            row.createCell(11).setCellValue(student.getPassword());


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

    // ** Edit student in Excel **
    public static void editStudentInExcel(String filePath,Student selectedStudent,  String oldStudentID,  String oldName, String oldAddress, String oldPhone, String oldEmail, String oldAcademicLevel, String oldPhotoPath, String oldSubjectRegistered, String oldTheisTitle, String oldProgress, String oldpassword ) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Student");
            // Loop through each row to find the row with the given student code
            for (Row row : sheet) {
                Cell[] cells = new Cell[12];
                for (int i = 0; i < 12; i++) {
                    cells[i] = row.getCell(i);
                }

                if (cells[0] != null && cells[0].getStringCellValue().equals(oldStudentID)) {
                    cells[0].setCellValue(selectedStudent.getStudentId());
                }
                if (cells[1] != null && cells[1].getStringCellValue().equals(oldName)) {
                    cells[1].setCellValue(selectedStudent.getName());
                }
                if (cells[2] != null && cells[2].getStringCellValue().equals(oldAddress)) {
                    cells[2].setCellValue(selectedStudent.getAddress());
                }
                if (cells[3] != null && cells[3].getStringCellValue().equals(oldEmail)) {
                    cells[3].setCellValue(selectedStudent.getEmail());
                }
                if (cells[4] != null && cells[4].getStringCellValue().equals(oldPhone)) {
                    cells[4].setCellValue(selectedStudent.getPhone());
                }
                if (cells[5] != null && cells[5].getStringCellValue().equals(oldAcademicLevel)) {
                    cells[5].setCellValue(selectedStudent.getAcademicLevel());
                }
                if (cells[7] != null && cells[7].getStringCellValue().equals(oldPhotoPath)) {
                    cells[7].setCellValue(selectedStudent.getPhotoPath());
                }
                if (cells[8] != null && cells[8].getStringCellValue().equals(oldSubjectRegistered)) {
                    cells[8].setCellValue(selectedStudent.getSubjectRegistered());
                }
                if (cells[9] != null && cells[9].getStringCellValue().equals(oldTheisTitle)) {
                    cells[9].setCellValue(selectedStudent.getThesisTitle());
                }
                if (cells[10] != null && cells[10].getStringCellValue().equals(oldProgress)) {
                    cells[10].setCellValue(selectedStudent.getProgress());
                }
                if (cells[11] != null && cells[11].getStringCellValue().equals(oldpassword)) {
                    cells[11].setCellValue(selectedStudent.getPassword());
                }
            }


            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudentFromExcel(String filePath, String studentCode) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Student");
            // Variable to store the index of the row to delete
            int rowIndex = -1;

            // Loop through each row to find the row with the given subject code
            for (Row row : sheet) {
                // get column of the subject codes
                Cell IDCell = row.getCell(0);
                if (IDCell != null && IDCell.getStringCellValue().equals(studentCode)) {
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
    public static void updateStudentPhoto(String studentId, String photoPath) {
        String filePath = "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx";

        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Student");

            for (Row row : sheet) {
                Cell idCell = row.getCell(0);
                if (idCell != null && idCell.getStringCellValue().equals(studentId)) {
                    Cell photoCell = row.getCell(7);
                    if (photoCell == null) {
                        photoCell = row.createCell(7);
                    }
                    photoCell.setCellValue(photoPath);
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
    public static void writeToExcelCourse(ObservableList<Course> courses, String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            // Get the sheet where subjects are stored
            Sheet sheet = workbook.getSheet("Courses");
            // variable to store number of filled rows
            int rowNum = sheet.getPhysicalNumberOfRows();
            // variable to store position of the last element of the sheet
            int lastElement = courses.size() - 1;
            // gets the last element of the sheet
            Course course = courses.get(lastElement);
            // creates a new row
            Row row = sheet.createRow(rowNum++);
            // adds the new subject to the new row
            row.createCell(0).setCellValue(course.getCourseCode());
            row.createCell(1).setCellValue(course.getName());
            row.createCell(2).setCellValue(course.getSubject());
            row.createCell(3).setCellValue(course.getSectionNumber());
            row.createCell(4).setCellValue(course.getCapacity());
            row.createCell(5).setCellValue(course.getSchedule());
            row.createCell(6).setCellValue(course.getFinalExamDateTime());
            row.createCell(7).setCellValue(course.getLocation());
            row.createCell(8).setCellValue(course.getFaculty());

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
    public static void editCourseInExcel(String filePath, Course selectedCourse, int oldCourseCode, String oldCourseName, String oldSubjectCode, String oldSectionNumber, int oldCapacity, String oldSchedule, String oldLocation, String oldTeacherName) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Courses");

            for (Row row : sheet) {
                Cell[] cells = new Cell[9]; // Only 9 columns expected
                for (int i = 0; i < 9; i++) {
                    cells[i] = row.getCell(i);
                }

                // Check each cell and update if it matches the old values
                if (cells[0] != null && cells[0].getCellType() == CellType.NUMERIC &&
                        cells[0].getNumericCellValue() == oldCourseCode) {
                    cells[0].setCellValue(selectedCourse.getCourseCode());
                }
                if (cells[1] != null && cells[1].getCellType() == CellType.STRING &&
                        cells[1].getStringCellValue().equals(oldCourseName)) {
                    cells[1].setCellValue(selectedCourse.getName());
                }
                if (cells[2] != null && cells[2].getCellType() == CellType.STRING &&
                        cells[2].getStringCellValue().equals(oldSubjectCode)) {
                    cells[2].setCellValue(selectedCourse.getSubject());
                }
                if (cells[3] != null && cells[3].getCellType() == CellType.STRING &&
                        cells[3].getStringCellValue().equals(oldSectionNumber)) {
                    cells[3].setCellValue(selectedCourse.getSectionNumber());
                }
                if (cells[4] != null && cells[4].getCellType() == CellType.NUMERIC &&
                        (int) cells[4].getNumericCellValue() == oldCapacity) {
                    cells[4].setCellValue(selectedCourse.getCapacity());
                }
                if (cells[5] != null && cells[5].getCellType() == CellType.STRING &&
                        cells[5].getStringCellValue().equals(oldSchedule)) {
                    cells[5].setCellValue(selectedCourse.getSchedule());
                }
                if (cells[6] != null && cells[6].getCellType() == CellType.STRING &&
                        cells[6].getStringCellValue().equals(oldLocation)) {
                    cells[6].setCellValue(selectedCourse.getLocation());
                }
                if (cells[7] != null && cells[7].getCellType() == CellType.STRING &&
                        cells[7].getStringCellValue().equals(oldTeacherName)) {
                    cells[7].setCellValue(selectedCourse.getFaculty());
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCourseFromExcel(String filePath, double courseCode) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Courses");
            // Variable to store the index of the row to delete
            int rowIndex = -1;

            for (Row row : sheet) {
                if(row.getRowNum() == 0){
                    continue;
                }
                Cell IDCell = row.getCell(0);
                if (IDCell != null && IDCell.getNumericCellValue() == courseCode) {
                    rowIndex = row.getRowNum();
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

    public static void writeToExcelFaculty(ObservableList<FacultyManagement> facultyList, String filePath, String facultyIdNumber) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            // Get the faculty sheet (assuming it's named "Faculty" or at index 3)
            Sheet sheet = workbook.getSheetAt(3); // Or workbook.getSheet("Faculty")

            // Get the last row number (adds after existing data)
            int rowNum = sheet.getLastRowNum() + 1;

            // Get the last faculty member from the list
            FacultyManagement faculty = facultyList.get(facultyList.size() - 1);

            // Create new row and populate cells
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(facultyIdNumber); // Column A: Faculty ID
            row.createCell(1).setCellValue(faculty.getName()); // Column B: Name
            row.createCell(2).setCellValue(faculty.getDegree()); // Column C: Degree
            row.createCell(3).setCellValue(faculty.getResearchInterests()); // Column D: Research
            row.createCell(4).setCellValue(faculty.getEmail()); // Column E: Email
            row.createCell(5).setCellValue(faculty.getOfficeLocation()); // Column F: Office
            row.createCell(6).setCellValue(faculty.getCoursesOffered()); // Column G: Courses
            row.createCell(7).setCellValue(faculty.getPassword()); // Column H: Password

            // Auto-size columns for better visibility
            for (int i = 0; i < 8; i++) { // Adjust for all 8 columns
                sheet.autoSizeColumn(i);
            }

            // Save changes to the file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editFacultyInExcel(String filePath, FacultyManagement selectedFaculty,
                                          String oldFacultyID, String oldName, String oldDegree, String oldResearch,
                                          String oldEmail, String oldOffice, String oldPassword) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Faculty");

            // Loop through each row to find the matching faculty
            for (Row row : sheet) {
                Cell[] cells = new Cell[8]; // Faculty has 8 columns
                for (int i = 0; i < 8; i++) {
                    cells[i] = row.getCell(i);
                }

                // Check if this is the row we want to edit
                if (cells[0] != null && cells[0].getStringCellValue().equals(oldFacultyID)) {
                    // Update all fields that might have changed
                    cells[0].setCellValue(selectedFaculty.getFacultyId());
                    cells[1].setCellValue(selectedFaculty.getName());
                    cells[2].setCellValue(selectedFaculty.getDegree());
                    cells[3].setCellValue(selectedFaculty.getResearchInterests());
                    cells[4].setCellValue(selectedFaculty.getEmail());
                    cells[5].setCellValue(selectedFaculty.getOfficeLocation());
                    cells[6].setCellValue(selectedFaculty.getCoursesOffered());
                    cells[7].setCellValue(selectedFaculty.getPassword());
                    break; // Found and updated our faculty, exit loop
                }
            }

            // Save changes
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFacultyFromExcel(String filePath, String facultyID) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Faculty");
            int rowIndex = -1;

            // Find the row to delete
            for (Row row : sheet) {
                Cell idCell = row.getCell(0);
                if (idCell != null && idCell.getStringCellValue().equals(facultyID)) {
                    rowIndex = row.getRowNum();
                    break;
                }
            }

            // Delete the row if found
            if (rowIndex != -1) {
                int lastRowNum = sheet.getLastRowNum();
                sheet.removeRow(sheet.getRow(rowIndex));

                // Shift rows up if needed
                if (rowIndex < lastRowNum) {
                    sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
                }
            }

            // Save changes
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateFacultyPhoto(String facultyId, String photoPath) {
        String filePath = "src/main/java/com/example/universitymanagementsystem/ExcelDatabase/UMS_Data.xlsx";

        try (FileInputStream fileIn = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheet("Faculty");

            for (Row row : sheet) {
                Cell idCell = row.getCell(0);
                if (idCell != null && idCell.getStringCellValue().equals(facultyId)) {
                    // Assuming photo path is stored in column 8 (adjust if needed)
                    Cell photoCell = row.getCell(8);
                    if (photoCell == null) {
                        photoCell = row.createCell(8);
                    }
                    photoCell.setCellValue(photoPath);
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

}

