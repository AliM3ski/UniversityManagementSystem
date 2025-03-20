package com.example.universitymanagementsystem.EventManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The ManagingEventsController handles the creation, updating, deletion,
 * and viewing of university events. It provides role-based management options
 * for administrators to maintain event records efficiently.
 */
public class ManagingEventsController {

    // UI Components for Event Management
    @FXML
    private ComboBox<String> eventComboBox;      // Dropdown for selecting existing events (used for updates)
    @FXML
    private ComboBox<String> deleteEventComboBox; // Dropdown for selecting events to delete

    @FXML
    private TextField eventTitle, newTitle, eventDate;  // Text fields for adding or updating events

    @FXML
    private TextArea eventDescription, newDescription; // Descriptions for new and updated events

    @FXML
    private ImageView imagePreview;  // Displays uploaded images for event posters

    // Data Structure to Hold Events
    private Map<String, String> eventDetails = new HashMap<>();

    /**
     * Navigates back to the dashboard page.
     * Ensures the system maintains the correct scene flow.
     *
     * @param event ActionEvent triggered by the back button
     * @throws IOException If the FXML file fails to load
     */
    @FXML
    public void backToDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }

    /**
     * Adds a new event to the system.
     * Ensures the title and date fields are filled before adding the event.
     *
     * @param event ActionEvent triggered by the "Add Event" button
     */
    @FXML
    public void addEvent(ActionEvent event) {
        String title = eventTitle.getText();
        String description = eventDescription.getText();
        String date = eventDate.getText();

        if (!title.isEmpty() && !date.isEmpty()) {
            // Add event details to internal data structure
            eventDetails.put(title, "Description: " + description + " | Date: " + date);

            // Populate ComboBoxes with the new event for management options
            eventComboBox.getItems().add(title);
            deleteEventComboBox.getItems().add(title);

            showAlert("Success", "Event added successfully.");
        } else {
            showAlert("Error", "Please fill in all fields.");
        }
    }

    /**
     * Updates an existing event's details.
     * Ensures the new title field is filled before updating the event.
     *
     * @param event ActionEvent triggered by the "Update Event" button
     */
    @FXML
    public void updateEvent(ActionEvent event) {
        String selectedEvent = eventComboBox.getValue();

        if (selectedEvent != null) {
            String newTitleText = newTitle.getText();
            String newDescriptionText = newDescription.getText();

            if (!newTitleText.isEmpty()) {
                // Remove old entry and add the updated details
                eventDetails.remove(selectedEvent);
                eventDetails.put(newTitleText, "Description: " + newDescriptionText);

                // Update ComboBox entries to reflect the change
                eventComboBox.getItems().remove(selectedEvent);
                eventComboBox.getItems().add(newTitleText);
                deleteEventComboBox.getItems().remove(selectedEvent);
                deleteEventComboBox.getItems().add(newTitleText);

                showAlert("Success", "Event updated successfully.");
            } else {
                showAlert("Error", "New event title cannot be empty.");
            }
        } else {
            showAlert("Error", "Please select an event to update.");
        }
    }

    /**
     * Uploads an image to serve as a visual aid for event information.
     * Utilizes a file chooser to select image files (.png, .jpg, .jpeg).
     *
     * @param event ActionEvent triggered by the "Upload Image" button
     */
    @FXML
    public void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        // File chooser opens a dialog window for selecting an image
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            // Display selected image in the ImageView component
            Image image = new Image(selectedFile.toURI().toString());
            imagePreview.setImage(image);
            showAlert("Success", "Image uploaded successfully.");
        } else {
            showAlert("Error", "No image selected.");
        }
    }

    /**
     * Deletes a selected event from the system.
     * Ensures the selected event exists before deleting.
     *
     * @param event ActionEvent triggered by the "Delete Event" button
     */
    @FXML
    public void deleteEvent(ActionEvent event) {
        String selectedEvent = deleteEventComboBox.getValue();

        if (selectedEvent != null) {
            // Remove the selected event from both data storage and ComboBoxes
            eventDetails.remove(selectedEvent);
            eventComboBox.getItems().remove(selectedEvent);
            deleteEventComboBox.getItems().remove(selectedEvent);

            showAlert("Success", "Event deleted successfully.");
        } else {
            showAlert("Error", "Please select an event to delete.");
        }
    }

    /**
     * Utility method to show alert messages for success, errors, or notifications.
     *
     * @param title   Title of the alert dialog
     * @param message Message to display in the alert dialog
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
