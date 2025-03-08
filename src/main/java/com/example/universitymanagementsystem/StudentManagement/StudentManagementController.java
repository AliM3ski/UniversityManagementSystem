package com.example.universitymanagementsystem.StudentManagement;

import com.example.universitymanagementsystem.DashBoard.DashBoardController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentManagementController {


    // Table and TableColumn for displaying student data
    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn, nameColumn, emailColumn, addressColumn;

    @FXML
    private TableColumn<Student, String> phoneColumn;
    @FXML
    private TableColumn<Student, String> levelColumn;
    @FXML
    private TableColumn<Student, String> semesterColumn;

    // Input fields for adding or editing student info
    @FXML
    private TextField studentIdField, nameField, emailField, addressField, phoneField;

    @FXML
    private ComboBox<String> academicLevelBox;

    //Variable for the button in the FXML
    @FXML
    private Button addStudentbutton, editStudentbutton, deleteStudentbutton;

    @FXML
    private Button viewProfileButton, manageEnrollmentsButton, manageTuitionButton;

    //Variable for the Labels in the FXML
    @FXML
    private Label studentIdLabel, nameIdLabel, emailLabel, addressLabel, phoneLabel, academicLevelLabel;


    private boolean isAdmin; // Added missing variable

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        checkAdminStatus(); // Update button visibility based on admin status
    }

    // Hides add, edit, and delete buttons when logged in as a regular user
    private void checkAdminStatus() {
        if (!isAdmin) {
            System.out.println("User is not an admin.");
            studentIdField.setDisable(true);
            nameField.setDisable(true);
            emailField.setDisable(true);
            academicLevelBox.setDisable(true);
            addressField.setDisable(true);
            phoneField.setDisable(true);
            studentIdField.setVisible(false);
            nameField.setVisible(false);
            emailField.setVisible(false);
            academicLevelBox.setVisible(false);
            addressField.setVisible(false);
            phoneField.setVisible(false);
            addStudentbutton.setVisible(false);
            editStudentbutton.setVisible(false);
            deleteStudentbutton.setVisible(false);
            manageTuitionButton.setVisible(false);
            studentIdLabel.setVisible(false);
            nameIdLabel.setVisible(false);
            emailLabel.setVisible(false);
            addressLabel.setVisible(false);
            viewProfileButton.setVisible(true);
            manageEnrollmentsButton.setVisible(true);
            phoneLabel.setVisible(false);
            academicLevelLabel.setVisible(false);
        }
    }

    // List to store students dynamically
    private ObservableList<Student> studentList;

    // Initialize the student data and configure table columns
    public void initialize() {
        studentList = FXCollections.observableArrayList();
        studentTable.setItems(studentList);

        idColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        levelColumn.setCellValueFactory(cellData -> cellData.getValue().academicLevelProperty());
        semesterColumn.setCellValueFactory(cellData -> cellData.getValue().currentSemesterProperty());

        academicLevelBox.getItems().addAll("Undergraduate", "Graduate", "PhD");
    }

    // Add a new student to the list
    @FXML
    private void addStudent() {
        String studentId = studentIdField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String academicLevel = academicLevelBox.getValue();
        String currentSemester = "Fall 2025"; // Default semester

        if (studentId.isEmpty() || name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty() || academicLevel == null) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        Student student = new Student(studentId, name, email, address, phone, academicLevel, currentSemester);
        studentList.add(student);
        clearFields();
        showAlert("Success", "Student added successfully!");
    }

    // Edit the selected student's details
    @FXML
    private void editStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            selectedStudent.setAddress(addressField.getText());
            selectedStudent.setPhone(phoneField.getText());
            studentTable.refresh();
            showAlert("Success", "Student details updated!");
        } else {
            showAlert("Error", "Please select a student to edit.");
        }
    }

    // Delete the selected student from the list
    @FXML
    private void deleteStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentList.remove(selectedStudent);
            showAlert("Success", "Student removed successfully!");
        } else {
            showAlert("Error", "Please select a student to delete.");
        }
    }

    // View detailed profile of the selected student
    @FXML
    private void viewStudentProfile() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            String profile = "Student ID: " + selectedStudent.getStudentId() + "\n" +
                    "Name: " + selectedStudent.getName() + "\n" +
                    "Email: " + selectedStudent.getEmail() + "\n" +
                    "Address: " + selectedStudent.getAddress() + "\n" +
                    "Phone: " + selectedStudent.getPhone() + "\n" +
                    "Academic Level: " + selectedStudent.getAcademicLevel() + "\n" +
                    "Current Semester: " + selectedStudent.getCurrentSemester();

            showAlert("Student Profile", profile);
        } else {
            showAlert("Error", "Please select a student to view.");
        }
    }

    // Placeholder for enrollment management
    @FXML
    private void manageEnrollments() {
        showAlert("Manage Enrollments", "Here you can manage student enrollments.");
    }

    // Placeholder for tracking academic progress
    @FXML
    private void trackProgress() {
        showAlert("Academic Progress", "This section tracks grades and academic progress.");
    }

    // Manage tuition fees based on academic level
    @FXML
    private void manageTuition() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            String academicLevel = selectedStudent.getAcademicLevel();
            int tuitionFee;

            switch (academicLevel) {
                case "Undergraduate":
                    tuitionFee = 5000;
                    break;
                case "Graduate":
                    tuitionFee = 4000;
                    break;
                case "PhD":
                    tuitionFee = 6000;
                    break;
                default:
                    tuitionFee = 0; // Handle unexpected values
                    break;
            }

            showAlert("Tuition Fee", "Tuition fee for " + academicLevel + ": $" + tuitionFee);
        } else {
            showAlert("Error", "Please select a student to calculate tuition.");
        }
    }

    // Helper method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Helper method to clear input fields
    private void clearFields() {
        studentIdField.clear();
        nameField.clear();
        emailField.clear();
        addressField.clear();
        phoneField.clear();
        academicLevelBox.setValue(null);

        }
    @FXML
    public void backToDashBoard(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));
        Parent root = loader.load();

        // Pass admin status
        DashBoardController dashboardController = loader.getController();
        dashboardController.setIsAdmin(this.isAdmin); // Pass admin status back

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }



}
