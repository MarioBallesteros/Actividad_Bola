package com.example.actividad_bola.Servidor;

import com.example.actividad_bola.Cliente.Cliente;
import com.example.actividad_bola.Elementos.Ball;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.List;

public class ServerController {

    @FXML
    private ImageView imageBola;
    @FXML
    private Button startButton;
    private List<Cliente> clientes;
    private Ball bola;
    private Server servidor;

    @FXML
    public void initialize() {
        servidor = new Server(12345);
        bola = new Ball(imageBola,servidor);
    }

    @FXML
    protected void onStartButtonClick() {
        bola.setMoviendo(!bola.estaMoviendo());

        if (!bola.estaMoviendo()) {
            Thread hiloServidor = new Thread(servidor);
            Thread hiloBola = new Thread(bola);
            hiloBola.setDaemon(true);
            hiloServidor.setDaemon(true);

            servidor.setServerController(this);
            servidor.setClientes(clientes);

            hiloBola.start();
            hiloServidor.start();
        }

        if (startButton.getText().equals("Start") ){
            startButton.setText("Stop");
        }else{
            startButton.setText("Start");
        }

    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }


}
