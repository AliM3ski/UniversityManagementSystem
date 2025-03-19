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

public class ManagingEventsController {

    @FXML
    private ComboBox<String> eventComboBox;

    @FXML
    private ComboBox<String> deleteEventComboBox;

    @FXML
    private TextField eventTitle, newTitle, eventDate;

    @FXML
    private TextArea eventDescription, newDescription;

    @FXML
    private ImageView imagePreview;

    // Sample data structure for events
    private Map<String, String> eventDetails = new HashMap<>();

    @FXML
    public void backToDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void addEvent(ActionEvent event) {
        String title = eventTitle.getText();
        String description = eventDescription.getText();
        String date = eventDate.getText();

        if (!title.isEmpty() && !date.isEmpty()) {
            eventDetails.put(title, "Description: " + description + " | Date: " + date);
            eventComboBox.getItems().add(title);
            deleteEventComboBox.getItems().add(title);
            showAlert("Success", "Event added successfully.");
        } else {
            showAlert("Error", "Please fill in all fields.");
        }
    }

    @FXML
    public void updateEvent(ActionEvent event) {
        String selectedEvent = eventComboBox.getValue();
        if (selectedEvent != null) {
            String newTitleText = newTitle.getText();
            String newDescriptionText = newDescription.getText();

            if (!newTitleText.isEmpty()) {
                eventDetails.remove(selectedEvent);
                eventDetails.put(newTitleText, "Description: " + newDescriptionText);
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

    @FXML
    public void deleteEvent(ActionEvent event) {
        String selectedEvent = deleteEventComboBox.getValue();
        if (selectedEvent != null) {
            eventDetails.remove(selectedEvent);
            eventComboBox.getItems().remove(selectedEvent);
            deleteEventComboBox.getItems().remove(selectedEvent);
            showAlert("Success", "Event deleted successfully.");
        } else {
            showAlert("Error", "Please select an event to delete.");
        }
    }

    // Utility method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
