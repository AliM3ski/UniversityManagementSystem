package com.example.universitymanagementsystem.EventManagement;

import com.example.universitymanagementsystem.SubjectManagement.SubjectManagementController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class EventManagementController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize event data or UI elements
        System.out.println("Event Management Page Initialized Successfully!");
    }

    @FXML
    public void launchSeminar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/Seminar.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Seminar");
        stage.show();
    }

    @FXML
    public void launchResearchWorkshopInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/ResearchWorkshopInfo.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Research Workshop Info");
        stage.show();
    }

    @FXML
    public void backToDashBoard(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void openManageEvents(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/ManagingEvents.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Manage Events");
        stage.show();
    }
}
