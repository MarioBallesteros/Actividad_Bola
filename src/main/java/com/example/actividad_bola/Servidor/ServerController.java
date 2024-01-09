package com.example.actividad_bola.Servidor;

import com.example.actividad_bola.Cliente.Cliente;
import com.example.actividad_bola.Elementos.Ball;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.concurrent.Task;

import java.util.List;

public class ServerController {

    @FXML
    private ImageView imageBola;
    private List<Cliente> clientes;
    private Ball bola;
    private Task<Void> servidorUDP;

    @FXML
    public void initialize() {
        bola = new Ball(imageBola);
    }

    @FXML
    protected void onStartButtonClick() {
        if (bola.estaMoviendo()) {
            // Iniciar el servidor UDP solo si la bola está en movimiento
            servidorUDP = new Server(12345, clientes);
            Thread hiloServidor = new Thread(servidorUDP);
            hiloServidor.setDaemon(true);
            hiloServidor.start();
        }

        bola.setMoviendo(!bola.estaMoviendo());
        if (!bola.estaMoviendo() && servidorUDP != null) {
            servidorUDP.cancel();
        }

        if (!bola.estaMoviendo()) {
            bola = new Ball(imageBola);
            Thread hiloBola = new Thread(bola);
            hiloBola.setDaemon(true);
            hiloBola.start();
        }
    }
}
