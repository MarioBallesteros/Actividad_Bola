package com.example.actividad_bola.Cliente;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente extends Task<Void> {

    private final static int MAX_BYTES = 1500;
    private final static String COD_TEXTO = "UTF-8";

    private DatagramSocket clientSocket;
    private InetAddress ipCliente;
    private int numPuerto;
    private String hostAddress;
    private ClienteController clienteController;

    public Cliente(String hostAddress, int numPuerto) {
        this.hostAddress = hostAddress;
        this.numPuerto = numPuerto;
        this.clienteController = new ClienteController();
    }

    @Override
    protected Void call() {
        try {
            clientSocket = new DatagramSocket();

            enviarMensajeAlServidor("hola",hostAddress,numPuerto);

        } catch (IOException ex) {
            System.out.println("Excepción de E/S en el cliente UDP");
            ex.printStackTrace();
        } finally {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        }

        return null;
    }

    private boolean clienteYaConectado(String hostAddress, int puerto) {
        return false;
    }

    public void enviarMensajeAlServidor(String mensaje, String servidorIP, int servidorPuerto) {
        try {
            InetAddress servidorAddress = InetAddress.getByName(servidorIP);
            byte[] bMensaje = mensaje.getBytes(COD_TEXTO);
            DatagramPacket paqueteMensaje = new DatagramPacket(bMensaje, bMensaje.length, servidorAddress, servidorPuerto);
            clientSocket.send(paqueteMensaje);
        } catch (IOException e) {
            System.out.println("Error al enviar mensaje al servidor");
            e.printStackTrace();
        }
    }


    public void setClienteController(ClienteController clienteController) {
        this.clienteController = clienteController;
    }

    public InetAddress getIpCliente() {
        return clientSocket.getInetAddress();
    }

    get
    public ClienteController getClienteController() {
        return clienteController;
    }
}
