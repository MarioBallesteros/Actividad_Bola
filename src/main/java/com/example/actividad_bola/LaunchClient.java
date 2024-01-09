package com.example.actividad_bola;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchClient extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LaunchClient.class.getResource("cliente-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Cliente");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
