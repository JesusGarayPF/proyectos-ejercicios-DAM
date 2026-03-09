package Procesos.CreacionProcesos.socketsTCPMultihilo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 12345;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde " + cliente.getInetAddress());
                GestorPeticiones hilo = new GestorPeticiones(cliente);
                hilo.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
