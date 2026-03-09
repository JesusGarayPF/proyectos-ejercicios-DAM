package Tema12;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Tarea08 {
    public static void main(String[] args) {

        Path dirF1 = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\f1.txt");
        Path dirF2 = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\f2.txt");
        Path dirF3 = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\f3.txt");
        try {
            if (!Files.exists(dirF3)) {
                Files.createFile(dirF3);
                System.out.println("El archivo ha sido creado");
            } else {
                System.out.println("El archivo ya existe");
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(dirF1.toFile()))) {
                try (BufferedReader reader2 = new BufferedReader(new FileReader(dirF2.toFile()))) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(dirF3.toFile()))) {
                        char c1 = (char) reader.read();
                        char c2 = (char) reader2.read();
                        while (c1 != -1 || c2 != -1) {
                            // Si c1 o c2 es un salto de línea, escribimos el salto de línea en el archivo de salida
                            if (c1 == '\n' || c2 == '\n') {
                                writer.write('\n');
                                // Leemos el siguiente caracter después del salto de línea
                                while (c1 == '\n') c1 = (char) reader.read();
                                while (c2 == '\n') c2 = (char) reader2.read();
                            } else {
                                if (c1 == -1) {
                                    writer.write(c2);
                                    c2 = (char) reader2.read();
                                } else if (c2 == -1) {
                                    writer.write(c1);
                                    c1 = (char) reader.read();
                                } else if (c1 <= c2) {
                                    writer.write(c1);
                                    c1 = (char) reader.read();
                                } else {
                                    writer.write(c2);
                                    c2 = (char) reader2.read();
                                }
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
