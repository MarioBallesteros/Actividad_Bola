package com.example.actividad_bola.Cliente;

public class Cliente {
    private String ip;
    private int puerto;

    public Cliente(String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
    }

    public String getIp() {
        return ip;
    }

    public int getPuerto() {
        return puerto;
    }
}
