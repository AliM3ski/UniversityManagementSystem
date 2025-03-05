package com.example.universitymanagementsystem.FacultyManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class FacultyManagementController {

    @FXML
    private TableView<FacultyManagement> facultyTable;
    @FXML
    private TableColumn<FacultyManagement, String> nameColumn, emailColumn, degreeColumn, researchColumn, coursesColumn, officeColumn, phoneNumberColumn, departmentColumn;
    @FXML
    private TableColumn<FacultyManagement, LocalDate> joinDateColumn;
    @FXML
    private TextField nameInput, emailInput, degreeInput, researchInput, coursesInput, officeInput, phoneNumberInput, departmentInput;
    @FXML
    private DatePicker joinDateInput;

    private final ObservableList<FacultyManagement> facultyList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        facultyTable.setItems(facultyList);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        degreeColumn.setCellValueFactory(cellData -> cellData.getValue().degreeProperty());
        researchColumn.setCellValueFactory(cellData -> cellData.getValue().researchInterestsProperty());
        coursesColumn.setCellValueFactory(cellData -> cellData.getValue().coursesOfferedProperty());
        officeColumn.setCellValueFactory(cellData -> cellData.getValue().officeLocationProperty());
        phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        departmentColumn.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        joinDateColumn.setCellValueFactory(cellData -> cellData.getValue().joinDateProperty());
    }

    @FXML
    private void addFaculty() {
        try {
            String name = nameInput.getText();
            String email = emailInput.getText();
            String degree = degreeInput.getText();
            String research = researchInput.getText();
            String courses = coursesInput.getText();
            String office = officeInput.getText();
            String phone = phoneNumberInput.getText();
            String department = departmentInput.getText();
            LocalDate joinDate = joinDateInput.getValue();

            if (name.isEmpty() || email.isEmpty() || degree.isEmpty() || research.isEmpty() || courses.isEmpty() || office.isEmpty() || phone.isEmpty() || department.isEmpty() || joinDate == null) {
                showAlert("Error", "All fields must be filled out.");
                return;
            }

            FacultyManagement faculty = new FacultyManagement(name, email, degree, research, courses, office, phone, department, joinDate);
            facultyList.add(faculty);

            clearFields();
        } catch (Exception e) {
            showAlert("Error", "Failed to add faculty. Please check your inputs.");
        }
    }

    private void clearFields() {
        nameInput.clear();
        emailInput.clear();
        degreeInput.clear();
        researchInput.clear();
        coursesInput.clear();
        officeInput.clear();
        phoneNumberInput.clear();
        departmentInput.clear();
        joinDateInput.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            // Load the previous scene (Replace "MainMenu.fxml" with your actual main scene FXML)
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/universitymanagementsystem/DashBoard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
