package com.example.universitymanagementsystem;

//Import Statement
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

public class StudentManagementController {
    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn< Student, String> idColumn;

    @FXML
    private TableColumn< Student, String> nameColumn;

    @FXML
    private TableColumn< Student, String> emailColumn;

    @FXML
    private TableColumn< Student, String> levelColumn;
    @FXML
    private TextField studentIdField;

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private
    ComboBox<String> academicLevelBox;
    // List to store students dynamically
    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    public void initialize(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        levelColumn.setCellValueFactory(cellData -> cellData.getValue().academicLevelProperty());

        studentTable.setItems(studentList);
    }
}












