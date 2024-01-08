package com.example.actividad_bola.Elementos;

import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

public class Ball extends Task<Punto> {
    private ImageView imagenBola;
    private Updatable updatable;
    private Punto punto;
    private boolean estaMoviendo;

    public Ball(ImageView imagenBola) {
        this.imagenBola = imagenBola;
        this.updatable = new Updater();
        this.estaMoviendo=false;
        this.punto = new Punto(imagenBola.getLayoutX(), imagenBola.getLayoutY());
        //System.out.println("X: "+imagenBola.getLayoutX() +" Y:"+imagenBola.getLayoutY());

        valueProperty().addListener((observableValue, positions, newPosition) -> {
            imagenBola.setLayoutX(newPosition.getX());
            imagenBola.setLayoutY(newPosition.getY());
            updatable.update(newPosition);
        });
    }

    public boolean estaMoviendo() {
        return estaMoviendo;
    }

    public void setMoviendo(boolean estaMoviendo) {
        this.estaMoviendo = estaMoviendo;
    }

    @Override
    protected Punto call() {
        Punto nuevoPunto;
        int valorsumarX = 6;
        int valorsumarY = valorsumarX;
        int tamanyoBola = 30;
        int tamanyoPantalla = 600;
        double valorX;
        double valorY;
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("justo antes de moverse:     X:" + imagenBola.getLayoutX() + " Y:" + imagenBola.getLayoutY());
            if(estaMoviendo) {
                valorX = imagenBola.getLayoutX() + valorsumarX;
                valorY = imagenBola.getLayoutY() + valorsumarY;
                if (valorX > tamanyoPantalla - tamanyoBola || valorX < 0) {
                    valorsumarX = valorsumarX * -1;
                }
                if (valorY > tamanyoPantalla - tamanyoBola || valorY < 0) {
                    valorsumarY = valorsumarY * -1;
                }
                nuevoPunto = new Punto(valorX, valorY);
                updateValue(nuevoPunto);
            }
            // System.out.println("stop");
        }
    }
}

