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
    private TextField eventCode, eventTitle, eventLocation, eventDateTime, eventCapacity, eventCost;
    @FXML
    private TextArea eventDescription;
    @FXML
    private ImageView imagePreview;

    // Fields for updating an event
    @FXML
    private TextField newTitle, newLocation, newDateTime, newCapacity, newCost;
    @FXML
    private TextArea newDescription;

    @FXML
    public AnchorPane contentPane;


    private User user;
    // List to hold Event objects
    private List<Event> eventList = new ArrayList<>();


    public void setUser(User user) {
        this.user = user;
    }
    @FXML
    public void launchEventManagement(ActionEvent event) throws IOException {
        moveBetweenInterfaces.launchEventManagement(user, contentPane);
    }

    /**
     * Adds a new event to the system.
     * Ensures required fields are filled before adding the event.
     *
     * @param event ActionEvent triggered by the "Add Event" button
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
            // Create new Event object and add it to the list
            Event newEvent = new Event(code, title, description, location, dateTime, capacity, cost, "", "");
            eventList.add(newEvent);

            // Populate ComboBoxes with the new event
            eventComboBox.getItems().add(title);
            deleteEventComboBox.getItems().add(title);

            showAlert("Success", "Event added successfully.");
        } else {
            showAlert("Error", "Please fill in all required fields.");
        }
    }

    /**
     * Updates an existing event's details.
     *
     * @param event ActionEvent triggered by the "Update Event" button
     */
    @FXML
    public void updateEvent(ActionEvent event) {
        String selectedEvent = eventComboBox.getValue();

        if (selectedEvent != null) {
            for (Event e : eventList) {
                if (e.getEventName().equals(selectedEvent)) {
                    // Update event properties if new values are provided
                    if (!newTitle.getText().isEmpty()) e.setEventName(newTitle.getText());
                    if (!newDescription.getText().isEmpty()) e.setDescription(newDescription.getText());
                    if (!newLocation.getText().isEmpty()) e.setLocation(newLocation.getText());
                    if (!newDateTime.getText().isEmpty()) e.setDateTime(newDateTime.getText());
                    if (!newCapacity.getText().isEmpty()) e.setCapacity(newCapacity.getText());
                    if (!newCost.getText().isEmpty()) e.setCost(newCost.getText());

                    // Update ComboBox entries
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
     * Uploads an image to serve as an event poster.
     *
     * @param event ActionEvent triggered by the "Upload Image" button
     */
    @FXML
    public void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

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
     * Deletes a selected event from the system.
     *
     * @param event ActionEvent triggered by the "Delete Event" button
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
     * Utility method to show alert messages.
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
