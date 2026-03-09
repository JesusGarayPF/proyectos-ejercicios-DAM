package Tema12.ej10;

import java.time.LocalDate;

public class Media {
    int id;
    String titulo;
    String artistaPrincipal;
    String productora;
    LocalDate fechaPub;
    Tipo tipo;

    public Media(int id, String titulo, String artistaPrincipal, String productora, LocalDate fechaPub, Tipo tipo) {
        this.id = id;
        this.titulo = titulo;
        this.artistaPrincipal = artistaPrincipal;
        this.productora = productora;
        this.fechaPub = fechaPub;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtistaPrincipal() {
        return artistaPrincipal;
    }

    public String getProductora() {
        return productora;
    }

    public LocalDate getFechaPub() {
        return fechaPub;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setArtistaPrincipal(String artistaPrincipal) {
        this.artistaPrincipal = artistaPrincipal;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public void setFechaPub(LocalDate fechaPub) {
        this.fechaPub = fechaPub;
    }
}
