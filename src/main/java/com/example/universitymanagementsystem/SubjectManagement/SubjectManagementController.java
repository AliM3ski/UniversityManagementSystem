package com.example.universitymanagementsystem.SubjectManagement;

import com.example.universitymanagementsystem.ExcelDatabase.ExcelReader;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelWriter;
import com.example.universitymanagementsystem.Users.User;
import com.example.universitymanagementsystem.moveBetweenInterfaces;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SubjectManagementController {

    private User user; // User object to store user information

    @FXML
    public AnchorPane contentPane;
    @FXML
    private Button addbutton;

    @FXML
    private Button editbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private TableColumn<Subject, String> codeColumn;

    @FXML
    private TableColumn<Subject, String> nameColumn;

    @FXML
    private TextField codeInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField searchInput;

    // Method to set the user object
    public void setUser(User user) {
        this.user = user;
        checkAdminStatus(); // Configure UI based on user type
    }

    // Configures UI based on user type
    private void checkAdminStatus() {
        switch (user.getUserType()) {
            case "Admin":
                System.out.println("Admin can manage all subjects.");
                enableInputFields(true);
                addbutton.setVisible(true);
                editbutton.setVisible(true);
                deletebutton.setVisible(true);
                break;
            case "Faculty":
                System.out.println("Faculty can view and edit subjects.");
                enableInputFields(true);
                addbutton.setVisible(false);
                editbutton.setVisible(true);
                deletebutton.setVisible(false);
                break;
            case "Student":
                System.out.println("Student can only view subjects.");
                enableInputFields(false);
                addbutton.setVisible(false);
                editbutton.setVisible(false);
                deletebutton.setVisible(false);
                break;
        }
    }

    // Helper method to enable or disable input fields
    private void enableInputFields(boolean enable) {
        codeInput.setDisable(!enable);
        nameInput.setDisable(!enable);
        codeInput.setVisible(enable);
        nameInput.setVisible(enable);
    }

    // List to store subjects dynamically
    private ObservableList<Subject> subjects = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        // Load subjects from the Excel database
        loadSubjects();
    }

    private void loadSubjects() {
        String filePath = "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx";
        ExcelReader.readExcelSubject(subjects, filePath);
        subjectTable.setItems(subjects);
    }

    @FXML
    private void addSubject() {
        String code = codeInput.getText().trim();
        String name = nameInput.getText().trim();

        if (code.isEmpty() || name.isEmpty()) {
            showAlert("Error", "Both fields are required.");
            return;
        }

        // Ensure subject code is unique
        for (Subject s : subjects) {
            if (s.getCode().equals(code)) {
                showAlert("Error", "Subject code must be unique.");
                return;
            }
        }

        // Add new subject to the list
        subjects.add(new Subject(code, name));

        // Update the Excel file
        ExcelWriter.writeToExcelSubject(subjects, "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx");

        // Clear input fields
        codeInput.clear();
        nameInput.clear();
    }

    @FXML
    private void editSubject() {
        Subject selected = subjectTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Error", "No subject selected.");
            return;
        }

        // Store old subject code before updating
        String oldSubjectCode = selected.getCode();

        // Update subject details
        selected.setCode(codeInput.getText().trim());
        selected.setName(nameInput.getText().trim());

        // Update the Excel file
        ExcelWriter.editSubjectInExcel("src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx", oldSubjectCode, selected);

        // Refresh table to reflect changes
        subjectTable.refresh();
    }

    @FXML
    private void deleteSubject() {
        Subject selected = subjectTable.getSelectionModel().getSelectedItem();

        if (selected != null) {
            subjects.remove(selected);
            ExcelWriter.deleteSubjectFromExcel("src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx", selected.getCode());
        } else {
            showAlert("Error", "No subject selected.");
        }
    }

    @FXML
    private void searchSubject() {
        String code = searchInput.getText().trim();

        for (Subject s : subjects) {
            if (s.getCode().equalsIgnoreCase(code)) {
                subjectTable.getSelectionModel().select(s);
                return;
            }
        }

        showAlert("Not Found", "No subject found with this code.");
    }

    // Helper method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}


