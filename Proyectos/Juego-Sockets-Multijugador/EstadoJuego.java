package Procesos.CreacionProcesos.practica2;

import java.util.Random;

public class EstadoJuego {
    private static EstadoJuego instancia;
    private int numeroAdivinar;
    private boolean juegoTerminado;

    private EstadoJuego() {
        this.numeroAdivinar = new Random().nextInt(10) + 1; // Número entre 1 y 10
        this.juegoTerminado = false;
    }

    public static synchronized EstadoJuego getInstancia() {
        if (instancia == null) {
            instancia = new EstadoJuego();
        }
        return instancia;
    }

    public int getNumeroAdivinar() {
        return numeroAdivinar;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean terminado) {
        this.juegoTerminado = terminado;
    }
}
