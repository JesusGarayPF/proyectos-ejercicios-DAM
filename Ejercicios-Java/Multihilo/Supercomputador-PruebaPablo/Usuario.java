package Procesos.CreacionProcesos.pruebaPablo;

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
        }
    }

    @Override
    public void run() {
        final int nucleos = rand.nextInt(4);
        final int memoria = rand.nextInt(8);

        try {
            sc.entrar();
            System.out.printf("""
                      El usuario %d ha entrado
                      - Usuarios actuales: %d
                      - Usuarios servidos: %d
                    - - - - - - - - - - - - - -
                    """, id, sc.getUsuariosActuales(), sc.getUsuariosServidos());
            try {
                sc.obtenerMV(nucleos, memoria);
                usarMV();
                System.out.printf("""
                          Usuario %d usando MV
                          - Núcleos disponibles: %d
                          - RAM disponible: %d
                        - - - - - - - - - - - - - -
                        """, id, sc.getNucleosDisponibles(), sc.getRamDisponible());

                sc.devolverMV(nucleos, memoria);
                System.out.printf("""
                          El usuario %d devolvió la MV
                          - Núcleos disponibles: %d
                          - RAM disponible: %d
                        - - - - - - - - - - - - - -
                        """, id, sc.getNucleosDisponibles(), sc.getRamDisponible());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                sc.salir();
                System.out.printf("""
                          El usuario %d salió
                          - Usuarios actuales: %d
                          - Usuarios servidos: %d
                        - - - - - - - - - - - - - -
                        """, id, sc.getUsuariosActuales(), sc.getUsuariosServidos());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
