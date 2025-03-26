package com.example.universitymanagementsystem.EventManagement;

import com.example.universitymanagementsystem.DashBoard.DashBoardController;
import com.example.universitymanagementsystem.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import jfxtras.scene.control.CalendarPicker;
import java.util.Calendar;

public class EventManagementController implements Initializable {

    private User user;

    @FXML
    private Button manageEventsButton;

    @FXML
    private VBox calendarContainer; // Container for the calendar UI

    private CalendarPicker calendarPicker; // JFXtras Calendar

    public void setUser(User user) {
        this.user = user;
        checkAdminStatus();
    }

    private void checkAdminStatus() {
        if (user != null) {
            switch (user.getUserType()) {
                case "Admin":
                    manageEventsButton.setDisable(false);
                    manageEventsButton.setVisible(true);
                    break;
                case "Faculty":
                case "Student":
                    manageEventsButton.setDisable(true);
                    manageEventsButton.setVisible(false);
                    break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Event Management Page Initialized!");

        // Initialize JFXtras Agenda Calendar
        calendarPicker = new CalendarPicker()   ;
        calendarPicker.setCalendar(Calendar.getInstance());

        // Add agenda to UI
        if (calendarContainer != null) {
            calendarContainer.getChildren().add(calendarPicker);
        }
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
        DashBoardController dashboardController = loader.getController();
        dashboardController.setUser(user);
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
