package com.example.universitymanagementsystem.EventManagement;

// Imports necessary JavaFX libraries and other Java utilities
import com.example.universitymanagementsystem.DashBoard.DashBoardController;
import com.example.universitymanagementsystem.Users.User;
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
import javafx.fxml.Initializable;  // Interface for initializing controller classes

// Controller class for managing the Event Management Page
public class EventManagementController implements Initializable {

    // User object to store user information
    private User user;

    // FXML annotation binds the manageEventsButton with its corresponding UI element
    @FXML
    private Button manageEventsButton;

    /**
     * Method to set the user object
     * @param user - User object containing user information
     */
    public void setUser(User user) {
        this.user = user;
        checkAdminStatus();  // Call the method to adjust UI for admin/non-admin
    }

    /**
     * Ensures only admins can access certain functionalities.
     * If the user is not an admin, the "Manage Events" button is hidden and disabled.
     */
    private void checkAdminStatus() {
        switch (user.getUserType()) {
            case "Admin":
                System.out.println("Admin can manage events.");
                manageEventsButton.setDisable(false); // Enables the button for admins
                manageEventsButton.setVisible(true); // Shows the button for admins
                break;
            case "Faculty":
            case "Student":
                System.out.println("User cannot manage events.");
                manageEventsButton.setDisable(true); // Disables the button for non-admins
                manageEventsButton.setVisible(false); // Hides the button for non-admins
                break;
        }
    }

    /**
     * Initializes the Event Management Page and prepares necessary data.
     * This method is called automatically when the controller is loaded.
     * @param url - URL used to resolve paths for the root object
     * @param resourceBundle - Resource bundle for localization support
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Event Management Page Initialized Successfully!");
    }

    /**
     * Launches the Seminar page when the user clicks the corresponding button.
     * @param event - Action event triggered by the button click
     */
    @FXML
    public void launchSeminar(ActionEvent event) throws IOException {
        // Load the Seminar.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/Seminar.fxml"));
        Parent root = loader.load();

        // Retrieve the current stage and update it with the Seminar scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Seminar");
        stage.show();
    }

    /**
     * Launches the Research Workshop Info page when the corresponding button is clicked.
     * @param event - Action event triggered by the button click
     */
    @FXML
    public void launchResearchWorkshopInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/ResearchWorkshopInfo.fxml"));
        Parent root = loader.load();

        // Retrieve the current stage and update it with the Research Workshop Info scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Research Workshop Info");
        stage.show();
    }

    /**
     * Navigates the user back to the Dashboard page when they click the "Back to Dashboard" button.
     * @param event - Mouse event triggered by clicking the button
     */
    @FXML
    public void backToDashBoard(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));
        Parent root = loader.load();

        // Pass the user object back to the dashboard
        DashBoardController dashboardController = loader.getController();
        dashboardController.setUser(user);

        // Retrieve the current stage and update it with the Dashboard scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }

    /**
     * Opens the "Manage Events" page. This functionality is restricted to admin users only.
     * @param event - Mouse event triggered by clicking the button
     */
    @FXML
    public void openManageEvents(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/ManagingEvents.fxml"));
        Parent root = loader.load();

        // Retrieve the current stage and update it with the Manage Events scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Manage Events");
        stage.show();
    }
}