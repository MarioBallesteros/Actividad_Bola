package com.example.actividad_bola.Cliente;

import com.example.actividad_bola.Elementos.Ball;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    @FXML
    private ImageView imageBola;
    private Ball bola;

    @FXML
    private TextField ipTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private Button connectButton;

    private Cliente udpCliente;
    private List<Cliente> clientes = new ArrayList<>();



    @FXML
    protected void onConnectButtonClick() {
        try {
            if (udpCliente != null) {
                udpCliente.cancel();
                udpCliente = null;
            }

            udpCliente = new Cliente(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
            udpCliente.setClienteController(this);

            bola= new Ball(imageBola);

            Thread clientThread = new Thread(udpCliente);
            clientThread.setDaemon(true);
            clientThread.start();

            connectButton.setText("Desconectar");
        } catch (NumberFormatException e) {
            System.out.println("El puerto no es válido");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void recibirPosicionPelota(double posX, double posY) {
        imageBola.setLayoutX(posX);
        imageBola.setLayoutY(posY);
        System.out.println("Posición de la pelota recibida: X=" + posX + ", Y=" + posY);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}