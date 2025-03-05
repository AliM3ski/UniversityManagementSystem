package com.example.universitymanagementsystem.CourseManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseManagementController {

    private boolean isAdmin;

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
    private TableColumn<Course, Integer> courseCodeColumn; // New column

    @FXML
    private TableColumn<Course, String> sectionNumberColumn; // New column

    @FXML
    private TableColumn<Course, String> finalExamDateTimeColumn; // New column

    @FXML
    private TableColumn<Course, String> locationColumn; // New column

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
    private TextField courseCodeInput; // New input field

    @FXML
    private TextField sectionNumberInput; // New input field

    @FXML
    private TextField finalExamDateTimeInput; // New input field

    @FXML
    private TextField locationInput; // New input field

    @FXML
    private TextField searchInput;

    private ObservableList<Course> courses = FXCollections.observableArrayList();

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        checkAdminStatus(); // Update button visibility based on admin status
    }

    private void checkAdminStatus() {
        if (isAdmin) {
            System.out.println("User is an admin.");
            // Enable or show buttons for admins
            addCourseButton.setDisable(false);
            editCourseButton.setDisable(false);
            deleteCourseButton.setDisable(false);
            addCourseButton.setVisible(true);
            editCourseButton.setVisible(true);
            deleteCourseButton.setVisible(true);

            // Enable or show text fields for admins
            nameInput.setDisable(false);
            subjectInput.setDisable(false);
            scheduleInput.setDisable(false);
            capacityInput.setDisable(false);
            facultyInput.setDisable(false);
            courseCodeInput.setDisable(false); // New field
            sectionNumberInput.setDisable(false); // New field
            finalExamDateTimeInput.setDisable(false); // New field
            locationInput.setDisable(false); // New field
            nameInput.setVisible(true);
            subjectInput.setVisible(true);
            scheduleInput.setVisible(true);
            capacityInput.setVisible(true);
            facultyInput.setVisible(true);
            courseCodeInput.setVisible(true); // New field
            sectionNumberInput.setVisible(true); // New field
            finalExamDateTimeInput.setVisible(true); // New field
            locationInput.setVisible(true); // New field
        } else {
            System.out.println("User is not an admin.");
            // Disable or hide buttons for non-admins
            addCourseButton.setDisable(true);
            editCourseButton.setDisable(true);
            deleteCourseButton.setDisable(true);
            addCourseButton.setVisible(false);
            editCourseButton.setVisible(false);
            deleteCourseButton.setVisible(false);

            // Disable or hide text fields for non-admins
            nameInput.setDisable(true);
            subjectInput.setDisable(true);
            scheduleInput.setDisable(true);
            capacityInput.setDisable(true);
            facultyInput.setDisable(true);
            courseCodeInput.setDisable(true); // New field
            sectionNumberInput.setDisable(true); // New field
            finalExamDateTimeInput.setDisable(true); // New field
            locationInput.setDisable(true); // New field
            nameInput.setVisible(false);
            subjectInput.setVisible(false);
            scheduleInput.setVisible(false);
            capacityInput.setVisible(false);
            facultyInput.setVisible(false);
            courseCodeInput.setVisible(false); // New field
            sectionNumberInput.setVisible(false); // New field
            finalExamDateTimeInput.setVisible(false); // New field
            locationInput.setVisible(false); // New field
        }
    }

    @FXML
    public void initialize() {
        // Initialize table columns
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        scheduleColumn.setCellValueFactory(cellData -> cellData.getValue().scheduleProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());
        facultyColumn.setCellValueFactory(cellData -> cellData.getValue().facultyProperty());
        courseCodeColumn.setCellValueFactory(cellData -> cellData.getValue().courseCodeProperty().asObject()); // New column
        sectionNumberColumn.setCellValueFactory(cellData -> cellData.getValue().sectionNumberProperty()); // New column
        finalExamDateTimeColumn.setCellValueFactory(cellData -> cellData.getValue().finalExamDateTimeProperty()); // New column
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty()); // New column

        // Set table data
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

        courses.add(new Course(name, subject, schedule, capacity, faculty, courseCode, sectionNumber, finalExamDateTime, location));
        clearInputs();
    }

    @FXML
    private void editCourse() {
        Course selected = courseTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "No course selected.");
            return;
        }

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

        courseTable.refresh();
    }

    @FXML
    private void deleteCourse() {
        Course selected = courseTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            courses.remove(selected);
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

    @FXML
    public void backToDashBoard(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));

        Parent root = loader.load();

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }
}