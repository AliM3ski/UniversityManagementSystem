package com.example.universitymanagementsystem;

import com.example.universitymanagementsystem.CourseManagement.CourseManagementController;
import com.example.universitymanagementsystem.DashBoard.DashBoardController;
import com.example.universitymanagementsystem.EventManagement.EventManagementController;
import com.example.universitymanagementsystem.FacultyManagement.FacultyManagementController;
import com.example.universitymanagementsystem.StudentManagement.StudentManagementController;
import com.example.universitymanagementsystem.SubjectManagement.SubjectManagementController;
import com.example.universitymanagementsystem.Users.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class moveBetweenInterfaces {

    public static void openDashBoard(User user, AnchorPane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(moveBetweenInterfaces.class.getResource("/com/example/universitymanagementsystem/DashBoard/DashBoard.fxml"));
        Node newContent = loader.load();

        // Get the controller and set the user object
        DashBoardController dashBoardController = loader.getController();
        dashBoardController.setUser(user);

        // Replace the content inside contentPane without changing the scene
        contentPane.getChildren().setAll(newContent);
    }

    public static void launchSubjectManagement(User user, AnchorPane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(moveBetweenInterfaces.class.getResource("/com/example/universitymanagementsystem/SubjectManagement/SubjectManagement.fxml"));
        Node newContent = loader.load();

        // Get the controller and set the user object
        SubjectManagementController subjectManagementController = loader.getController();
        subjectManagementController.setUser(user);

        contentPane.getChildren().setAll(newContent);
    }

    public static void launchEventManagement(User user, AnchorPane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(moveBetweenInterfaces.class.getResource("/com/example/universitymanagementsystem/EventManagement/EventManagement.fxml"));
        Node newContent = loader.load();

        // Get the controller and set the user object
        EventManagementController eventManagementController = loader.getController();
        eventManagementController.setUser(user);

        contentPane.getChildren().setAll(newContent);

    }

    public static void launchStudentManagement(User user, AnchorPane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(moveBetweenInterfaces.class.getResource("/com/example/universitymanagementsystem/StudentManagement/StudentManagement.fxml"));
        Node newContent = loader.load();

        // Get the controller and set the user object
        StudentManagementController studentManagementController = loader.getController();
        studentManagementController.setUser(user);

        contentPane.getChildren().setAll(newContent);

    }

    public static void launchCourseManagement(User user, AnchorPane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(moveBetweenInterfaces.class.getResource("/com/example/universitymanagementsystem/CourseManagement/CourseManagement.fxml"));
        Node newContent = loader.load();

        // Get the controller and set the user object
        CourseManagementController courseManagementController = loader.getController();
        courseManagementController.setUser(user);

        contentPane.getChildren().setAll(newContent);

    }
    public static void launchFacultyManagement(User user, AnchorPane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(moveBetweenInterfaces.class.getResource("/com/example/universitymanagementsystem/FacultyManagement/FacultyManagement.fxml"));
        Node newContent= loader.load();

        // Get the controller and set the user object
        FacultyManagementController facultyManagementController = loader.getController();
        facultyManagementController.setUser(user);

        contentPane.getChildren().setAll(newContent);


    }
    public static void signOut(User user, AnchorPane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(moveBetweenInterfaces.class.getResource("/com/example/universitymanagementsystem/Login/Login.fxml"));
        Node newContent = loader.load();

        contentPane.getChildren().setAll(newContent);
    }
    public static void openManageEvents(User user, AnchorPane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(moveBetweenInterfaces.class.getResource("/com/example/universitymanagementsystem/EventManagement/ManagingEvents.fxml"));
        Node newContent = loader.load();
        contentPane.getChildren().setAll(newContent);
    }

    public static void openListOfEvents(User user, AnchorPane contentPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(moveBetweenInterfaces.class.getResource("/com/example/universitymanagementsystem/EventManagement/ListOfEvents.fxml"));
        Node newContent = loader.load();
        contentPane.getChildren().setAll(newContent);
    }
}
