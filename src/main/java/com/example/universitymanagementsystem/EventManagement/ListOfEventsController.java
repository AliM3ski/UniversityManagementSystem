package com.example.universitymanagementsystem.EventManagement;

import com.example.universitymanagementsystem.ExcelDatabase.ExcelReader;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelWriter;
import com.example.universitymanagementsystem.SubjectManagement.Subject;
import com.example.universitymanagementsystem.Users.User;
import com.example.universitymanagementsystem.moveBetweenInterfaces;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ListOfEventsController {

    private User user;

    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, String> codeColumn;

    @FXML
    private TableColumn<Event, String> nameColumn;

    @FXML
    private TableColumn<Event, String> descriptionColumn;

    @FXML
    private TableColumn<Event, String> locationColumn;

    @FXML
    private TableColumn<Event, String> dateTimeColumn;

    @FXML
    private TableColumn<Event, String> capacityColumn;

    @FXML
    private TableColumn<Event, String> costColumn;

    @FXML
    private TableColumn<Event, String> headerImagesColumn;

    @FXML
    private TableColumn<Event, String> registeredStudentsColumn;

    // Method to set user (if needed)
    public void setUser(User user) {
        this.user = user;
    }

    // List to store events dynamically
    private ObservableList<Event> events = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind columns using lambda expressions
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().eventCodeProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().eventNameProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        dateTimeColumn.setCellValueFactory(cellData -> cellData.getValue().dateTimeProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());
        costColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty());
        headerImagesColumn.setCellValueFactory(cellData -> cellData.getValue().headerImagesProperty());
        registeredStudentsColumn.setCellValueFactory(cellData -> cellData.getValue().registeredStudentsProperty());

        // Load event data
        loadEvents();
    }

    private void loadEvents() {
        String filePath = "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx";
        ExcelReader.readExcelEvent(events, filePath);
        eventTable.setItems(events);
    }
}