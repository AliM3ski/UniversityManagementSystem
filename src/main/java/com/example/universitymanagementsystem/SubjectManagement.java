package com.example.universitymanagementsystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class SubjectManagement extends Application {

    private TableView<Subject> table; // Table to display subjects
    private ObservableList<Subject> subjects; // List to store subjects

    public static void main(String[] args) {
        launch(args); // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Subject Management"); // Set window title

        subjects = FXCollections.observableArrayList(); // Initialize subject list

        // Table Columns
        TableColumn<Subject, String> codeColumn = new TableColumn<>("Subject Code");
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty()); // Bind code property

        TableColumn<Subject, String> nameColumn = new TableColumn<>("Subject Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty()); // Bind name property

        table = new TableView<>(); // Initialize table
        table.setItems(subjects); // Set table items
        table.getColumns().addAll(codeColumn, nameColumn); // Add columns to table

        // Input Fields for adding/editing subjects
        TextField codeInput = new TextField();
        codeInput.setPromptText("Subject Code"); // Placeholder text

        TextField nameInput = new TextField();
        nameInput.setPromptText("Subject Name"); // Placeholder text

        // Buttons for actions
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addSubject(codeInput, nameInput)); // Add subject on click

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> editSubject(codeInput, nameInput)); // Edit subject on click

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteSubject()); // Delete subject on click

        Button searchButton = new Button("Search"); // Search button
        TextField searchInput = new TextField(); // Search field
        searchInput.setPromptText("Search by Code"); // Placeholder text
        searchButton.setOnAction(e -> searchSubject(searchInput.getText())); // Search subject on click

        // Layout for input fields and buttons
        HBox inputBox = new HBox(10, codeInput, nameInput, addButton, editButton, deleteButton);
        inputBox.setAlignment(Pos.CENTER);

        // Layout for search bar
        HBox searchBox = new HBox(10, searchInput, searchButton);
        searchBox.setAlignment(Pos.CENTER);

        // Main layout
        VBox layout = new VBox(10, table, inputBox, searchBox);
        layout.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(layout, 600, 400)); // Set scene size
        primaryStage.show(); // Show window
    }

    // Method to add a new subject
    private void addSubject(TextField codeInput, TextField nameInput) {
        String code = codeInput.getText().trim(); // Get and trim input
        String name = nameInput.getText().trim();
        if (code.isEmpty() || name.isEmpty()) {
            showAlert("Error", "Both fields are required."); // Validation check
            return;
        }
        for (Subject s : subjects) {
            if (s.getCode().equals(code)) {
                showAlert("Error", "Subject code must be unique."); // Check for duplicate code
                return;
            }
        }
        subjects.add(new Subject(code, name)); // Add subject to list
        codeInput.clear(); // Clear input fields
        nameInput.clear();
    }

    // Method to edit an existing subject
    private void editSubject(TextField codeInput, TextField nameInput) {
        Subject selected = table.getSelectionModel().getSelectedItem(); // Get selected subject
        if (selected == null) {
            showAlert("Error", "No subject selected."); // Validation check
            return;
        }
        selected.setCode(codeInput.getText().trim()); // Update code
        selected.setName(nameInput.getText().trim()); // Update name
        table.refresh(); // Refresh table view
    }

    // Method to delete a subject
    private void deleteSubject() {
        Subject selected = table.getSelectionModel().getSelectedItem(); // Get selected subject
        if (selected != null) {
            subjects.remove(selected); // Remove subject from list
        } else {
            showAlert("Error", "No subject selected."); // Validation check
        }
    }

    // Method to search for a subject by code
    private void searchSubject(String code) {
        for (Subject s : subjects) {
            if (s.getCode().equalsIgnoreCase(code)) {
                table.getSelectionModel().select(s); // Select matching subject
                return;
            }
        }
        showAlert("Not Found", "No subject found with this code."); // Show alert if not found
    }

    // Method to display alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait(); // Show alert dialog
    }
}

// Subject class definition
class Subject {
    private final StringProperty code; // Property for subject code
    private final StringProperty name; // Property for subject name

    public Subject(String code, String name) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
    }

    public String getCode() {
        return code.get(); // Get code value
    }

    public void setCode(String code) {
        this.code.set(code); // Set code value
    }

    public StringProperty codeProperty() {
        return code; // Return code property
    }

    public String getName() {
        return name.get(); // Get name value
    }

    public void setName(String name) {
        this.name.set(name); // Set name value
    }

    public StringProperty nameProperty() {
        return name; // Return name property
    }
}
