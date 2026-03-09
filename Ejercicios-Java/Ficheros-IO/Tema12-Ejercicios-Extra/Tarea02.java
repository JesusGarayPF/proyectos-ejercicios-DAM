package Tema12;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tarea02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta al directorio que desees");
        String directorio = sc.nextLine();
        File consutlarDir = new File(directorio);
        if (consutlarDir.exists()) {
            System.out.println("El directorio o fichero existe");
            if (consutlarDir.isDirectory()) {
                System.out.println("La ruta introducida define un directorio");
                String[] contenidoEncontrado = consutlarDir.list();
                //Declaracion directa como lista del array de string generado por .list()
                //List<String> contenido = List.of(consutlarDir.list());
                //Conversion a lista del array de string generado por .list
                //List<String> contenido = Arrays.asList(contenidoEncontrado);
                File[] ficherosEncontrados = consutlarDir.listFiles();
                if (ficherosEncontrados != null) {
                    for (File ficheroEncontrado : ficherosEncontrados) {
                        System.out.printf("%s - %s - %d\n", ficheroEncontrado.isDirectory() ? "D" : "F", ficheroEncontrado.getName(), ficheroEncontrado.length());
                    }
                }
            }
            if (consutlarDir.isFile()) {
                System.out.println("la ruta introducida lleva a un fichero");
            }
        } else {
            System.out.println("El directorio no existe");
        }
    }
}
