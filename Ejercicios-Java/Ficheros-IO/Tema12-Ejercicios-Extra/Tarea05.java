package Tema12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Tarea05 {
    private static final Path PATH_FICHERO_ENTRADA = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\parrafo.txt");

    public static void main(String[] args) throws FileNotFoundException {
        try (FileReader fr = new FileReader(PATH_FICHERO_ENTRADA.toFile())) {
            char [] letrasLeidas = new char[5];
            int cuantasLeidas;
            while((cuantasLeidas =  fr.read()) > 0){
                mostrarLetras(letrasLeidas, cuantasLeidas);
            }
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-".repeat(100));

    }

    private static void mostrarLetras(char[] letrasLeidas, int cuantasLeidas) {
        for (int i = 0; i < cuantasLeidas; i++) {
            System.out.print(letrasLeidas[i]);
        }
        System.out.println();
    }
}
