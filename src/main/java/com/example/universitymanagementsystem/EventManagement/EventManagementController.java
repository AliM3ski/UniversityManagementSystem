package com.example.universitymanagementsystem.EventManagement;

import com.example.universitymanagementsystem.DashBoard.DashBoardController;
import com.example.universitymanagementsystem.Users.User;
import com.example.universitymanagementsystem.moveBetweenInterfaces;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import jfxtras.scene.control.CalendarPicker;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Controller class for the Event Management page.
 * Handles calendar display and navigation to seminar and workshop pages,
 * as well as access control based on user role.
 */
public class EventManagementController implements Initializable {

    private User user;

    // AnchorPane that acts as the main container for this screen
    @FXML
    public AnchorPane contentPane;

    // Button used to open the "Manage Events" screen (Admin only)
    @FXML
    private Button manageEventsButton;

    // VBox container in the FXML that will hold the calendar
    @FXML
    private VBox calendarContainer;

    // CalendarPicker from JFXtras library to show a calendar widget
    private CalendarPicker calendarPicker;

    /**
     * Sets the current user and configures access based on their role.
     *
     * @param user The currently logged-in user
     */
    public void setUser(User user) {
        this.user = user;
        checkAdminStatus();
    }

    /**
     * Enables or disables the "Manage Events" button based on user type.
     * Only Admin users can access the Manage Events screen.
     */
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

    /**
     * Initializes the controller after the FXML is loaded.
     * Adds a JFXtras calendar to the UI.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Event Management Page Initialized!");

        // Create a calendar picker and set it to the current date
        calendarPicker = new CalendarPicker();
        calendarPicker.setCalendar(Calendar.getInstance());

        // Add the calendar picker to the UI container
        if (calendarContainer != null) {
            calendarContainer.getChildren().add(calendarPicker);
        }
    }

    /**
     * Loads and displays the Seminar screen in full screen.
     */
    @FXML
    public void launchSeminar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/Seminar.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Seminar");
        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * Loads and displays the Research Workshop Info screen in full screen.
     */
    @FXML
    public void launchResearchWorkshopInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/ResearchWorkshopInfo.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Research Workshop Info");
        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * Opens the Manage Events page inside the content pane.
     * Triggered by clicking on the "Manage Events" card or button.
     */
    @FXML
    public void openManageEvents(MouseEvent event) throws IOException {
        moveBetweenInterfaces.openManageEvents(user, contentPane);
    }

    /**
     * Opens the List of Events page inside the content pane.
     * Triggered by clicking the "List of Events" card or button.
     */
    @FXML
    public void openListOfEvents(ActionEvent event) throws IOException {
        moveBetweenInterfaces.openListOfEvents(user, contentPane);
    }
}
