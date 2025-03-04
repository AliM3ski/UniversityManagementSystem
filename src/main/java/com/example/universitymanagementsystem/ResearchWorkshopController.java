package com.example.universitymanagementsystem;  // Package declaration (update if necessary)

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

/**
 * Controller for the Research Workshop Info page in the University Management System.
 * This class handles user interactions, including:
 * - Navigating back to the Events page.
 * - Registering for the Research Workshop.
 */
public class ResearchWorkshopController {

    // FXML-linked UI elements
    @FXML
    private Button backButton;  // Button to go back to the Events page

    @FXML
    private Button registerButton;  // Button to register for the workshop

    /**
     * Handles the action when the "Back to Events" button is clicked.
     * Loads and switches the scene to "Events.fxml".
     */
    @FXML
    private void goBack() {
        try {
            // Load the Events page (Ensure "Events.fxml" exists in the resources folder)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Events.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) using the button as reference
            Stage stage = (Stage) backButton.getScene().getWindow();

            // Set the new scene with the loaded "Events.fxml"
            stage.setScene(new Scene(root));

            // Update the window title
            stage.setTitle("Events Page");

            // Show the new scene
            stage.show();
        } catch (IOException e) {
            // Print error message in case loading fails
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the "Register Now" button is clicked.
     * Displays a confirmation alert to the user.
     */
    @FXML
    private void registerForWorkshop() {
        // Create a new information alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Set alert title
        alert.setTitle("Registration Successful");

        // Remove header text (null = no header)
        alert.setHeaderText(null);

        // Set the content message shown in the alert
        alert.setContentText("You have successfully registered for the Research Workshop!");

        // Display the alert and wait for user to close it
        alert.showAndWait();
    }
}

