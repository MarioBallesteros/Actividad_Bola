package com.example.actividad_bola;

public class Updater implements Updatable {
    @Override
    public void update(Punto newPosition) {
        System.out.println("Posición actualizada: " + newPosition);

    }
}
