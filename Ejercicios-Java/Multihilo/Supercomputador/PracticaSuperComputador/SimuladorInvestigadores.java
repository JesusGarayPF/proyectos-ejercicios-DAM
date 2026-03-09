package Procesos.CreacionProcesos.PracticaSuperComputador;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimuladorInvestigadores {
    public static void main(String[] args) {
        int maxUsuarios = 5;
        int núcleosSupercomputador = 16;
        int ramSupercomputador = 32;

        Supercomputador sc = new Supercomputador(maxUsuarios, núcleosSupercomputador, ramSupercomputador);
        List<Thread> usuarios = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario(i, sc);
            Thread hiloUsuario = new Thread(usuario);
            usuarios.add(hiloUsuario);
            hiloUsuario.start();
        }

        while (usuarios.stream().anyMatch(Thread::isAlive)) {
            System.out.println("Estado del Supercomputador:");
            System.out.println("Núcleos disponibles: " + sc.núcleosDisponibles());
            System.out.println("RAM disponible: " + sc.ramDisponible() + " GiB");
            System.out.println("Usuarios actuales: " + sc.usuariosActuales());
            System.out.println("Usuarios servidos: " + sc.usuariosServidos());
            System.out.println("-------------------------------");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Simulación finalizada.");
    }
}

