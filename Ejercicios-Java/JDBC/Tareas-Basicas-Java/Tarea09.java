
import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Tarea09 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String CIUDAD_EXISTE = """
            select city
            from city
            where city = ?
            """;
    private static final String PAIS_EXISTE = """
            select country
            from country
            where country = ?
            """;
    private static final String INSERT_CIUDAD = """
            insert into city(city, country_id)
            values(?,?)
            """;
    private static final String INSERT_PAIS = """
            insert into country (country)
            values(?)
            """;
    private static final String GET_COUNTRY_ID = """
            select country_id
            from country
            where country = ?
            """;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean terminado = false;
        do {
            System.out.println("Que ciudad quiere añadir a la base de datos?");
            String ciudad = sc.nextLine();
            if (ciudadExiste(ciudad)) {
                System.out.println("La ciudad ya existe");
            } else {
                System.out.println("En que país está la ciudad que quiere añadir?");
                String pais = sc.nextLine();
                if (paisExiste(pais)) {
                    System.out.println("El pais elegido existe en la base de datos\nLa ciudad se añadirá a este pais");
                    añadirCiudad(ciudad, pais);
                    System.out.println("Base de datos actualizada");
                } else {
                    System.out.println("El pais no existe en la base de datos y será añadido\nTambien se añadirá la ciudad a este pais");
                    añadirPais(pais);
                    añadirCiudad(ciudad, pais);
                    System.out.println("Base de datos actualizada");
                }
            }
            System.out.println("Quiere añadir nuevas ciudades? Introduzca 0 para terminar o 1 para continuar");
            int respuesta = sc.nextInt();
            sc.nextLine();
            if (respuesta == 0) terminado = true;
        } while (!terminado);
        System.out.println("Fin de programa");

    }

    private static void añadirPais(String pais) {
        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD)) {
            try (PreparedStatement insertPais = connection.prepareStatement(INSERT_PAIS)) {
                insertPais.setString(1, pais);
                insertPais.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void añadirCiudad(String ciudad, String pais) {
        int idPais;
        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD)) {
            try (PreparedStatement getCountryId = connection.prepareStatement(GET_COUNTRY_ID)) {
                getCountryId.setString(1, pais);
                try (ResultSet resultados = getCountryId.executeQuery()) {
                    resultados.next();
                    idPais = resultados.getInt("country_id");
                }
            }
            try (PreparedStatement insertCiudad = connection.prepareStatement(INSERT_CIUDAD)) {
                insertCiudad.setString(1, ciudad);
                insertCiudad.setInt(2, idPais);
                insertCiudad.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean paisExiste(String pais) {
        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD)) {
            try (PreparedStatement ps1 = connection.prepareStatement(PAIS_EXISTE)) {
                ps1.setString(1, pais);
                try (ResultSet resultados = ps1.executeQuery()) {
                    return resultados.isBeforeFirst();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean ciudadExiste(String ciudad) {
        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD)) {
            try (PreparedStatement ps2 = connection.prepareStatement(CIUDAD_EXISTE)) {
                ps2.setString(1, ciudad);
                try (ResultSet resultados = ps2.executeQuery()) {
                    return resultados.isBeforeFirst();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
