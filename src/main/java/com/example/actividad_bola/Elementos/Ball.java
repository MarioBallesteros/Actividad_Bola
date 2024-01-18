package com.example.actividad_bola.Elementos;

import com.example.actividad_bola.Cliente.Cliente;
import com.example.actividad_bola.Servidor.Server;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

import java.util.List;

public class Ball extends Task<Punto> {
    private ImageView imagenBola;
    private Updatable updatable;
    private Punto punto;
    private boolean estaMoviendo;
    private Server servidor;

    public Ball(ImageView imagenBola,Server servidor) {
        this.imagenBola = imagenBola;
        this.updatable = new Updater();
        this.estaMoviendo=false;
        this.punto = new Punto(imagenBola.getLayoutX(), imagenBola.getLayoutY());
        this.servidor = servidor;

        valueProperty().addListener((observableValue, positions, newPosition) -> {
            imagenBola.setLayoutX(newPosition.getX());
            imagenBola.setLayoutY(newPosition.getY());
            updatable.update(newPosition);
            servidor.enviarPosicionPelota(newPosition.getX(), newPosition.getY());
            //System.out.println("clientes:" + servidor.getClientes().toString());
        });
    }

    public Ball(ImageView imagenBola) {
        this.imagenBola = imagenBola;
        this.updatable = new Updater();
        this.estaMoviendo=false;
        this.punto = new Punto(imagenBola.getLayoutX(), imagenBola.getLayoutY());

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
            if(estaMoviendo & servidor != null) {
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

