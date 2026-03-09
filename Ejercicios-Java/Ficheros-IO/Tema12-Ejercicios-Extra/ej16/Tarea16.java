package Tema12.ej16;

import net.datafaker.Faker;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;



public class Tarea16 {
    private static Path destino  = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\ej16.dat");

    public static void main(String[] args){
        Alumno alumno = generarAlumnoAleatorio();

    }
    private static Alumno generarAlumnoAleatorio(){
        Faker faker = new Faker();
        String dni = faker.idNumber().toString();
        String nombre = faker.name().name();
        String apellidos = faker.name().lastName() + faker.name().lastName();
        LocalDate fechaNac = faker.date().birthdayLocalDate();
        Alumno alumno = new Alumno(dni, nombre, apellidos, fechaNac);
        return alumno;
    }
//    private static void mostrarAlumno(Path pathficheroAlumnos){
//        File ficheroAlumnos = pathficheroAlumnos.toFile();
//        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream(pathficheroAlumnos){
//            boolean esFinFichero = false;
//            while (!esFinFichero){
//                Alumno alumno = (Alumno) ois.readObject;
//            }
//    }
//    private static void añadirAlumno(Alumno alumno, Path ficheroAlumnos){
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(destino.toFile()))){
//            boolean esFinFichero = false;
//            while (!esFinFichero){
//                Alumno alumno = (Alumno) ois.readObject;
//            }
//            oos.writeObject(alumno);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
