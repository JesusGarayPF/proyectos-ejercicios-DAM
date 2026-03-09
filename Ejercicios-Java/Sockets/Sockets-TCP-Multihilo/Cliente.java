package Procesos.CreacionProcesos.socketsTCPMultihilo;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto);
             InputStream entrada = socket.getInputStream();
             BufferedReader lector = new BufferedReader(new InputStreamReader(entrada))) {
            String mensaje = lector.readLine();
            System.out.println("Mensaje del servidor: " + mensaje);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
