package com.example.universitymanagementsystem.DashBoard;

import com.example.universitymanagementsystem.CourseManagement.CourseManagementController;
import com.example.universitymanagementsystem.FacultyManagement.FacultyManagementController;
import com.example.universitymanagementsystem.SubjectManagement.SubjectManagementController;
import com.example.universitymanagementsystem.StudentManagement.StudentManagementController;
import com.example.universitymanagementsystem.EventManagement.EventManagementController;
import com.example.universitymanagementsystem.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {
    @FXML
    private Button menubutton;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/SubjectManagement/SubjectManagement.fxml"));
        Parent root = loader.load();

        // Get the controller and set the user object
        SubjectManagementController subjectManagementController = loader.getController();
        subjectManagementController.setUser(user);

        // Get the current stage from the event source
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("SubjectManagement");
        stage.show();
    }

    @FXML
    public void launchEventManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/EventManagement/EventManagement.fxml"));
        Parent root = loader.load();

        // Get the controller and set the user object
        EventManagementController eventManagementController = loader.getController();
        eventManagementController.setUser(user);

        // Get the current stage from the event source
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("EventManagement");
        stage.show();
    }

    @FXML
    public void launchStudentManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/StudentManagement/StudentManagement.fxml"));
        Parent root = loader.load();

        // Get the controller and set the user object
        StudentManagementController studentManagementController = loader.getController();
        studentManagementController.setUser(user);

        // Get the current stage from the event source
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("StudentManagement");
        stage.show();
    }

    @FXML
    public void launchCourseManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/CourseManagement/CourseManagement.fxml"));
        Parent root = loader.load();

        // Get the controller and set the user object
        CourseManagementController courseManagementController = loader.getController();
        courseManagementController.setUser(user);

        // Get the current stage from the event source
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("CourseManagement");
        stage.show();
    }

    @FXML
    public void launchFacultyManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/FacultyManagement/FacultyManagement.fxml"));
        Parent root = loader.load();

        // Get the controller and set the user object
        FacultyManagementController facultyManagementController = loader.getController();
        facultyManagementController.setUser(user);

        // Get the current stage from the event source
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("FacultyManagement");
        stage.show();
    }

    @FXML
    public void signOut(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/Login/Login.fxml"));
        Parent root = loader.load();

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }
}