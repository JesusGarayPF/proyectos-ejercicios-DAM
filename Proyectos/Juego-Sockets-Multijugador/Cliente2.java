package Procesos.CreacionProcesos.practica2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             PrintWriter writer = new PrintWriter(output, true);
             Scanner scanner = new Scanner(System.in)) {

            String mensajeServidor;
            while ((mensajeServidor = reader.readLine()) != null) {
                System.out.println("Servidor: " + mensajeServidor);

                // Salir al recibir el mensaje de cierre
                if (mensajeServidor.equals("CERRANDO_CONEXION") || mensajeServidor.startsWith("El juego ya ha terminado")) {
                    System.out.println("El servidor ha cerrado la conexión. Fin del juego.");
                    break;
                }
                if (mensajeServidor.startsWith("¡Felicidades") || mensajeServidor.startsWith("¡Se acabaron")) {
                    break;
                }
                System.out.print("Tu intento: ");
                String intento = scanner.nextLine();
                writer.println(intento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
