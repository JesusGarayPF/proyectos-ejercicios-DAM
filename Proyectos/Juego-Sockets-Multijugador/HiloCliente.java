package Procesos.CreacionProcesos.practica2;

import java.io.*;
import java.net.*;

public class HiloCliente extends Thread {
    private Socket socket;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output, true)
        ) {
            EstadoJuego estadoJuego = EstadoJuego.getInstancia();
            int intentos = 5;
            boolean clienteGanador = false;

            writer.println("¡Bienvenido! Intenta adivinar el número entre 1 y 10. Tienes " + intentos + " intentos.");

            for (int i = 0; i < intentos; i++) {
                if (estadoJuego.isJuegoTerminado()) {
                    writer.println("El juego ya ha terminado. CERRANDO_CONEXION.");
                    break;
                }

                String mensajeCliente = reader.readLine();
                if (mensajeCliente == null) {
                    break; // El cliente cerró la conexión
                }

                try {
                    int intento = Integer.parseInt(mensajeCliente);
                    System.out.println("Cliente intentó con: " + intento);

                    if (intento == estadoJuego.getNumeroAdivinar()) {
                        writer.println("¡Felicidades! Has adivinado el número.");
                        estadoJuego.setJuegoTerminado(true);
                        clienteGanador = true;
                        break;
                    } else {
                        writer.println("Incorrecto. Intentos restantes: " + (intentos - i - 1));
                    }
                } catch (NumberFormatException e) {
                    writer.println("Entrada inválida. Intenta nuevamente.");
                }
            }
            if (!clienteGanador && !estadoJuego.isJuegoTerminado()) {
                writer.println("¡Se acabaron tus intentos! El juego ha terminado.");
            }

            writer.println("CERRANDO_CONEXION");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Conexión con el cliente cerrada.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
