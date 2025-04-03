package com.example.universitymanagementsystem.FacultyManagement;

import com.example.universitymanagementsystem.ExcelDatabase.ExcelReader;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelWriter;
import com.example.universitymanagementsystem.Users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.nio.file.Paths;


public class FacultyManagementController {

    private User user;
    @FXML public AnchorPane contentPane;

    @FXML
    private ImageView facultyImageView;
    private String facultyPhotoPath;

    // Table and columns
    @FXML private TableView<FacultyManagement> facultyTable;
    @FXML private TableColumn<FacultyManagement, String> faculty_Column, name_Column, degree_Column,
            research_Column, email_Column, office_Column, course_Column, password_Column;

    // Input fields
    @FXML private TextField facultyInput, nameInput, degreeInput, researchInput,
            emailInput, officeInput, passwordInput;

    // Buttons
    @FXML private Button add, editButton, deleteButton, uploadbutton;

    private final ObservableList<FacultyManagement> facultyList = FXCollections.observableArrayList();
    private static final String EXCEL_PATH = "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx";

    public void setUser(User user) {
        this.user = user;
        checkAdminStatus();
    }

    private void checkAdminStatus() {
        switch (user.getUserType()) {
            case "Admin":
                enableInputFields(true);
                add.setVisible(true);
                editButton.setVisible(true);
                deleteButton.setVisible(true);
                break;
            case "Faculty":
                enableInputFields(false);
                add.setVisible(false);
                editButton.setVisible(true);
                deleteButton.setVisible(false);
                break;
            case "Student":
                enableInputFields(false);
                add.setVisible(false);
                editButton.setVisible(false);
                deleteButton.setVisible(false);
                break;
        }
    }

    private void enableInputFields(boolean enable) {
        facultyInput.setDisable(!enable);
        nameInput.setDisable(!enable);
        degreeInput.setDisable(!enable);
        researchInput.setDisable(!enable);
        emailInput.setDisable(!enable);
        officeInput.setDisable(!enable);
        passwordInput.setDisable(!enable);

        facultyInput.setVisible(enable);
        nameInput.setVisible(enable);
        degreeInput.setVisible(enable);
        researchInput.setVisible(enable);
        emailInput.setVisible(enable);
        officeInput.setVisible(enable);
        passwordInput.setVisible(enable);

        add.setDisable(!enable);
        editButton.setDisable(!enable);
        deleteButton.setDisable(!enable);
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        loadFacultyData();
    }

    private void setupTableColumns() {
        facultyTable.setItems(facultyList);
        faculty_Column.setCellValueFactory(cellData -> cellData.getValue().facultyIdProperty());
        name_Column.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        degree_Column.setCellValueFactory(cellData -> cellData.getValue().degreeProperty());
        research_Column.setCellValueFactory(cellData -> cellData.getValue().researchInterestsProperty());
        email_Column.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        office_Column.setCellValueFactory(cellData -> cellData.getValue().officeLocationProperty());
        course_Column.setCellValueFactory(cellData -> cellData.getValue().coursesOfferedProperty());
        password_Column.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
    }

    private void loadFacultyData() {
        ExcelReader.readExcelFaculty(facultyList, EXCEL_PATH);
        facultyTable.setItems(facultyList);
    }

    @FXML
    private void addFaculty() {
        String facultyId = facultyInput.getText();
        String name = nameInput.getText();
        String degree = degreeInput.getText();
        String research = researchInput.getText();
        String email = emailInput.getText();
        String office = officeInput.getText();
        String password = passwordInput.getText();

        if (facultyId.isEmpty() || name.isEmpty() || degree.isEmpty() ||
                research.isEmpty() || email.isEmpty() || office.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return;
        }

        FacultyManagement faculty = new FacultyManagement(facultyId, name, degree, research, email, office, password);
        facultyList.add(faculty);
        ExcelWriter.writeToExcelFaculty(facultyList, EXCEL_PATH, facultyId);

        clearFields();
        showAlert("Success", "Faculty added successfully!");
    }

