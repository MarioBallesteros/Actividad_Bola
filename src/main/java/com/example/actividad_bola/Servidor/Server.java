package com.example.actividad_bola.Servidor;

import com.example.actividad_bola.Cliente.Cliente;
import com.example.actividad_bola.Elementos.Punto;
import javafx.concurrent.Task;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Server extends Task<Void> {
    private final static int MAX_BYTES = 1500;
    private final static String COD_TEXTO = "UTF-8";

    private int numPuerto;
    private List<Cliente> clientes;
    private DatagramSocket serverSocket;
    private ServerController serverController;

    public Server(int numPuerto){
        this.numPuerto = numPuerto;
        this.clientes = new ArrayList<>();
    }

    public void setNumPuerto(int numPuerto) {
        this.numPuerto = numPuerto;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    protected Void call() {
        try {
            serverSocket = new DatagramSocket(numPuerto);
            System.out.printf("Creando Servidor para puerto %s.\n", numPuerto);

            while (true) {
                byte[] datosRecibidos = new byte[MAX_BYTES];
                DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);

                serverSocket.receive(paqueteRecibido);

                InetAddress IPCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                System.out.printf("Recibido nuevo cliente: %s:%d\n", IPCliente.getHostAddress(), puertoCliente);
                Cliente cliente = new Cliente(IPCliente.getHostAddress(), puertoCliente);
                clientes.add(cliente);

                onClientConnected(cliente);
            }
        } catch (IOException ex) {
            System.out.println("Excepción de E/S en el servidor UDP");
            ex.printStackTrace();
        }
        return null;
    }

    public void onClientConnected(Cliente cliente) {
        System.out.println("Nuevo cliente conectado: " + cliente.getIpCliente());
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void enviarPosicionPelota(double posX, double posY) {
        if (clientes != null) {
            for (Cliente cliente : clientes) {
                enviarMensajeAlCliente(cliente.getIpCliente().getHostAddress(), cliente.getPuerto(), posX, posY);
            }
        }
    }

    public void enviarMensajeAlCliente(String clienteIP, int clientePuerto, double posX, double posY) {
        try {
            InetAddress clienteAddress = InetAddress.getByName(clienteIP);
            String mensaje = posX + "," + posY;
            byte[] bMensaje = mensaje.getBytes(COD_TEXTO);
            DatagramPacket paqueteMensaje = new DatagramPacket(bMensaje, bMensaje.length, clienteAddress, clientePuerto);
            serverSocket.send(paqueteMensaje);
        } catch (IOException e) {
            System.out.println("Error al enviar mensaje al cliente");
            e.printStackTrace();
        }
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }
}
