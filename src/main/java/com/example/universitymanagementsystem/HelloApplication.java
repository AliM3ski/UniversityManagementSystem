package com.example.universitymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Opens the fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/universitymanagementsystem/Login/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); //Creates a new scene
        stage.setTitle("Login"); //Changes the title of the window to login
        stage.setScene(scene); //Associates the Scene with the stage
        stage.setMaximized(true);
        stage.show(); //Displays
        
    }

    public static void main(String[] args) {
        launch();
    }
}