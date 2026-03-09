package Tema12.ej16;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Alumno implements Serializable {
    String dni;
    String nombre;
    String apellidos;
    LocalDate fechaNac;

    public Alumno(String dni, String nombre, String apellidos, LocalDate fechaNac) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNac=" + fechaNac +
                '}';
    }

    public int getEdad(){
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNac, fechaActual).getYears();
    }
}
