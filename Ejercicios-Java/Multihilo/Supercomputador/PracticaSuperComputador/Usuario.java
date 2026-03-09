package Procesos.CreacionProcesos.PracticaSuperComputador;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Usuario implements Runnable {
    private static final Random rand = new Random();
    private final int id;
    private final Supercomputador sc;

    public Usuario(int id, Supercomputador sc) {
        this.id = id;
        this.sc = sc;
    }

    private void usarMV() {
        try {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        int núcleos = 1 + rand.nextInt(4);
        int memoria = 1 + rand.nextInt(8);

        try {
            sc.entrar();
            System.out.println("El usuario " + id + " ha entrado a la sala");
            try {
                sc.obtenerMV(núcleos, memoria);
                System.out.println("Usuario " + id + " usando MV con " + núcleos + " núcleos y " + memoria + " GB de RAM");
                usarMV();
                sc.devolverMV(núcleos, memoria);
                System.out.println("Usuario " + id + " devolvió la MV");
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                sc.salir();
                System.out.println("Usuario " + id + " salió de la sala");
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

