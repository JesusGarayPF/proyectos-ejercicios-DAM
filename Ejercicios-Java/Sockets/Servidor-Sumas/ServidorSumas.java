package servidorSumaMoviles;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ServidorSumas {
    public static final int SERVER_PORT = 9898;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Servidor iniciado en el puerto " + SERVER_PORT);

            while (true) {
                System.out.println("Esperando un cliente...");
                Socket cliente = serverSocket.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                Scanner sc = new Scanner(cliente.getInputStream());
                PrintStream ps = new PrintStream(cliente.getOutputStream());

                int a = sc.nextInt();
                int b = sc.nextInt();
                int suma = a + b;

                System.out.println("Recibido: " + a + " + " + b + " = " + suma);
                ps.println(suma);

                sc.close();
                ps.close();
                cliente.close();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
