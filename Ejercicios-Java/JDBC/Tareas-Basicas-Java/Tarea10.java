import java.sql.*;
import java.util.Scanner;

public class Tarea10 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    private static final String PAIS_EXISTE = """
            select country
            from country
            where country = ?
            """;
    private static final String INSERT_PAIS = """
            insert into country (country)
            values(?)
            """;
    private static final String SQL_SELECT_PAISES = """
            select * from country;
            """;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean terminado = false;
        do {
            try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD)) {
                String pais="";
                while (pais == null || pais.isBlank()) {
                    System.out.println("Que país quiere añadir?");
                    pais = sc.nextLine();
                    if (pais == null || pais.isBlank()){
                        System.out.println("El pais no puede quedar vacio");
                    }
                }
                if (paisExiste(pais, connection)) {
                    System.out.println("El pais elegido existe en la base de datos");
                    System.out.println("No se realizan cambios");
                } else {
                    System.out.println("El pais no existe en la base de datos y será añadido");
                    añadirPais(pais, connection);
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

    private static void añadirPais(String pais, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PAISES, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery()) {
            rs.moveToInsertRow();
            rs.updateString("country", pais);
            rs.insertRow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*try (PreparedStatement insertPais = connection.prepareStatement(INSERT_PAIS, Statement.RETURN_GENERATED_KEYS)) {
            insertPais.setString(1, pais);
            insertPais.executeUpdate();
            try (ResultSet generatedKeys = insertPais.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idPais = generatedKeys.getInt(1);
                    System.out.println("El país se ha añadido con éxito con ID: " + idPais);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    private static boolean paisExiste(String pais, Connection connection) {
        try (PreparedStatement ps1 = connection.prepareStatement(PAIS_EXISTE)) {
            ps1.setString(1, pais);
            try (ResultSet resultados = ps1.executeQuery()) {
                return resultados.isBeforeFirst();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

