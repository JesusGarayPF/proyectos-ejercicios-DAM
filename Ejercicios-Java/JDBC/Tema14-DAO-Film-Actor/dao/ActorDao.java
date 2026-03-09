package Tema14_Ej12_15.dao;

import Tema14_Ej12_15.entitites.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDao {
    private final String cadenaConexión;
    private final String usuario;
    private final String contraseña;
    private static final String SELECT_ACTORES = """
            select actor_id, first_name, last_name from actor
            """;
    private static final String SELECT_ACTOR_ID = """
            select actor_id, first_name, last_name from actor
            where actor_id = ?; 
            """;
    public ActorDao(String cadenaConexión, String usuario, String contraseña) {
        this.cadenaConexión = cadenaConexión;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public List<Actor> getAll() {
        ArrayList<Actor> actorsAll = new ArrayList<Actor>();
        try (Connection connection = DriverManager.getConnection(this.cadenaConexión, this.usuario, this.contraseña)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_ACTORES)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int actorId = rs.getInt("actor_Id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    actorsAll.add(new Actor(actorId, firstName, lastName));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actorsAll;
    }

    public List<Actor> getById(int id) {
        ArrayList<Actor> actorsId = new ArrayList<Actor>();
        try (Connection connection = DriverManager.getConnection(this.cadenaConexión, this.usuario, this.contraseña)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_ACTOR_ID)) {
                ResultSet rs = ps.executeQuery();
                ps.setInt(1,id);
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    actorsId.add(new Actor(id, firstName, lastName));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return actorsId;
    }
}
