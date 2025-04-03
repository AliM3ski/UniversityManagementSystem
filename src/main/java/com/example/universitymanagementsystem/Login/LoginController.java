package com.example.universitymanagementsystem.Login;

import com.example.universitymanagementsystem.moveBetweenInterfaces;
import com.example.universitymanagementsystem.Users.Admin;
import com.example.universitymanagementsystem.Users.Faculty;
import com.example.universitymanagementsystem.Users.Student;
import com.example.universitymanagementsystem.Users.User;
import com.example.universitymanagementsystem.ExcelDatabase.ExcelReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import static com.example.universitymanagementsystem.ExcelDatabase.ExcelReader.readExcelFaculty;
import static com.example.universitymanagementsystem.ExcelDatabase.ExcelReader.readExcelStudentIDs;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    public AnchorPane contentPane;

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
        String filePath = "src\\main\\java\\com\\example\\universitymanagementsystem\\ExcelDatabase\\UMS_Data.xlsx";
        // Example validation logic
        if (username.equals("admin") && password.equals("password")) {
            return new Admin(username, password); // Admin user
        } else if (readExcelFaculty(filePath, username, password)) {
            return new Faculty(username, password); // Faculty user
        } else if (readExcelStudentIDs(filePath, username, password)) {
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
        moveBetweenInterfaces.openDashBoard(user, contentPane);

    }

}