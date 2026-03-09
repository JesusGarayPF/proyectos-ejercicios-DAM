package Procesos.CreacionProcesos.sockets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class cliente {
    public static void main(String[] args) {
        String ip = "172.17.225.67";
        int puerto = 12345;

        try (Socket socket = new Socket(ip, puerto)) {
            System.out.println("Conectado a la ip " + ip + " en el puerto " + puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            String mensaje;
            while (true) {
                System.out.println("<=Manda Mensaje ('salir' para terminar) =>");
                mensaje = teclado.readLine();
                salida.println(mensaje);
                if ("salir".equalsIgnoreCase(mensaje)) {
                    System.out.println("Cerrando conexion");
                    break;
                }
                String respuesta = entrada.readLine();
                System.out.println(respuesta);
            }
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
