package com.example.actividad_bola.Servidor;

import com.example.actividad_bola.Cliente.Cliente;
import javafx.concurrent.Task;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class Server extends Task<Void> {
    private final static int MAX_BYTES = 1500;
    private final static String COD_TEXTO = "UTF-8";
    private final int numPuerto;
    private final List<Cliente> clientes;

    public Server(int numPuerto, List<Cliente> clientes) {
        this.numPuerto = numPuerto;
        this.clientes = clientes;
    }

    @Override
    protected Void call() {
        try (DatagramSocket serverSocket = new DatagramSocket(numPuerto)) {
            System.out.printf("Creado socket de datagramas para puerto %s.\n", numPuerto);

            while (!isCancelled()) {
                byte[] datosRecibidos = new byte[MAX_BYTES];
                DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);

                serverSocket.receive(paqueteRecibido);

                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength(), COD_TEXTO);
                InetAddress IPCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                System.out.printf("Recibido datagrama de %s:%d (%s)\n", IPCliente.getHostAddress(), puertoCliente, mensaje);


            }
        } catch (IOException ex) {
            System.out.println("Excepción de E/S en el servidor UDP");
            ex.printStackTrace();
        }

        return null;
    }
}
