package com.example.universitymanagementsystem;

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

    private ObservableList<Subject> subjects = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        // Set table data
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
        for (Subject s : subjects) {
            if (s.getCode().equals(code)) {
                showAlert("Error", "Subject code must be unique.");
                return;
            }
        }
        subjects.add(new Subject(code, name));
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
        selected.setCode(codeInput.getText().trim());
        selected.setName(nameInput.getText().trim());
        subjectTable.refresh();
    }

    @FXML
    private void deleteSubject() {
        Subject selected = subjectTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            subjects.remove(selected);
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void backToDashBoard(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoard.fxml"));
        Parent root = loader.load();

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }
}