    @FXML
    public void editFaculty(ActionEvent actionEvent) {
        FacultyManagement selectedFaculty = facultyTable.getSelectionModel().getSelectedItem();
        if (selectedFaculty == null) {
            showAlert("Error", "Please select a faculty to edit.");
            return;
        }

        // Store old values
        String oldFacultyId = selectedFaculty.getFacultyId();
        String oldName = selectedFaculty.getName();
        String oldDegree = selectedFaculty.getDegree();
        String oldResearch = selectedFaculty.getResearchInterests();
        String oldEmail = selectedFaculty.getEmail();
        String oldOffice = selectedFaculty.getOfficeLocation();
        String oldPassword = selectedFaculty.getPassword();

        // Update with new values
        if (!facultyInput.getText().isEmpty()) selectedFaculty.setFacultyId(facultyInput.getText());
        if (!nameInput.getText().isEmpty()) selectedFaculty.setName(nameInput.getText());
        if (!degreeInput.getText().isEmpty()) selectedFaculty.setDegree(degreeInput.getText());
        if (!researchInput.getText().isEmpty()) selectedFaculty.setResearchInterests(researchInput.getText());
        if (!emailInput.getText().isEmpty()) selectedFaculty.setEmail(emailInput.getText());
        if (!officeInput.getText().isEmpty()) selectedFaculty.setOfficeLocation(officeInput.getText());
        if (!passwordInput.getText().isEmpty()) selectedFaculty.setPassword(passwordInput.getText());

        ExcelWriter.editFacultyInExcel(EXCEL_PATH, selectedFaculty,
                oldFacultyId, oldName, oldDegree, oldResearch, oldEmail, oldOffice, oldPassword);

        facultyTable.refresh();
        clearFields();
    }

    @FXML
    public void deleteFaculty(ActionEvent actionEvent) {
        FacultyManagement selectedFaculty = facultyTable.getSelectionModel().getSelectedItem();

        if (selectedFaculty != null) {
            facultyList.remove(selectedFaculty);  // Remove from list
            facultyTable.getItems().remove(selectedFaculty);  // Ensure table updates
            ExcelWriter.deleteFacultyFromExcel(EXCEL_PATH, selectedFaculty.getFacultyId()); // Remove from database
            facultyTable.refresh();  // Refresh the table UI
            showAlert("Success", "Faculty removed successfully!");
        } else {
            showAlert("Error", "Please select a faculty to delete.");
        }
    }


    public void upload(ActionEvent actionEvent) {
        // Implement similar to StudentManagementController if needed
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        facultyInput.clear();
        nameInput.clear();
        degreeInput.clear();
        researchInput.clear();
        emailInput.clear();
        officeInput.clear();
        passwordInput.clear();
    }

    @FXML
    private void handleUploadPhoto() {
        // Create file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Faculty Photo");

        // Set extension filters
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Get the current stage from any control (using the upload button in this case)
        Stage stage = (Stage) uploadbutton.getScene().getWindow();

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                // Create target directory path
                Path targetDir = Paths.get(
                        "src", "main", "java", "com", "example",
                        "universitymanagementsystem", "ExcelDatabase", "faculty_photos"
                );

                // Create directory if it doesn't exist
                if (!Files.exists(targetDir)) {
                    Files.createDirectories(targetDir);
                }

                // Create target file path
                Path targetPath = targetDir.resolve(selectedFile.getName());

                // Copy the file
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                // Update the image view
                facultyImageView.setImage(new Image(targetPath.toUri().toString()));

                // Update the selected faculty member
                FacultyManagement selectedFaculty = facultyTable.getSelectionModel().getSelectedItem();
                if (selectedFaculty != null) {
                    selectedFaculty.setPhotoPath(targetPath.toString());
                    showAlert("Success", "Photo uploaded successfully!");
                } else {
                    showAlert("Error", "Please select a faculty member first.");
                }
            } catch (IOException e) {
                showAlert("Error", "Failed to upload photo: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void viewFacultyProfile() {
        FacultyManagement selectedFaculty = facultyTable.getSelectionModel().getSelectedItem();

        if (selectedFaculty == null) {
            showAlert("Error", "Please select a faculty member first.");
            return;
        }

        // Build profile information string
        String facultyInfo = String.format(
                "Faculty ID: %s\nName: %s\nDegree: %s\nResearch: %s\nEmail: %s\nOffice: %s",
                selectedFaculty.getFacultyId(),
                selectedFaculty.getName(),
                selectedFaculty.getDegree(),
                selectedFaculty.getResearchInterests(),
                selectedFaculty.getEmail(),
                selectedFaculty.getOfficeLocation()
        );

        // Handle photo display
        String imagePath = null;
        if (selectedFaculty.getPhotoPath() != null && !selectedFaculty.getPhotoPath().isEmpty()) {
            try {
                imagePath = Paths.get(selectedFaculty.getPhotoPath()).toUri().toString();
            } catch (Exception e) {
                System.out.println("Error loading photo: " + e.getMessage());
            }
        }

        // Show alert with profile info and photo
        showAlertWithImage("Faculty Profile", facultyInfo, imagePath);
    }

    // Corrected method to show an alert with an image
    private void showAlertWithImage(String title, String message, String imagePath) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        if (imagePath != null) {
            try {
                Image image = new Image(imagePath);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                alert.setGraphic(imageView);
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        }

        alert.showAndWait();
    }


}