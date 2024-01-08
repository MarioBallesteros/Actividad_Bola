package com.example.actividad_bola.Servidor;

import javafx.scene.image.ImageView;
import com.example.actividad_bola.Cliente.Cliente;

import java.util.List;

public class Server {
    private List<Cliente> clientes;
    private ImageView imagenBola;

    public Server(List<Cliente> clientes, ImageView imagenBola) {
        this.clientes = clientes;
        this.imagenBola = imagenBola;
    }

    public void addCliente(Cliente cliente) {
         clientes.add(cliente);
    }
    public void deleteCliente(String ip){
        for (Cliente cliente:clientes) {
            if (cliente.getIp().equals(ip))
                clientes.remove(cliente);
        }
    }
}
