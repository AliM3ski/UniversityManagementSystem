package com.example.universitymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {
    @FXML
    private Button menubutton;

    @FXML
    public void launchSubjectManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SubjectManagement.fxml"));
        Parent root = loader.load();

        // Get the current stage from the event source
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("SubjectManagement");
        stage.show();
    }
}
