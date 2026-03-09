package Procesos.CreacionProcesos.socketsTCPMultihilo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GestorPeticiones extends Thread {
    private Socket cliente;

    public GestorPeticiones(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try (OutputStream salida = cliente.getOutputStream();
             PrintWriter writer = new PrintWriter(salida, true)) {

            Random random = new Random();
            int aleatorio = random.nextInt(1000);
            writer.println("Número aleatorio asignado: " + aleatorio);
            System.out.println("Número enviado al cliente: " + aleatorio);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
