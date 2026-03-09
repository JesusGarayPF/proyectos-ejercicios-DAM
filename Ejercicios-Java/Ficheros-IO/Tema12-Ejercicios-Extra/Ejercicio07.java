package Tema12;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio07 {
    private static final char CHAR_INICIAL = 0x20;
    private static final char CHAR_FINAL = 0x7E;

    private static Random rnd = new Random();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Path directorio = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios");
        System.out.println("Introduce el nombre del fichero del fichero de texto que quieres crear");
        String nombreArchivo = sc.nextLine();
        File dirUsuario = directorio.resolve(nombreArchivo).toFile();
        try {
            if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);
            }
            if (!dirUsuario.exists()) {
                dirUsuario.createNewFile();
                System.out.println("El archivo ha sido creado");
            } else {
                System.out.println("El archivo ya existe");
                return;
            }
            ArrayList<Character> letras = new ArrayList<Character>();
            while (letras.size() < 101) {
                char aleatorio = (char) rnd.nextInt(CHAR_INICIAL, CHAR_FINAL);
                letras.add(aleatorio);
            }
            letras.sort(null);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dirUsuario))) {
                for (Character letra : letras) {
                    writer.write(letra);
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
