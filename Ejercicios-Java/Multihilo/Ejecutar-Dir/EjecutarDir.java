package Procesos.CreacionProcesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjecutarDir {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("CMD","/C","DIR");
            Process p = pb.start();
            BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                for (char c : linea.toCharArray()) {
                    System.out.print(c);
                }
                System.out.println();
            }
            int resultado = p.waitFor();

            if (resultado == 0) {
                System.out.println("El comando DIR se ejecutó correctamente (exit code 0).");
            } else {
                System.out.println("Hubo un error al ejecutar el comando DIR (exit code -1).");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

