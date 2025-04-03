package com.example.universitymanagementsystem.EventManagement;

import com.example.universitymanagementsystem.Users.User;
import com.example.universitymanagementsystem.moveBetweenInterfaces;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for the Event Management admin panel.
 * This class provides functionality for administrators to:
 * - Add new events
 * - Edit existing events
 * - Delete events
 * - Upload a poster/image for an event
 */
public class ManagingEventsController {

    // ===== UI Components (linked from FXML) =====

    @FXML
    private ComboBox<String> eventComboBox;           // Used to select an event to edit
    @FXML
    private ComboBox<String> deleteEventComboBox;     // Used to select an event to delete

    @FXML
    private TextField eventCode, eventTitle, eventLocation, eventDateTime, eventCapacity, eventCost; // Fields for adding a new event
    @FXML
    private TextArea eventDescription;                // Description field for new event
    @FXML
    private ImageView imagePreview;                   // Shows uploaded image

    // Fields for editing an existing event
    @FXML
    private TextField newTitle, newLocation, newDateTime, newCapacity, newCost;
    @FXML
    private TextArea newDescription;

    @FXML
    public AnchorPane contentPane;                    // AnchorPane container to support UI switching/navigation

    private User user;                                // Stores the logged-in user
    private List<Event> eventList = new ArrayList<>(); // In-memory list of created events (can later be connected to a database)


    /**
     * Assigns the currently logged-in user to this controller.
     * This method can be used by other controllers to pass context.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Navigates back to the main Event Management page.
     * Triggered by "Back to Events" button.
     */
    @FXML
    public void launchEventManagement(ActionEvent event) throws IOException {
        moveBetweenInterfaces.launchEventManagement(user, contentPane);
    }

    /**
     * Adds a new event based on form inputs.
     * - Checks if required fields are filled
     * - Creates a new Event object
     * - Adds it to the internal list
     * - Updates the edit/delete ComboBoxes with the new event
     * - Displays success or error alert
     */
    @FXML
    public void addEvent(ActionEvent event) {
        String code = eventCode.getText();
        String title = eventTitle.getText();
        String description = eventDescription.getText();
        String location = eventLocation.getText();
        String dateTime = eventDateTime.getText();
        String capacity = eventCapacity.getText();
        String cost = eventCost.getText();

        if (!code.isEmpty() && !title.isEmpty() && !dateTime.isEmpty()) {
            // Create a new event and store it
            Event newEvent = new Event(code, title, description, location, dateTime, capacity, cost, "", "");
            eventList.add(newEvent);

            // Update ComboBoxes for editing and deleting
            eventComboBox.getItems().add(title);
            deleteEventComboBox.getItems().add(title);

            showAlert("Success", "Event added successfully.");
        } else {
            showAlert("Error", "Please fill in all required fields.");
        }
    }

    /**
     * Updates the information of an existing event.
     * - Finds the selected event from the ComboBox
     * - Replaces only fields that were filled in
     * - Updates the ComboBox labels with the new name if changed
     */
    @FXML
    public void updateEvent(ActionEvent event) {
        String selectedEvent = eventComboBox.getValue();

        if (selectedEvent != null) {
            for (Event e : eventList) {
                if (e.getEventName().equals(selectedEvent)) {
                    // Update fields only if new values are provided
                    if (!newTitle.getText().isEmpty()) e.setEventName(newTitle.getText());
                    if (!newDescription.getText().isEmpty()) e.setDescription(newDescription.getText());
                    if (!newLocation.getText().isEmpty()) e.setLocation(newLocation.getText());
                    if (!newDateTime.getText().isEmpty()) e.setDateTime(newDateTime.getText());
                    if (!newCapacity.getText().isEmpty()) e.setCapacity(newCapacity.getText());
                    if (!newCost.getText().isEmpty()) e.setCost(newCost.getText());

                    // Update ComboBox displays
                    eventComboBox.getItems().remove(selectedEvent);
                    eventComboBox.getItems().add(e.getEventName());

                    deleteEventComboBox.getItems().remove(selectedEvent);
                    deleteEventComboBox.getItems().add(e.getEventName());

                    showAlert("Success", "Event updated successfully.");
                    return;
                }
            }
        } else {
            showAlert("Error", "Please select an event to update.");
        }
    }

    /**
     * Opens a file chooser to upload an image for the selected event.
     * The selected image is displayed in an ImageView as a preview.
     */
    @FXML
    public void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imagePreview.setImage(image);
            showAlert("Success", "Image uploaded successfully.");
        } else {
            showAlert("Error", "No image selected.");
        }
    }

    /**
     * Deletes an event from the system.
     * - Removes from internal list
     * - Removes from both ComboBoxes
     */
    @FXML
    public void deleteEvent(ActionEvent event) {
        String selectedEvent = deleteEventComboBox.getValue();

        if (selectedEvent != null) {
            eventList.removeIf(e -> e.getEventName().equals(selectedEvent));

            eventComboBox.getItems().remove(selectedEvent);
            deleteEventComboBox.getItems().remove(selectedEvent);

            showAlert("Success", "Event deleted successfully.");
        } else {
            showAlert("Error", "Please select an event to delete.");
        }
    }

    /**
     * Utility method to show an information alert to the user.
     * Useful for displaying success and error messages.
     *
     * @param title   The title of the alert window
     * @param message The message body to display
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);  // No header, just the title and content
        alert.setContentText(message);
        alert.showAndWait();
    }
}
