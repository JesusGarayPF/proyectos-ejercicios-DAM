import java.sql.*;
import java.util.Scanner;

public class Tarea11 {
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
                String pais = pedirPais(sc, connection);
                if (paisExiste(pais, connection)) {
                    System.out.println("El pais elegido existe en la base de datos");
                    System.out.println("Introduzca el nuevo nombre para ese pais");
                    pais = pedirPais(sc, connection);
                    if (paisExiste(pais, connection)) {
                        System.out.println("El nuevo nombre para el pais elegido ya está en uso.\nFin de programa");
                    } else {
                        modificarPais(pais, connection);
                        System.out.println("Nombre modificado");
                    }
                } else {
                    System.out.println("El pais no existe en la base de datos y no habrá cambios");
                }
                System.out.println("Quiere añadir nuevos paises? Introduzca 0 para terminar o 1 para continuar");
                int respuesta = sc.nextInt();
                sc.nextLine();
                if (respuesta == 0) terminado = true;
            }
        } while (!terminado);
        System.out.println("Fin de programa");
    }

    private static String pedirPais(Scanner sc, Connection connection) {
        String pais = "";
        while (pais == null || pais.isBlank()) {
            System.out.println("Que país quiere añadir?");
            pais = sc.nextLine();
            if (pais == null || pais.isBlank()) System.out.println("El pais no puede quedar vacio");
        }
        return pais;
    }

    private static int modificarPais(String pais, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PAISES, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = ps.executeQuery()) {
            rs.moveToInsertRow();
            rs.updateString("country", pais);
            rs.insertRow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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

