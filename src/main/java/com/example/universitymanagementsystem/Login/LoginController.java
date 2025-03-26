package com.example.universitymanagementsystem.Login;

import com.example.universitymanagementsystem.DashBoard.DashBoardController;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelReader;
import com.example.universitymanagementsystem.Users.Admin;
import com.example.universitymanagementsystem.Users.Faculty;
import com.example.universitymanagementsystem.Users.Student;
import com.example.universitymanagementsystem.Users.User;
import com.example.universitymanagementsystem.StudentManagement.StudentManagementController;
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

        // Validate login and determine the user type
        User user = validateLogin(username, password);

        if (user != null) {
            // Open the dashboard and pass the user object
            openDashBoard(event, user);
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private User validateLogin(String username, String password) {
        // Example validation logic
        if (username.equals("admin") && password.equals("password")) {
            return new Admin(username, password); // Admin user
        } else if (username.equals("faculty") && password.equals("password")) {
            return new Faculty(username, password); // Faculty user
        } else if (username.equals("student") && password.equals("password")) {
            return new Student(username, password); // Student user
        }
        return null; // Invalid login
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openDashBoard(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));
        Parent root = loader.load();

        // Get the controller and set the user object
        DashBoardController dashBoardController = loader.getController();
        dashBoardController.setUser(user);

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("DashBoard");
        stage.show();
    }
}