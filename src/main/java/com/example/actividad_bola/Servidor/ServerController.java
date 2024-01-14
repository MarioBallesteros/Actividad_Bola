package com.example.actividad_bola.Servidor;

import com.example.actividad_bola.Cliente.Cliente;
import com.example.actividad_bola.Elementos.Ball;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

public class ServerController {

    @FXML
    private ImageView imageBola;
    private List<Cliente> clientes;
    private Ball bola;
    private Server servidor;

    @FXML
    public void initialize() {
        bola = new Ball(imageBola,servidor);
    }

    @FXML
    protected void onStartButtonClick() {
        bola.setMoviendo(!bola.estaMoviendo());

        if (bola.estaMoviendo()) {
            if (servidor != null) {
                servidor.cancel();
            }

            // Configurar el servidor con la lista de clientes
            if (servidor != null) {
                servidor.setClientes(clientes);
            } else {
                servidor = new Server(36089);
                servidor.setClientes(clientes);
                servidor.setServerController(this);  // Establecer el controlador
                Thread hiloServidor = new Thread(servidor);
                hiloServidor.setDaemon(true);
                hiloServidor.start();
            }

            bola = new Ball(imageBola, servidor);
            Thread hiloBola = new Thread(bola);
            hiloBola.setDaemon(true);
            hiloBola.start();
        } else {
            if (servidor != null) {
                servidor.cancel();
            }
        }
    }

        public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void onClientConnected(Cliente cliente) {
        System.out.println("Nuevo cliente conectado: " + cliente.getMessage());
    }
}
