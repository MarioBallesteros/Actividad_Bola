package com.example.actividad_bola;

import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

public class Ball extends Task<Punto> {
    private ImageView imagenBola;
    private Punto punto;

    public Ball(ImageView imagenBola) {

        this.imagenBola = imagenBola;
        this.punto = new Punto(imagenBola.getLayoutX(), imagenBola.getLayoutY());

        valueProperty().addListener((observableValue, positions, newPosition) -> {
                imagenBola.setLayoutX(newPosition.getX());
                imagenBola.setLayoutY(newPosition.getY());
                //updatable.update(newPosition);
    });
    }

    @Override
    protected Punto call() throws Exception {
        while (true){
            System.out.println("me intento mover");
            punto.x++;
            punto.y++;
            updateValue(new Punto(punto.getX(), punto.getY()));
            Thread.sleep(100);
        }
    }
}
