package com.example.universitymanagementsystem.StudentManagement;

import com.example.universitymanagementsystem.DashBoard.DashBoardController;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelReader;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelWriter;
import com.example.universitymanagementsystem.Users.User;
import com.example.universitymanagementsystem.Users.Student;
import com.example.universitymanagementsystem.moveBetweenInterfaces;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
import java.nio.file.Path;

public class StudentManagementController {

    public ImageView profileImageView;
    // Table and TableColumn for displaying student data
    @FXML
    public AnchorPane contentPane;
    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn, nameColumn, emailColumn, addressColumn;

    @FXML
    private TableColumn<Student, String> phoneColumn;
    @FXML
    private TableColumn<Student, String> levelColumn;
    @FXML
    private TableColumn<Student, String> semesterColumn, subjectRegisteredColumn, thesisTitleColumn, progressColumn, passwordColumn;

    // Input fields for adding or editing student info
    @FXML
    private TextField studentIdField, nameField, emailField, addressField, phoneField, subjectRegisteredField, thesisTitleField, progressField, passwordField;

    @FXML
    private ComboBox<String> academicLevelBox;

    // Buttons in the FXML
    @FXML
    private Button addStudentbutton, editStudentbutton, deleteStudentbutton;

    @FXML
    private Button viewProfileButton, manageEnrollmentsButton, manageTuitionButton;

    // Labels in the FXML
    @FXML
    private Label studentIdLabel, nameIdLabel, emailLabel, addressLabel, phoneLabel, academicLevelLabel, subjectRegisteredLabel, thesisTitleLabel, progressLabel, passwordLabel;

    private User user; // User object to store user information
    private ImageView studentImageView;

