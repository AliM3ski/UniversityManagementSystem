package com.example.universitymanagementsystem.SubjectManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SubjectManagementController {

    private boolean isAdmin;

    @FXML
    private Button addbutton;

    @FXML
    private Button editbutton;

    @FXML
    private Button deletebutton;

    // TableView to display subjects
    @FXML
    private TableView<Subject> subjectTable;

    // TableColumn for subject code
    @FXML
    private TableColumn<Subject, String> codeColumn;

    // TableColumn for subject name
    @FXML
    private TableColumn<Subject, String> nameColumn;

    // TextField for subject code input
    @FXML
    private TextField codeInput;

    // TextField for subject name input
    @FXML
    private TextField nameInput;

    // TextField for searching a subject
    @FXML
    private TextField searchInput;

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        checkAdminStatus(); // Update button visibility based on admin status
    }
    // hides add, edit, and delete buttons when logged in as user
    private void checkAdminStatus() {
        if (!isAdmin) {
            System.out.println("User is not an admin.");
            codeInput.setDisable(true);
            nameInput.setDisable(true);
            addbutton.setDisable(true);
            editbutton.setDisable(true);
            deletebutton.setDisable(true);
            codeInput.setVisible(false);
            nameInput.setVisible(false);
            addbutton.setVisible(false);
            editbutton.setVisible(false);
            deletebutton.setVisible(false);
        }
    }

    // List to store subjects dynamically
    // ObservableList to store Subject objects dynamically and update the UI automatically.
    // The FXCollections.observableArrayList() method initializes an empty ObservableList that can be dynamically updated.
    private ObservableList<Subject> subjects = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // creates column for subject codes
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        // create column for subject names
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        // Set/Display table data
        subjectTable.setItems(subjects);
    }

    @FXML
    private void addSubject() {
        // Get input values and trim all spaces
        String code = codeInput.getText().trim();
        String name = nameInput.getText().trim();

        // Validate that both fields are not empty
        if (code.isEmpty() || name.isEmpty()) {
            showAlert("Error", "Both fields are required.");
            return;
        }

        // Ensure subject code is unique
        for (Subject s : subjects) {
            // checks that subject code does not already exist
            if (s.getCode().equals(code)) {
                // display pop up with error message
                showAlert("Error", "Subject code must be unique.");
                return;
            }
        }

        // Add new subject to the list
        subjects.add(new Subject(code, name));

        // Clear input fields after adding
        codeInput.clear();
        nameInput.clear();
    }

    @FXML
    private void editSubject() {
        // Get the selected subject from the table
        Subject selected = subjectTable.getSelectionModel().getSelectedItem();

        // Ensure a subject is selected
        if (selected == null) {
            showAlert("Error", "No subject selected.");
            return;
        }

        // Update subject details
        selected.setCode(codeInput.getText().trim());
        selected.setName(nameInput.getText().trim());

        // Refresh table to reflect changes
        subjectTable.refresh();
    }

    @FXML
    private void deleteSubject() {
        // Get selected subject
        Subject selected = subjectTable.getSelectionModel().getSelectedItem();

        // Remove subject if selected, else show error
        if (selected != null) {
            subjects.remove(selected);
        } else {
            showAlert("Error", "No subject selected.");
        }
    }

    @FXML
    private void searchSubject() {
        // Get search query
        String code = searchInput.getText().trim();

        // Iterate over subjects to find a match
        for (Subject s : subjects) {
            if (s.getCode().equalsIgnoreCase(code)) {
                subjectTable.getSelectionModel().select(s);
                return;
            }
        }

        // Show alert if no subject is found
        showAlert("Not Found", "No subject found with this code.");
    }
    // function for all the pop up alerts
    private void showAlert(String title, String message) {
        // Display alert messages to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void backToDashBoard(MouseEvent event) throws IOException {
        // Load the Dashboard FXML file
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
