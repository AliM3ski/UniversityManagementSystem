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
import javafx.scene.input.MouseEvent;

public class FacultyManagementController {

    private boolean isAdmin;

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
    @FXML
    private Button add;

    private final ObservableList<FacultyManagement> facultyList = FXCollections.observableArrayList();

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        checkAdminStatus(); // Update button visibility based on admin status
    }

    private void checkAdminStatus() {
        if (isAdmin) {
            System.out.println("User is an admin.");
            // Enable or show buttons for admins


            // Enable or show text fields for admins
            nameInput.setDisable(false);
            emailInput.setDisable(false);
            degreeInput.setDisable(false);
            researchInput.setDisable(false);
            coursesInput.setDisable(false);
            officeInput.setDisable(false);
            phoneNumberInput.setDisable(false);
            departmentInput.setDisable(false);
            joinDateInput.setDisable(false);
            add.setDisable(false);


            nameInput.setVisible(true);
            emailInput.setVisible(true);
            degreeInput.setVisible(true);
            researchInput.setVisible(true);
            coursesInput.setVisible(true);
            officeInput.setVisible(true);
            phoneNumberInput.setVisible(true);
            departmentInput.setVisible(true);
            joinDateInput.setVisible(true);
            add.setVisible(true);


        } else {
            System.out.println("User is not an admin.");
            // Disable or hide buttons for non-admins


            // Disable or hide text fields for non-admins
            nameInput.setDisable(true);
            emailInput.setDisable(true);
            degreeInput.setDisable(true);
            researchInput.setDisable(true);
            coursesInput.setDisable(true);
            officeInput.setDisable(true);
            phoneNumberInput.setDisable(true);
            departmentInput.setDisable(true);
            joinDateInput.setDisable(true);
            add.setDisable(true);


            nameInput.setVisible(false);
            emailInput.setVisible(false);
            degreeInput.setVisible(false);
            researchInput.setVisible(false);
            coursesInput.setVisible(false);
            officeInput.setVisible(false);
            phoneNumberInput.setVisible(false);
            departmentInput.setVisible(false);
            joinDateInput.setVisible(false);
            add.setVisible(false);

        }
    }


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
