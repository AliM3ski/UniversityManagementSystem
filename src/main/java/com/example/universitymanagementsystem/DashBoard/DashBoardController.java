package com.example.universitymanagementsystem.DashBoard;

import com.example.universitymanagementsystem.moveBetweenInterfaces;
import com.example.universitymanagementsystem.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardController {
    @FXML
    private Button menubutton;

    @FXML
    public AnchorPane contentPane;

    private User user;

    // Method to set the user object
    public void setUser(User user) {
        this.user = user;
        configureDashboard(); // Configure the dashboard based on the user type
    }

    private void configureDashboard() {
        // Configure dashboard based on user type
        switch (user.getUserType()) {
            case "Admin":
                System.out.println("Admin dashboard");
                break;
            case "Faculty":
                System.out.println("Faculty dashboard");
                break;
            case "Student":
                System.out.println("Student dashboard");
                break;
        }
    }

    @FXML
    public void launchSubjectManagement(ActionEvent event) throws IOException {
        moveBetweenInterfaces.launchSubjectManagement(user, contentPane);
    }

    @FXML
    public void launchEventManagement(ActionEvent event) throws IOException {
        moveBetweenInterfaces.launchEventManagement(user, contentPane);
    }

    @FXML
    public void launchStudentManagement(ActionEvent event) throws IOException {
        moveBetweenInterfaces.launchStudentManagement(user, contentPane);
    }

    @FXML
    public void launchCourseManagement(ActionEvent event) throws IOException {
        moveBetweenInterfaces.launchCourseManagement(user, contentPane);
    }

    @FXML
    public void launchFacultyManagement(ActionEvent event) throws IOException {
        moveBetweenInterfaces.launchFacultyManagement(user, contentPane);
    }

    @FXML
    public void signOut(MouseEvent event) throws IOException {
        moveBetweenInterfaces.signOut(user, contentPane);
    }
}