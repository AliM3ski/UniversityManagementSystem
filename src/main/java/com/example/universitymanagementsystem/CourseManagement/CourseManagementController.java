package com.example.universitymanagementsystem.CourseManagement;

import com.example.universitymanagementsystem.Users.User;
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

    private User user;

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

    private ObservableList<Course> courses = FXCollections.observableArrayList();

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
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        scheduleColumn.setCellValueFactory(cellData -> cellData.getValue().scheduleProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());
        facultyColumn.setCellValueFactory(cellData -> cellData.getValue().facultyProperty());
        courseCodeColumn.setCellValueFactory(cellData -> cellData.getValue().courseCodeProperty().asObject());
        sectionNumberColumn.setCellValueFactory(cellData -> cellData.getValue().sectionNumberProperty());
        finalExamDateTimeColumn.setCellValueFactory(cellData -> cellData.getValue().finalExamDateTimeProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

        // Set table data
        courseTable.setItems(courses);
    }

    @FXML
    private void addCourse() {
        // Add course logic
    }

    @FXML
    private void editCourse() {
        // Edit course logic
    }

    @FXML
    private void deleteCourse() {
        // Delete course logic
    }

    @FXML
    private void searchCourse() {
        // Search course logic
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