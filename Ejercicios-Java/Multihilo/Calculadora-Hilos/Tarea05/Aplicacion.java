package Procesos.CreacionProcesos.Tarea05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {
    private static final int NUM_CPU = 10;
    private static final List<HilosCalculador> hilos = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        for (int i = 0; i < NUM_CPU; i++) {
            HilosCalculador hiloCalculador = new HilosCalculador();
            hilos.add(hiloCalculador);
            new Thread(hiloCalculador).start();
        }

        int opcion;
        do {
            opcion = pedirOpcion();
            switch (opcion) {
                case 1:
                    reanudarHilos();
                    break;
                case 2:
                    suspenderHilo();
                    break;
                case 3:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (opcion != 3);
    }

    private static int pedirOpcion() {
        System.out.println("""
                Introduce una opción:
                1 - Reanudar hilos
                2 - Suspender hilos
                3 - Salir
                """);
        return Integer.parseInt(sc.nextLine());
    }

    private static void suspenderHilo() {
        boolean algunHiloSuspendido = false;
        for (HilosCalculador hilo : hilos) {
            if (!hilo.isSuspended()) {
                hilo.suspender();
                System.out.println("Hilo suspendido.");
                algunHiloSuspendido = true;
                break;
            }
        }
        if (!algunHiloSuspendido) {
            System.out.println("No hay hilos que suspender.");
        }
    }

    private static void reanudarHilos() {
        int contador = 0;
        for (HilosCalculador hilo : hilos) {
            if (hilo.isSuspended()) {
                hilo.reanudar();
                contador++;
            }
        }
        System.out.println("Hilos reanudados: " + contador);
    }
}
