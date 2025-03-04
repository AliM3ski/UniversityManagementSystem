package com.example.universitymanagementsystem;

// Import Statements
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Alert;


public class StudentManagementController {
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> emailColumn;
    @FXML private TableColumn<Student, String> levelColumn;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;
    @FXML private TextField phoneField;
    @FXML private ComboBox<String> academicLevelBox;
    @FXML private Label generatedStudentID;
    @FXML private ImageView profileImageView;

    // List to store students dynamically
    private ObservableList<Student> studentList;

    @FXML
    public void initialize() {
        System.out.println("Initializing Student Management Controller..."); // Debugging

        studentList = FXCollections.observableArrayList();
        studentTable.setItems(studentList);

        idColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        levelColumn.setCellValueFactory(cellData -> cellData.getValue().academicLevelProperty());

        academicLevelBox.getItems().addAll("Undergraduate", "Graduate", "PhD");
    }

    @FXML
    private void addStudent() {
        System.out.println("Add Student button clicked!"); // Debugging

        String id = generateStudentID();
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String academicLevel = academicLevelBox.getValue();
        String profilePhotoPath = (profileImageView.getImage() != null) ? profileImageView.getImage().getUrl() : "file:default.png";

        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty() || academicLevel == null) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        Student student = new Student(id, name, email, address, phone, academicLevel, "Pending", "None", "None", "Semester 1", "None", "None", "0%", profilePhotoPath);
        studentList.add(student);
        generatedStudentID.setText("Student ID: " + id);
        clearFields();
    }

    private String generateStudentID() {
        return "S" + (100000 + (int)(Math.random() * 900000)); // Generates unique ID
    }

    @FXML
    private void uploadProfilePhoto() {
        System.out.println("Upload Photo button clicked!"); // Debugging

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        Stage stage = (Stage) profileImageView.getScene().getWindow();  // Get current stage
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profileImageView.setImage(image);
        }
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        addressField.clear();
        phoneField.clear();
        academicLevelBox.setValue(null);
        profileImageView.setImage(new Image("file:default.png")); // Use default image instead of null
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
