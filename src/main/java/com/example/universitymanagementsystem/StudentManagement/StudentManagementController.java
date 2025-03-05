package com.example.universitymanagementsystem.StudentManagement;

import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

public class StudentManagementController {

    // Table and TableColumn for displaying student data
    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> emailColumn;

    @FXML
    private TableColumn<Student, String> addressColumn;

    @FXML
    private TableColumn<Student, String> phoneColumn;

    @FXML
    private TableColumn<Student, String> levelColumn;

    @FXML
    private TableColumn<Student, String> semesterColumn;

    // Input fields for adding or editing student info
    @FXML
    private TextField studentIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> academicLevelBox;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneField;

    // List to store students dynamically
    private ObservableList<Student> studentList;

    // Initialize the student data and configure table columns
    public void initialize() {
        studentList = FXCollections.observableArrayList();
        studentTable.setItems(studentList);

        // Set up TableColumn cell value factories to display the corresponding student data
        idColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        levelColumn.setCellValueFactory(cellData -> cellData.getValue().academicLevelProperty());
        semesterColumn.setCellValueFactory(cellData -> cellData.getValue().currentSemesterProperty());

        // Populate the academic level ComboBox with options
        academicLevelBox.getItems().addAll("Undergraduate", "Graduate", "PhD");

        studentTable.setItems(studentList);  // Assign the student list to the TableView
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

        // Validate input fields to ensure none are empty
        if (studentId.isEmpty() || name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty() || academicLevel == null) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        // Create a new Student object and add it to the student list
        Student student = new Student(studentId, name, email, address, phone, academicLevel, currentSemester);
        studentList.add(student);

        clearFields(); // Clear the input fields after adding the student
        showAlert("Success", "Student added successfully!");
    }

    // Edit the selected student's details
    @FXML
    private void editStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Set the selected student's address and phone to the new values
            selectedStudent.setAddress(addressField.getText());
            selectedStudent.setPhone(phoneField.getText());
            studentTable.refresh(); // Refresh the table to show updated data
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
            studentList.remove(selectedStudent); // Remove selected student from the list
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

    // Manage student enrollments (this is just a placeholder for now)
    @FXML
    private void manageEnrollments() {
        // You could load a new FXML scene for enrollment management here
        showAlert("Manage Enrollments", "Here you can manage student enrollments.");
    }

    // Track the academic progress of a student (this is just a placeholder for now)
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
            int tuitionFee = 0;

            // Set the tuition fee based on the academic level
            switch (academicLevel) {
                case "Undergraduate":
                    tuitionFee = 5000; // Undergraduate tuition fee
                    break;
                case "Graduate":
                    tuitionFee = 4000; // Graduate tuition fee
                    break;
                case "PhD":
                    tuitionFee = 6000; // PhD tuition fee
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

    // Helper method to clear the input fields
    private void clearFields() {
        studentIdField.clear();
        nameField.clear();
        emailField.clear();
        addressField.clear();
        phoneField.clear();
        academicLevelBox.setValue(null); // Reset ComboBox value
    }
}












