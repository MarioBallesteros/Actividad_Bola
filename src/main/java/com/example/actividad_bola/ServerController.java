package com.example.actividad_bola;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ServerController {
    @FXML
    private ImageView imageBola;
    @FXML
    private Thread hiloBola;

    @FXML
    public void initialize() {
        hiloBola = new Thread(new Ball(imageBola));
    }
    @FXML
    protected void onStartButtonClick() {
        if (!hiloBola.isAlive()){
            hiloBola.setDaemon(true);
            hiloBola.start();
        }

    }
}
