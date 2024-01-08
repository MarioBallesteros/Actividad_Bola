package com.example.actividad_bola.Servidor;

import com.example.actividad_bola.Elementos.Ball;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ServerController {

    @FXML
    private ImageView imageBola;
    @FXML
    private Thread hiloBola;

    private Ball bola;

    @FXML
    public void initialize() {
        bola = new Ball(imageBola);
        hiloBola = new Thread(bola);
    }
    @FXML
    protected void onStartButtonClick() {
        bola.setMoviendo(!bola.estaMoviendo());
        if (!hiloBola.isAlive()){
            hiloBola.setDaemon(true);
            hiloBola.start();
        }
    }
}
