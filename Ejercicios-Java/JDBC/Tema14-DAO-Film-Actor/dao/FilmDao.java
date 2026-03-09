package Tema14_Ej12_15.dao;

import Tema14_Ej12_15.entitites.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDao {
    private final String cadenaConexión;
    private final String usuario;
    private final String contraseña;
    private static final String SELECT_PELICULAS = """
            select film_id, title, description, release_year from film
            """;
    private static final String SELECT_PELICULA_ID = """
            select film_id, title, description, release_year from film
            where film_id = ?
            """;

    public FilmDao(String cadenaConexión, String usuario, String contraseña) {
        this.cadenaConexión = cadenaConexión;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public List<Film> getAll() {
       ArrayList<Film> filmsAll = new ArrayList<Film>();
        try (Connection connection = DriverManager.getConnection(this.cadenaConexión, this.usuario, this.contraseña)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_PELICULAS)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int filmId = rs.getInt("film_id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    int releaseYear = rs.getInt("release_year");
                    filmsAll.add(new Film(filmId, title, description, releaseYear));
                }
                return filmsAll;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Film> getById(int id) {
        ArrayList<Film> filmId = new ArrayList<Film>();
        try (Connection connection = DriverManager.getConnection(this.cadenaConexión, this.usuario, this.contraseña)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_PELICULA_ID)) {
                ResultSet rs = ps.executeQuery();
                ps.setInt(1, id);
                while (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    int releaseYear = rs.getInt("release_year");
                    filmId.add(new Film(id, title, description, releaseYear));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return filmId;
    }
}
