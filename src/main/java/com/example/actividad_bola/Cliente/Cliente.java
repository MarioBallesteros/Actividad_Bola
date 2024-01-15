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
            System.out.printf("Creado socket de datagramas para puerto %s.\n", numPuerto);

            enviarMensajeAlServidor("hola",hostAddress,numPuerto);

            while (true) {
                byte[] datosRecibidos = new byte[MAX_BYTES];
                DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);

                clientSocket.receive(paqueteRecibido);

                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength(), COD_TEXTO);
                InetAddress IPCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                System.out.printf("Recibido datagrama de %s:%d (%s)\n", IPCliente.getHostAddress(), puertoCliente, mensaje);

                if (!clienteYaConectado(IPCliente.getHostAddress(), puertoCliente)) {
                    System.out.printf("¡Hola! Nuevo cliente conectado desde %s:%d\n", IPCliente.getHostAddress(), puertoCliente);
                }

                String[] coordenadas = mensaje.split(",");
                double posX = Double.parseDouble(coordenadas[0]);
                double posY = Double.parseDouble(coordenadas[1]);

                Platform.runLater(() -> clienteController.recibirPosicionPelota(posX, posY));
            }
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

    public ClienteController getClienteController() {
        return clienteController;
    }
}
