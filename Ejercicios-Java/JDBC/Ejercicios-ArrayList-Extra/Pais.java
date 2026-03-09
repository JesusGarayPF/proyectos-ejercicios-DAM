package EjerciciosAsh;

import java.io.Serializable;

public class Pais {
    private final String nombre;
    private final double poblacion;
    private final double extension;
    private final double pib;

    public Pais(String nombre, double poblacion, double extension, double pib) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.extension = extension;
        this.pib = pib;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPoblacion() {
        return poblacion;
    }

    public double getExtension() {
        return extension;
    }

    public double getPib() {
        return pib;
    }
}
