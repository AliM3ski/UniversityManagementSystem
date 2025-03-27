package com.example.universitymanagementsystem.FacultyManagement;

import com.example.universitymanagementsystem.Users.User;
import com.example.universitymanagementsystem.moveBetweenInterfaces;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.time.LocalDate;
import javafx.scene.input.MouseEvent;

public class FacultyManagementController {

    private User user; // User object to store user information

    @FXML
    public AnchorPane contentPane;
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
    public Button editButton;
    public Button deleteButton;

    private final ObservableList<FacultyManagement> facultyList = FXCollections.observableArrayList();

    // Method to set the user object
    public void setUser(User user) {
        this.user = user;
        checkAdminStatus(); // Configure UI based on user type
    }

    // Configures UI based on user type
    private void checkAdminStatus() {
        switch (user.getUserType()) {
            case "Admin":
                System.out.println("Admin can manage all faculty.");
                enableInputFields(true);
                add.setVisible(true);
                editButton.setVisible(true);
                deleteButton.setVisible(true);
                break;
            case "Faculty":
                System.out.println("Faculty can view and edit their own details.");
                enableInputFields(false);
                add.setVisible(false);
                editButton.setVisible(true);
                deleteButton.setVisible(false);
                break;
            case "Student":
                System.out.println("Student can only view faculty details.");
                enableInputFields(false);
                add.setVisible(false);
                editButton.setVisible(false);
                deleteButton.setVisible(false);
                break;
        }
    }

    // Helper method to enable or disable input fields
    private void enableInputFields(boolean enable) {
        nameInput.setDisable(!enable);
        emailInput.setDisable(!enable);
        degreeInput.setDisable(!enable);
        researchInput.setDisable(!enable);
        coursesInput.setDisable(!enable);
        officeInput.setDisable(!enable);
        phoneNumberInput.setDisable(!enable);
        departmentInput.setDisable(!enable);
        joinDateInput.setDisable(!enable);
        add.setDisable(!enable);
        editButton.setDisable(!enable);
        deleteButton.setDisable(!enable);

        nameInput.setVisible(enable);
        emailInput.setVisible(enable);
        degreeInput.setVisible(enable);
        researchInput.setVisible(enable);
        coursesInput.setVisible(enable);
        officeInput.setVisible(enable);
        phoneNumberInput.setVisible(enable);
        departmentInput.setVisible(enable);
        joinDateInput.setVisible(enable);
        add.setVisible(enable);
        editButton.setVisible(enable);
        deleteButton.setVisible(enable);
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
    public void editFaculty(ActionEvent actionEvent) {
        FacultyManagement selectedFaculty = facultyTable.getSelectionModel().getSelectedItem();
        if (selectedFaculty != null) {
            // Check each field and update only if it's changed
            if (!nameInput.getText().isEmpty() && !nameInput.getText().equals(selectedFaculty.getName())) {
                selectedFaculty.setName(nameInput.getText());
            }
            if (!emailInput.getText().isEmpty() && !emailInput.getText().equals(selectedFaculty.getEmail())) {
                selectedFaculty.setEmail(emailInput.getText());
            }
            if (!degreeInput.getText().isEmpty() && !degreeInput.getText().equals(selectedFaculty.getDegree())) {
                selectedFaculty.setDegree(degreeInput.getText());
            }
            if (!researchInput.getText().isEmpty() && !researchInput.getText().equals(selectedFaculty.getResearchInterests())) {
                selectedFaculty.setResearchInterests(researchInput.getText());
            }
            if (!coursesInput.getText().isEmpty() && !coursesInput.getText().equals(selectedFaculty.getCoursesOffered())) {
                selectedFaculty.setCoursesOffered(coursesInput.getText());
            }
            if (!officeInput.getText().isEmpty() && !officeInput.getText().equals(selectedFaculty.getOfficeLocation())) {
                selectedFaculty.setOfficeLocation(officeInput.getText());
            }
            if (!phoneNumberInput.getText().isEmpty() && !phoneNumberInput.getText().equals(selectedFaculty.getPhoneNumber())) {
                selectedFaculty.setPhoneNumber(phoneNumberInput.getText());
            }
            if (!departmentInput.getText().isEmpty() && !departmentInput.getText().equals(selectedFaculty.getDepartment())) {
                selectedFaculty.setDepartment(departmentInput.getText());
            }
            if (joinDateInput.getValue() != null && !joinDateInput.getValue().equals(selectedFaculty.getJoinDate())) {
                selectedFaculty.setJoinDate(joinDateInput.getValue());
            }

            facultyTable.refresh();  // Refresh the table to reflect changes
            clearFields();  // Clear the input fields after editing
        }
    }

    public void deleteFaculty(ActionEvent actionEvent) {
        FacultyManagement selectedFaculty = facultyTable.getSelectionModel().getSelectedItem();
        if (selectedFaculty != null) {
            facultyTable.getItems().remove(selectedFaculty);
        }
    }
    @FXML
    public void backToDashBoard(MouseEvent event) throws IOException {
        moveBetweenInterfaces.openDashBoard(user, contentPane);
    }
}