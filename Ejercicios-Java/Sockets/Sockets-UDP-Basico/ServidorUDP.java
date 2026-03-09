package Procesos.CreacionProcesos.sockets;

import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            // Crear socket asociado al puerto
            DatagramSocket socketServidor = new DatagramSocket(9876);
            System.out.println("Servidor UDP escuchando en el puerto 9876...");

            // Crear el buffer para recibir datos
            byte[] bufferRecepcion = new byte[1024];

            while (true) {
                // Crear el datagrama para recibir datos
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

                // Esperar a recibir un datagrama
                socketServidor.receive(paqueteRecepcion);
                System.out.println("Mensaje recibido del cliente.");

                // Extraer datos del datagrama recibido
                String mensaje = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                System.out.println("Mensaje: " + mensaje);

                // Preparar respuesta
                String respuesta = "Mensaje recibido: " + mensaje;
                byte[] bufferRespuesta = respuesta.getBytes();

                // Obtener la dirección y puerto del cliente
                InetAddress direccionCliente = paqueteRecepcion.getAddress();
                int puertoCliente = paqueteRecepcion.getPort();

                // Crear datagrama de respuesta
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente);

                // Enviar la respuesta
                socketServidor.send(paqueteRespuesta);
                System.out.println("Respuesta enviada al cliente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
