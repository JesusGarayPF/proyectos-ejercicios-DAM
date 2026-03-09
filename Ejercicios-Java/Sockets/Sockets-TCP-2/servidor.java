package Procesos.CreacionProcesos.sockets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
    public static void main(String[] args) {
        int puerto = 12345;
        try(ServerSocket  serversocket= new ServerSocket(puerto)){
            System.out.println("Servidor iniciado en el puerto " + puerto);
            Socket socket = serversocket.accept();
            System.out.println("Cliente conectado desde " + socket.getInetAddress());
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(),true);
            String mensaje;
            while ((mensaje = entrada.readLine()) !=  null){
                System.out.println("Cliente: " + mensaje);
                if ("salir".equalsIgnoreCase(mensaje)){
                    System.out.println("El cliente ha cerrado la conexion");
                    break;
                }
                salida.println("Servidor: Recibido \"" + mensaje + "\"");
            }
            socket.close();
            System.out.println("Conexion cerrada");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
