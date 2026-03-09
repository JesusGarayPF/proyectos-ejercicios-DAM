package Tema14_Ej12_15.dao;

import Tema14_Ej12_15.entitites.ActorInFilm;
import Tema14_Ej12_15.entitites.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorInFilmDao {
    private final String cadenaConexión;
    private final String usuario;
    private final String contraseña;
    private static final String SELECT_ACTOR_IN_FILM = """
            select actor_id, film_id from film_actor
        """;

    public ActorInFilmDao(String cadenaConexión, String usuario, String contraseña) {
        this.cadenaConexión = cadenaConexión;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public List<ActorInFilm> getAll() {
        ArrayList<ActorInFilm> actorFilmsAll = new ArrayList<ActorInFilm>();
        try (Connection connection = DriverManager.getConnection(this.cadenaConexión, this.usuario, this.contraseña)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_ACTOR_IN_FILM)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int actorId = rs.getInt("actor_id");
                    int filmId = rs.getInt("film_id");
                    actorFilmsAll.add(new ActorInFilm(actorId, filmId));
                }
                return actorFilmsAll;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
