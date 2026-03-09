package Tema12;

import java.io.File;

public class Tarea01 {
    public static void main(String[] args) {
        //String rutaEscritorio = "C:\Users\Jesús Garay\Desktop";
        //Path pathEscritorio = Path.of( System.getProperty("user.home") + File.separator + "Desktop");
        String rutaEscritorio = System.getProperty("user.home") + File.separator + "Desktop";
        String rutaDirectorio = rutaEscritorio + File.separator + "ficheros-de-prueba-borrar";
        File directorio = new File(rutaDirectorio);
        System.out.println("Creando directorio " + rutaDirectorio);
        if (!directorio.exists()) {
            if (directorio.mkdir()) {
                System.out.println("El directorio se ha creado");
            } else {
                System.out.println("Error al crear el directorio");
            }
        } else {
            System.out.println("El directorio ya existe");
        }
        System.out.println("Eliminando directorio " + rutaDirectorio);
        if (directorio.exists()) {
            if (directorio.delete()) {
                System.out.println("El directorio ha sido borrado");
            } else {
                System.out.println("Error al borrar el directorio");
            }
        } else {
            System.out.println("El directorio ya existe");
        }
        System.out.println("Creando estructura de ficheros: ficheros-de-prueba/ejercicio01");
        String rutaAnidada = rutaEscritorio + File.separator + "ficheros-de-prueba" + File.separator + "ejercicio01";
        File dirAnidado = new File(rutaAnidada);
        if (!dirAnidado.exists()) {
            if (dirAnidado.mkdir()) {
                System.out.println("Los ficheros \"ficheros-de-prueba\" y \"ejercicio01\" ha sido creado");
            }
        } else{
            System.out.println("El directorio \"ficheros-de-prueba\" ya existe");
        }
    }
}