    private String studentPhotoPath; // Store the path of the student photo

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
        subjectRegisteredColumn.setCellValueFactory(cellData -> cellData.getValue().subjectRegisteredProperty());
        thesisTitleColumn.setCellValueFactory(cellData -> cellData.getValue().thesisTitleProperty());
        progressColumn.setCellValueFactory(cellData -> cellData.getValue().progressProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

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
        String subjectRegistered = subjectRegisteredField.getText();
        String thesisTitle = thesisTitleField.getText();
        String progress = progressField.getText();
        String password = passwordField.getText();
        String currentSemester = "Fall 2025"; // Default semester
        String photoPath = "";

        if (studentId.isEmpty() || name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty() || academicLevel == null) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        Student student = new Student(studentId, name, email, address, phone, academicLevel, currentSemester, photoPath, subjectRegistered, thesisTitle, progress, password);
        studentList.add(student);
        // updates the excel database with new student
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
        String oldPhotoPath = selectedStudent.getPhotoPath();
        String oldSubjectRegistered = selectedStudent.getSubjectRegistered();
        String oldThesisTitle = selectedStudent.getThesisTitle();
        String oldProgress = selectedStudent.getProgress();
        String oldPassword = selectedStudent.getPassword();

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
        if (subjectRegisteredField.getText() != null && !subjectRegisteredField.getText().isEmpty()) {
            selectedStudent.setSubjectRegistered(subjectRegisteredField.getText());
        }
        if (thesisTitleField.getText() != null && !thesisTitleField.getText().isEmpty()) {
            selectedStudent.setThesisTitle(thesisTitleField.getText());
        }
        if (progressField.getText() != null && !progressField.getText().isEmpty()) {
            selectedStudent.setProgress(progressField.getText());
        }
        if (passwordField.getText() != null && !passwordField.getText().isEmpty()) {
            selectedStudent.setPassword(passwordField.getText());
        }


        // Update the Excel file
        ExcelWriter.editStudentInExcel("src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx",  selectedStudent, oldStudentId, oldName, oldAddress, oldPhone, oldEmail, oldAcademicLevel, oldPhotoPath, oldSubjectRegistered, oldThesisTitle,oldProgress,oldPassword);

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

        String studentInfoMessage = ("Name: " + selectedStudent.getName() + "\nEmail: " + selectedStudent.getEmail() +
                "\nAddress: " + selectedStudent.getAddress() + "\nPhone: " + selectedStudent.getPhone() +
                "\nAcademic Level: " + selectedStudent.getAcademicLevel() + "\nSubject Registered: "+
                selectedStudent.getSubjectRegistered() + "\nThesis Title: "+ selectedStudent.getThesisTitle() + "\nProgress: "+
                selectedStudent.getProgress() + "\nPassword: " + selectedStudent.getPassword());

        if (selectedStudent == null) {
            showAlert("Error", "Please select a student to view profile.", "images/default.png");
            return;
        }
        else{
            System.out.println(selectedStudent.getPhotoPath());
            Path targetDirectory = Path.of("src/main/java/com/example/universitymanagementsystem/ExcelDatabase/studentprofileimages");
            try {
                showAlert("Student Profile",studentInfoMessage, targetDirectory.resolve(selectedStudent.getPhotoPath()).toFile().toURI().toURL().toString());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
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

    @FXML
    private void handleUploadPhoto() throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Photo");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) profileImageView.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        // gets file directory of the studentprofileimages folder
        Path targetDirectory = Path.of("src/main/java/com/example/universitymanagementsystem/ExcelDatabase/studentprofileimages");
        // gets path of the user uploaded image


        if (selectedFile != null) {
            Path targetPath = targetDirectory.resolve(selectedFile.getName());
            System.out.println(selectedFile.toPath().toAbsolutePath()+"   "+targetPath.getParent());
            System.out.println(new File(selectedFile.toPath().toAbsolutePath().toString())+" "+new File(targetPath.toString()));
            System.out.println(Files.exists(selectedFile.toPath().toAbsolutePath()));
            System.out.println(Files.isReadable(selectedFile.toPath().toAbsolutePath()));

            // moves user selected image to studentprofileimages folder in our project
            Files.copy(selectedFile.toPath().toAbsolutePath(),targetPath, StandardCopyOption.REPLACE_EXISTING);
            // updates to the new file path
            selectedFile = targetPath.toFile();
            try {
                studentPhotoPath =selectedFile.getName();
                Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                    selectedStudent.setPhotoPath(studentPhotoPath);
                    ExcelWriter.updateStudentPhoto(selectedStudent.getStudentId(), studentPhotoPath);
                    System.out.println("Photo path saved to Excel: " + studentPhotoPath);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error loading image: " + e.getMessage());
            }
        } else {
            System.out.println("No file selected.");
        }
    }




    // Helper method to show alerts with an optional image
    private void showAlert(String title, String message, String imagePath) {
        // Create an alert of type INFORMATION
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Set the title of the alert window
        alert.setTitle(title);
        // No header is displayed in the alert
        alert.setHeaderText(null);
        // Set the main content text of the alert
        alert.setContentText(message);

        // Check if a valid image path is provided
        if (imagePath != null && !imagePath.isEmpty()) {
            // Load the image from the given file path
            Image image = new Image(imagePath);
            // Create an ImageView to display the image in the alert
            ImageView imageView = new ImageView(image);
            // Set the dimensions for the image display
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            // Set the imageView as the graphic for the alert
            alert.setGraphic(imageView);
        }
        // Display the alert and wait for the user to close it
        alert.showAndWait();
    }

    public void showAlert(String title, String message){
        showAlert(title, message, null);

    }



    // Helper method to clear input fields
    private void clearFields() {
        studentIdField.clear();
        nameField.clear();
        emailField.clear();
        addressField.clear();
        phoneField.clear();
        academicLevelBox.setValue(null);
        subjectRegisteredField.clear();
        thesisTitleField.clear();
        progressField.clear();
        passwordField.clear();

    }

    @FXML
    public void backToDashBoard(MouseEvent event) throws IOException {
        moveBetweenInterfaces.openDashBoard(user, contentPane);
    }
}