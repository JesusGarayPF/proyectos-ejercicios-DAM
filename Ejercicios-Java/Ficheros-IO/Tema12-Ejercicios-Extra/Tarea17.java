package Tema12;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

public class Tarea17 {

    private static Scanner sc = new Scanner(System.in);
    private static final int NUMS_POR_LINEA = 5;
    private static final Path DESTINO = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\ej17.dat");

    public static void main(String[] args) throws IOException {
        int cantidad = preguntarCantidad();
        List<Integer> numeros = crearLilsta(cantidad);
        añadirNumerosFichero(numeros, DESTINO);
        eliminarMultiplosCinco(numeros);
        añadirNumerosFichero(numeros, DESTINO);


    }

    private static int preguntarCantidad() {
        System.out.println("Introduce cuantos numeros quieres generar");
        int cantidad = 0;
        boolean esValido = false;
        while (!esValido) {
            try {
                cantidad = Integer.parseInt(sc.nextLine());
                if (cantidad < 100 || cantidad > 200) {
                    System.out.println("El valor introducido debe estar entre 100-200 incluidos");
                } else {
                    esValido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato intoducido no es un numero");
            }
        }
        return cantidad;
    }

    private static List<Integer> crearLilsta(int cantidad) {
        Random rnd = new Random();
        List<Integer> numeros = new ArrayList<>(cantidad);
        for (int i = 0; i < cantidad; i++) {
            numeros.add(rnd.nextInt(-50, 51));
        }
        numeros.sort(null);
        return numeros;
    }

    private static void añadirNumerosFichero(List<Integer> numeros, Path pathFichero) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(pathFichero.toFile(), true))) {
            int indice = 1;
            for (Integer numero : numeros) {
                pw.printf("%3d ", numero);
                indice++;
                if (indice % NUMS_POR_LINEA == 0 && numeros.indexOf(numero) != numeros.size()) {
                    pw.println();
                }
            }
            pw.printf("\n%s\n", "_".repeat(20));
        }
    }

    private static void eliminarMultiplosCinco(List<Integer> numeros) {
        Iterator<Integer> iter = numeros.iterator();
        while (iter.hasNext()) {
            int numero = iter.next();
            if (numero % 5 == 0) {
                iter.remove();
            }
        }
    }

    private static void separarParesDeImpares() {
    }
}
