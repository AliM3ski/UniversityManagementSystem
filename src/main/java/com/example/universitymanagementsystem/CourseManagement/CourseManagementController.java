package com.example.universitymanagementsystem.CourseManagement;

import com.example.universitymanagementsystem.ExcelDatabase.ExcelWriter;
import com.example.universitymanagementsystem.moveBetweenInterfaces;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelReader;
import com.example.universitymanagementsystem.Users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CourseManagementController {

    private User user;

    @FXML
    public AnchorPane contentPane;
    @FXML
    private Button addCourseButton;

    @FXML
    private Button editCourseButton;

    @FXML
    private Button deleteCourseButton;

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> nameColumn;

    @FXML
    private TableColumn<Course, String> subjectColumn;

    @FXML
    private TableColumn<Course, String> scheduleColumn;

    @FXML
    private TableColumn<Course, Integer> capacityColumn;

    @FXML
    private TableColumn<Course, String> facultyColumn;

    @FXML
    private TableColumn<Course, Integer> courseCodeColumn;

    @FXML
    private TableColumn<Course, String> sectionNumberColumn;

    @FXML
    private TableColumn<Course, String> finalExamDateTimeColumn;

    @FXML
    private TableColumn<Course, String> locationColumn;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField subjectInput;

    @FXML
    private TextField scheduleInput;

    @FXML
    private TextField capacityInput;

    @FXML
    private TextField facultyInput;

    @FXML
    private TextField courseCodeInput;

    @FXML
    private TextField sectionNumberInput;

    @FXML
    private TextField finalExamDateTimeInput;

    @FXML
    private TextField locationInput;

    @FXML
    private TextField searchInput;

    public ObservableList<Course> courses = FXCollections.observableArrayList();

    // Method to set the user object
    public void setUser(User user) {
        this.user = user;
        configureCourseManagement(); // Configure UI based on user type
    }

    private void configureCourseManagement() {
        // Configure UI based on user type
        switch (user.getUserType()) {
            case "Admin":
                System.out.println("Admin can manage all courses.");
                addCourseButton.setDisable(false);
                editCourseButton.setDisable(false);
                deleteCourseButton.setDisable(false);
                addCourseButton.setVisible(true);
                editCourseButton.setVisible(true);
                deleteCourseButton.setVisible(true);
                enableInputFields(true);
                break;
            case "Faculty":
                System.out.println("Faculty can manage their courses.");
                addCourseButton.setDisable(true);
                editCourseButton.setDisable(false);
                deleteCourseButton.setDisable(true);
                addCourseButton.setVisible(false);
                editCourseButton.setVisible(true);
                deleteCourseButton.setVisible(false);
                enableInputFields(false);
                break;
            case "Student":
                System.out.println("Student can view courses.");
                addCourseButton.setDisable(true);
                editCourseButton.setDisable(true);
                deleteCourseButton.setDisable(true);
                addCourseButton.setVisible(false);
                editCourseButton.setVisible(false);
                deleteCourseButton.setVisible(false);
                enableInputFields(false);
                break;
        }
    }

    private void enableInputFields(boolean enable) {
        nameInput.setDisable(!enable);
        subjectInput.setDisable(!enable);
        scheduleInput.setDisable(!enable);
        capacityInput.setDisable(!enable);
        facultyInput.setDisable(!enable);
        courseCodeInput.setDisable(!enable);
        sectionNumberInput.setDisable(!enable);
        finalExamDateTimeInput.setDisable(!enable);
        locationInput.setDisable(!enable);
        nameInput.setVisible(enable);
        subjectInput.setVisible(enable);
        scheduleInput.setVisible(enable);
        capacityInput.setVisible(enable);
        facultyInput.setVisible(enable);
        courseCodeInput.setVisible(enable);
        sectionNumberInput.setVisible(enable);
        finalExamDateTimeInput.setVisible(enable);
        locationInput.setVisible(enable);
    }


    @FXML
    public void initialize() {
        // Initialize table columns
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        courseCodeColumn.setCellValueFactory(cellData -> cellData.getValue().courseCodeProperty().asObject());
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        sectionNumberColumn.setCellValueFactory(cellData -> cellData.getValue().sectionNumberProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());
        scheduleColumn.setCellValueFactory(cellData -> cellData.getValue().scheduleProperty());
        finalExamDateTimeColumn.setCellValueFactory(cellData -> cellData.getValue().finalExamDateTimeProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        facultyColumn.setCellValueFactory(cellData -> cellData.getValue().facultyProperty());


        // Set table data
        loadCourse();
    }

    // Load courses from the Excel database
    private void loadCourse() {
        String filePath = "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx";
        ExcelReader.readExcelCourse(courses, filePath);
        courseTable.setItems(courses);
    }

    @FXML
    private void addCourse() {
        String name = nameInput.getText().trim();
        String subject = subjectInput.getText().trim();
        String schedule = scheduleInput.getText().trim();
        String faculty = facultyInput.getText().trim();
        int capacity;
        int courseCode;
        String sectionNumber = sectionNumberInput.getText().trim();
        String finalExamDateTime = finalExamDateTimeInput.getText().trim();
        String location = locationInput.getText().trim();

        try {
            capacity = Integer.parseInt(capacityInput.getText().trim());
            courseCode = Integer.parseInt(courseCodeInput.getText().trim()); // Validate course code
        } catch (NumberFormatException e) {
            showAlert("Error", "Capacity and Course Code must be numbers.");
            return;
        }

        if (name.isEmpty() || subject.isEmpty() || schedule.isEmpty() || faculty.isEmpty() ||
                sectionNumber.isEmpty() || finalExamDateTime.isEmpty() || location.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        courses.add(new Course(courseCode, name, subject, sectionNumber, capacity, schedule, finalExamDateTime, location, faculty));
        // Update the Excel file
        ExcelWriter.writeToExcelCourse(courses, "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx");

        clearInputs();
    }

    @FXML
    private void editCourse() {
        Course selected = courseTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "No course selected.");
            return;
        }
        // Store old values
        int oldCourseCode= selected.getCourseCode();
        String oldCourseName = selected.getName();
        String oldSubjectCode = selected.getSubject();
        String oldSectionNumber = selected.getSectionNumber();
        int oldCapacity = selected.getCapacity();
        String oldSchedule= selected.getSchedule();
        String oldLocation = selected.getLocation();
        String oldTeacherName = selected.getFaculty();


        selected.setName(nameInput.getText().trim());
        selected.setSubject(subjectInput.getText().trim());
        selected.setSchedule(scheduleInput.getText().trim());
        selected.setFaculty(facultyInput.getText().trim());

        try {
            selected.setCapacity(Integer.parseInt(capacityInput.getText().trim()));
            selected.setCourseCode(Integer.parseInt(courseCodeInput.getText().trim())); // Update course code
        } catch (NumberFormatException e) {
            showAlert("Error", "Capacity and Course Code must be numbers.");
            return;
        }

        selected.setSectionNumber(sectionNumberInput.getText().trim()); // Update section number
        selected.setFinalExamDateTime(finalExamDateTimeInput.getText().trim()); // Update final exam date/time
        selected.setLocation(locationInput.getText().trim()); // Update location

        ExcelWriter.editCourseInExcel("src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx",selected, oldCourseCode,oldCourseName,oldSubjectCode,oldSectionNumber, oldCapacity, oldSchedule, oldLocation, oldTeacherName);

            courseTable.refresh();
    }

    @FXML
    private void deleteCourse() {
        Course selected = courseTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            courses.remove(selected);
            ExcelWriter.deleteCourseFromExcel("src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx", selected.getCourseCode());

        } else {
            showAlert("Error", "No course selected.");
        }
    }

    @FXML
    private void searchCourse() {
        String name = searchInput.getText().trim();
        for (Course c : courses) {
            if (c.getName().equalsIgnoreCase(name)) {
                courseTable.getSelectionModel().select(c);
                return;
            }
        }
        showAlert("Not Found", "No course found with this name.");
    }

    private void clearInputs() {
        nameInput.clear();
        subjectInput.clear();
        scheduleInput.clear();
        capacityInput.clear();
        facultyInput.clear();
        courseCodeInput.clear(); // Clear new field
        sectionNumberInput.clear(); // Clear new field
        finalExamDateTimeInput.clear(); // Clear new field
        locationInput.clear(); // Clear new field
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}