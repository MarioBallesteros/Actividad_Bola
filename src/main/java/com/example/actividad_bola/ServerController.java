package com.example.actividad_bola;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ServerController {

    @FXML
    private Label textToast;
    @FXML
    private ImageView imageBola;
    @FXML
    private Thread hiloBola;


    @FXML
    protected void onStartButtonClick() {
        hiloBola = new Thread(new Ball(imageBola));
        hiloBola.start();

    }

    private void move(ImageView imageBola) {
        while (true){
                //Thread.sleep(10000);
                imageBola.setX(50);
                imageBola.setY(50);

        }
    }


}