package com.example.actividad_bola;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

import java.util.List;

public class Ball extends Task<Punto> {
    private ImageView imagenBola;
    private Updatable updatable;
    private Punto punto;

    public Ball(ImageView imagenBola) {
        this.imagenBola = imagenBola;
        this.updatable = new Updater();
        this.punto = new Punto(imagenBola.getLayoutX(), imagenBola.getLayoutY());
        System.out.println("X: "+imagenBola.getLayoutX() +" Y:"+imagenBola.getLayoutY());
        valueProperty().addListener((observableValue, positions, newPosition) -> {
            imagenBola.setLayoutX(newPosition.getX());
            imagenBola.setLayoutY(newPosition.getY());
            updatable.update(newPosition);
        });
    }

    @Override
    protected Punto call() throws Exception {
        Punto nuevoPunto;
        int valorsumar = 0;
        while (true) {
            System.out.println("justo antes de moverse:     X:" + imagenBola.getLayoutX() + " Y:" + imagenBola.getLayoutY());
            nuevoPunto = new Punto(imagenBola.getLayoutX() + valorsumar, imagenBola.getLayoutY() + valorsumar);
            valorsumar++;
            updateValue(nuevoPunto);
            Thread.sleep(1000);
            }
        }
    }

