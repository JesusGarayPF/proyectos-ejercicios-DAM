package Tema12;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Tarea06 {
    public static void main(String[] args) {
        Path directorio = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios");
        Path sinVocales = directorio.resolve("parrafoSinVocales.txt");

        try {
            Files.createDirectories(directorio);
            System.out.println("Directorio creado: " + directorio);
        } catch (IOException e) {
            System.out.println("Error al crear el directorio: " + e.getMessage());
            return;
        }
        //try (BufferedReader reader = new BufferedReader(new FileReader()));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sinVocales.toFile()))) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Escribe líneas de texto. Escribe 'FIN' en mayúsculas para terminar:");

            String linea;
            while (!(linea = scanner.nextLine()).equals("FIN")) {
                writer.write(linea);
                writer.newLine();
            }
            writer.write("FIN");
            System.out.println("Contenido escrito en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
