package Procesos.CreacionProcesos.sockets;

import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            // Dirección del servidor
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            // Crear el socket UDP
            DatagramSocket socketCliente = new DatagramSocket();

            // Leer mensaje del usuario
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce un mensaje para el servidor:");
            String mensaje = scanner.nextLine();
            scanner.close();

            // Convertir mensaje a bytes
            byte[] bufferEnvio = mensaje.getBytes();

            // Crear datagrama para enviar el mensaje
            DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, 9876);

            // Enviar el datagrama
            socketCliente.send(paqueteEnvio);
            System.out.println("Mensaje enviado al servidor.");

            // Buffer para recibir la respuesta
            byte[] bufferRecepcion = new byte[1024];

            // Crear datagrama para recibir la respuesta
            DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

            // Recibir la respuesta
            socketCliente.receive(paqueteRecepcion);

            // Mostrar la respuesta
            String respuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cerrar el socket
            socketCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

