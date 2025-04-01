package com.example.universitymanagementsystem.EventManagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the Welcome Seminar Info page.
 * Handles navigation and seminar registration.
 */
public class WelcomeSeminarInfoController {

    @FXML
    private Button backButton;

    @FXML
    private Button registerButton;

    /**
     * Handles the "Back" button click event.
     * Navigates the user back to the Event Management page.
     */
    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/EventManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setFullScreen(true);
            stage.setTitle("Events Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the "REGISTER" button click event.
     * Shows a confirmation popup.
     */
    @FXML
    private void registerForSeminar() {
        System.out.println("REGISTER button clicked"); // Optional: Debug message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText(null);
        alert.setContentText("You have successfully registered for the Welcome Seminar!");
        alert.showAndWait();
    }
}
