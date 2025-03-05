package com.example.universitymanagementsystem.DashBoard;

import com.example.universitymanagementsystem.CourseManagement.CourseManagementController;
import com.example.universitymanagementsystem.FacultyManagement.FacultyManagementController;
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

    private boolean isAdmin;

    // Method to set the isAdmin value
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        checkAdminStatus(); // Call a method to handle logic after setting isAdmin
    }

    private void checkAdminStatus() {
        if (isAdmin) {
            System.out.println("User is an admin.");
        } else {
            System.out.println("User is not an admin.");
        }
    }

    @FXML
    public void launchSubjectManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/SubjectManagement/SubjectManagement.fxml"));
        Parent root = loader.load();

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

        // Get the controller and set the isAdmin value
        CourseManagementController courseManagementController = loader.getController();
        courseManagementController.setIsAdmin(isAdmin);

        // Get the current stage from the event source
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("CourseManagement");
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

    @FXML
    public void launchFacultyManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/universitymanagementsystem/FacultyManagement/FacultyManagement.fxml"));
        Parent root = loader.load();

        // Get the controller and set the isAdmin value
        FacultyManagementController facultyManagementController = loader.getController();
        facultyManagementController.setIsAdmin(isAdmin);



        // Get the current stage from the event source
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.setTitle("FacultyManagement");
        stage.show();
    }
}
