package Tema12;

import java.io.File;
import java.util.Scanner;

public class Tarea04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta al directorio que desees");
        String directorio = sc.nextLine();
        File consultarDir = new File(directorio);
        if (consultarDir.exists()) {
            System.out.println("El directorio o fichero existe");
            if (consultarDir.isDirectory()) {
                System.out.println("La ruta introducida define un directorio");
                String[] contenidoEncontrado = consultarDir.list();
                //Declaracion directa como lista del array de string generado por .list()
                //List<String> contenido = List.of(consutlarDir.list());
                //Conversion a lista del array de string generado por .list
                //List<String> contenido = Arrays.asList(contenidoEncontrado);
                mostrarEstructuraIdentada(consultarDir, 1);
                mostrarDatosParticion(consultarDir);
            }
            if (consultarDir.isFile()) {
                System.out.println("la ruta introducida lleva a un fichero");
            }
        } else {
            System.out.println("El directorio no existe");
        }
    }

    private static void mostrarEstructuraIdentada(File consultarDir, int nivel) {
        File[] ficherosEncontrados = consultarDir.listFiles();
        if (ficherosEncontrados != null) {
            for (File ficheroEncontrado : ficherosEncontrados) {
                if (ficheroEncontrado.isDirectory()) {
                    System.out.print("\t".repeat(nivel));
                    System.out.printf("%s - %s - %d\n", "D", ficheroEncontrado.getName(), ficheroEncontrado.length());
                    mostrarEstructuraIdentada(ficheroEncontrado, nivel + 1);
                }else{
                    System.out.print("\t".repeat(nivel));
                    System.out.printf("%s - %s - %d\n", "F", ficheroEncontrado.getName(), ficheroEncontrado.length());
                    mostrarEstructuraIdentada(ficheroEncontrado, nivel + 1);
                }
            }
        }
    }
    private static void mostrarDatosParticion(File directorio){
        File [] raices = directorio.listRoots();
        for (File raiz : raices) {
            long total = (long) (raiz.getTotalSpace() / Math.pow(1024,3));
            long usable = (long) (raiz.getUsableSpace() / Math.pow(1024,3));
            long libre = (long) (raiz.getFreeSpace() / Math.pow(1024,3));
            System.out.println();
            System.out.printf("Tamaño particion/disco: %d G|| Espacio usable %d G|| Espacio libre %d G", total, usable, libre);
        }

    }
}

