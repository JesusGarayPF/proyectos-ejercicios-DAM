package Procesos.CreacionProcesos.sockets3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

public class servidor {
    public static void main(String[] args) {
        int puerto = 48000;
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Cliente conectado desde " + socket.getInetAddress());
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                    String nombreArchivo = entrada.readLine();
                    Path rutaCliente = Path.of(entrada.readLine());
                    Path rutaCompleta = rutaCliente.resolve(nombreArchivo);
                    File fichero = rutaCompleta.toFile();

                    if (fichero.exists() && fichero.isFile()) {
                        System.out.println("Fichero encontrado. Mostrando contenido");
                        salida.println("Fichero encontrado. Mostrando contenido:");
                        try (BufferedReader lectorArchivo = new BufferedReader(new FileReader(fichero))) {
                            String linea;
                            while ((linea = lectorArchivo.readLine()) != null) {
                                salida.println(linea);
                            }
                            break;
                        }
                    } else {
                        System.out.println("El fichero no existe o la ruta es incorrecta. Cerrando conexión.");
                        salida.println("Error: El fichero no existe o la ruta es incorrecta.");
                    }
                } catch (IOException e) {
                    System.out.println("Error al procesar la conexión con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
