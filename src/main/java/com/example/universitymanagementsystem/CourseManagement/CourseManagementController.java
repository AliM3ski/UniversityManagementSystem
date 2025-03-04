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
    private TextField searchInput;

    private ObservableList<Course> courses = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        scheduleColumn.setCellValueFactory(cellData -> cellData.getValue().scheduleProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty().asObject());
        facultyColumn.setCellValueFactory(cellData -> cellData.getValue().facultyProperty());

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

        try {
            capacity = Integer.parseInt(capacityInput.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Error", "Capacity must be a number.");
            return;
        }

        if (name.isEmpty() || subject.isEmpty() || schedule.isEmpty() || faculty.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        courses.add(new Course(name, subject, schedule, capacity, faculty));
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
        } catch (NumberFormatException e) {
            showAlert("Error", "Capacity must be a number.");
            return;
        }

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
