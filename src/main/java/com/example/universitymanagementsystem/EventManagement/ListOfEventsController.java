package com.example.universitymanagementsystem.EventManagement;

import com.example.universitymanagementsystem.ExcelDatabase.ExcelReader;
import com.example.universitymanagementsystem.Users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

public class ListOfEventsController {

    private User user;

    @FXML
    private TableView<Event> eventTable;

    @FXML private TableColumn<Event, String> codeColumn;
    @FXML private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, String> descriptionColumn;
    @FXML private TableColumn<Event, String> locationColumn;
    @FXML private TableColumn<Event, String> dateTimeColumn;
    @FXML private TableColumn<Event, String> capacityColumn;
    @FXML private TableColumn<Event, String> costColumn;
    @FXML private TableColumn<Event, String> headerImagesColumn;
    @FXML private TableColumn<Event, String> registeredStudentsColumn;

    @FXML
    private Button backButton;

    private final ObservableList<Event> events = FXCollections.observableArrayList();

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().eventCodeProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().eventNameProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        dateTimeColumn.setCellValueFactory(cellData -> cellData.getValue().dateTimeProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());
        costColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty());
        headerImagesColumn.setCellValueFactory(cellData -> cellData.getValue().headerImagesProperty());
        registeredStudentsColumn.setCellValueFactory(cellData -> cellData.getValue().registeredStudentsProperty());

        loadEvents();
    }

    private void loadEvents() {
        String filePath = "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx";
        ExcelReader.readExcelEvent(events, filePath);
        eventTable.setItems(events);
    }

    /**
     * Back button action to return to EventManagement.fxml
     */
    @FXML
    private void goBackToEvents(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/EventManagement.fxml"));
        Parent root = loader.load();

        EventManagementController controller = loader.getController();
        controller.setUser(user); // Pass the user

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setFullScreen(true);
        stage.setTitle("Events Page");
        stage.show();
    }
}
