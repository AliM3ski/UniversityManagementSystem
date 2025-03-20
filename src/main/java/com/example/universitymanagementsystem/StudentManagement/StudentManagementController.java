package com.example.universitymanagementsystem.StudentManagement;

import com.example.universitymanagementsystem.DashBoard.DashBoardController;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelReader;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelWriter;
import com.example.universitymanagementsystem.Users.User;
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

    // Buttons in the FXML
    @FXML
    private Button addStudentbutton, editStudentbutton, deleteStudentbutton;

    @FXML
    private Button viewProfileButton, manageEnrollmentsButton, manageTuitionButton;

    // Labels in the FXML
    @FXML
    private Label studentIdLabel, nameIdLabel, emailLabel, addressLabel, phoneLabel, academicLevelLabel;

    private User user; // User object to store user information

    // Method to set the user object
    public void setUser(User user) {
        this.user = user;
        checkAdminStatus(); // Configure UI based on user type
    }

    // Configures UI based on user type
    private void checkAdminStatus() {
        switch (user.getUserType()) {
            case "Admin":
                System.out.println("Admin can manage all students.");
                enableInputFields(true);
                addStudentbutton.setVisible(true);
                editStudentbutton.setVisible(true);
                deleteStudentbutton.setVisible(true);
                manageTuitionButton.setVisible(true);
                viewProfileButton.setVisible(true);
                manageEnrollmentsButton.setVisible(true);
                break;
            case "Faculty":
                System.out.println("Faculty can view and edit students.");
                enableInputFields(false);
                addStudentbutton.setVisible(false);
                editStudentbutton.setVisible(true);
                deleteStudentbutton.setVisible(false);
                manageTuitionButton.setVisible(false);
                viewProfileButton.setVisible(true);
                manageEnrollmentsButton.setVisible(true);
                break;
            case "Student":
                System.out.println("Student can view their profile.");
                enableInputFields(false);
                addStudentbutton.setVisible(false);
                editStudentbutton.setVisible(false);
                deleteStudentbutton.setVisible(false);
                manageTuitionButton.setVisible(false);
                viewProfileButton.setVisible(true);
                manageEnrollmentsButton.setVisible(false);
                break;
        }
    }

    // Helper method to enable or disable input fields
    private void enableInputFields(boolean enable) {
        studentIdField.setDisable(!enable);
        nameField.setDisable(!enable);
        emailField.setDisable(!enable);
        academicLevelBox.setDisable(!enable);
        addressField.setDisable(!enable);
        phoneField.setDisable(!enable);
        studentIdField.setVisible(enable);
        nameField.setVisible(enable);
        emailField.setVisible(enable);
        academicLevelBox.setVisible(enable);
        addressField.setVisible(enable);
        phoneField.setVisible(enable);
        studentIdLabel.setVisible(enable);
        nameIdLabel.setVisible(enable);
        emailLabel.setVisible(enable);
        addressLabel.setVisible(enable);
        phoneLabel.setVisible(enable);
        academicLevelLabel.setVisible(enable);
    }

    // List to store students dynamically
    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    // Initialize the student data and configure table columns
    public void initialize() {
        studentTable.setItems(studentList);

        idColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        levelColumn.setCellValueFactory(cellData -> cellData.getValue().academicLevelProperty());
        semesterColumn.setCellValueFactory(cellData -> cellData.getValue().currentSemesterProperty());

        academicLevelBox.getItems().addAll("Undergraduate", "Graduate", "PhD");

        loadStudent();
    }

    // Load students from the Excel database
    private void loadStudent() {
        String filePath = "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx";
        ExcelReader.readExcelStudent(studentList, filePath);
        studentTable.setItems(studentList);
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
        ExcelWriter.writeToExcelStudent(studentList, "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx");

        clearFields();
        showAlert("Success", "Student added successfully!");
    }

    // Edit the selected student's details
    @FXML
    private void editStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showAlert("Error", "Please select a student to edit.");
            return;
        }

        // Store old values
        String oldStudentId = selectedStudent.getStudentId();
        String oldName = selectedStudent.getName();
        String oldAddress = selectedStudent.getAddress();
        String oldPhone = selectedStudent.getPhone();
        String oldEmail = selectedStudent.getEmail();
        String oldAcademicLevel = selectedStudent.getAcademicLevel();

        // Only update fields if they are not empty
        if (studentIdField.getText() != null && !studentIdField.getText().isEmpty()) {
            selectedStudent.setStudentId(studentIdField.getText());
        }
        if (nameField.getText() != null && !nameField.getText().isEmpty()) {
            selectedStudent.setName(nameField.getText());
        }
        if (addressField.getText() != null && !addressField.getText().isEmpty()) {
            selectedStudent.setAddress(addressField.getText());
        }
        if (phoneField.getText() != null && !phoneField.getText().isEmpty()) {
            selectedStudent.setPhone(phoneField.getText());
        }
        if (emailField.getText() != null && !emailField.getText().isEmpty()) {
            selectedStudent.setEmail(emailField.getText());
        }
        if (academicLevelBox.getValue() != null && !academicLevelBox.getValue().isEmpty()) {
            selectedStudent.setAcademicLevel(academicLevelBox.getValue());
        }

        // Update the Excel file
        ExcelWriter.editStudentInExcel("src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx", selectedStudent, oldStudentId, oldName, oldAddress, oldPhone, oldEmail, oldAcademicLevel);

        studentTable.refresh();
    }

    // Delete the selected student from the list
    @FXML
    private void deleteStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentList.remove(selectedStudent);
            ExcelWriter.deleteStudentFromExcel("src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx", selectedStudent.getStudentId());
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

    // Navigate back to the dashboard
    @FXML
    public void backToDashBoard(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));
        Parent root = loader.load();

        // Pass the user object back to the dashboard
        DashBoardController dashboardController = loader.getController();
        dashboardController.setUser(user);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }
}