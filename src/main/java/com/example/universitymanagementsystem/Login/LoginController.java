package com.example.universitymanagementsystem.Login;

import com.example.universitymanagementsystem.DashBoard.DashBoardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    // This method will be triggered when the login button is clicked
    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isAdmin;

        // Example validation
        if (username.equals("admin") && password.equals("password")) {
            // user logged in as admin
            isAdmin = true;
            openDashBoard(event, isAdmin);
        } else if (username.equals("user") && password.equals("password")) {
            isAdmin = false;
            openDashBoard(event, isAdmin);
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openDashBoard(ActionEvent event, boolean isAdmin) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));
        Parent root = loader.load();

        // Get the controller and set the isAdmin value
        DashBoardController dashBoardController = loader.getController();
        dashBoardController.setIsAdmin(isAdmin);



        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("DashBoard");
        stage.show();
    }
}
