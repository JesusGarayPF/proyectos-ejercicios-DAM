package Procesos.CreacionProcesos.practica2;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        EstadoJuego estadoJuego = EstadoJuego.getInstancia();
        System.out.println("Servidor iniciado. Número a adivinar: " + estadoJuego.getNumeroAdivinar());

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            int maxClientes = 5;
            int clientesConectados = 0;

            while (clientesConectados < maxClientes && !estadoJuego.isJuegoTerminado()) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");
                new HiloCliente(clienteSocket).start();
                clientesConectados++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("El juego ha terminado.");
    }
}
