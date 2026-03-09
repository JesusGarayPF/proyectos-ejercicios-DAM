package Procesos.CreacionProcesos.sockets3;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class cliente {
    public static void main(String[] args) {
        String ip = "172.17.225.67";
        int puerto = 48000;

        try (Socket socket = new Socket(ip, puerto)) {
            System.out.println("Conectado con la IP " + ip + " en el puerto " + puerto);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); // 'true' para autoflush
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Introduce el nombre del archivo: ");
            String nombre = teclado.readLine();
            salida.println(nombre);
            System.out.print("Introduce la ruta al archivo: ");
            String ruta = teclado.readLine();
            salida.println(ruta);
            System.out.println("Respuesta del servidor:");
            String linea;
            while ((linea = entrada.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (UnknownHostException e) {
            System.err.println("No se pudo conectar con el servidor: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
