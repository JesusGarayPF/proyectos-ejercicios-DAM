import java.sql.*;

public class Tarea01 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_QUERY_01 = "select title, release_year, language_id, original_language_id, rating, description from film";

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultados = statement.executeQuery(SQL_QUERY_01)) {

            while(resultados.next()){
                String title = resultados.getString("title");
                String releaseYear = resultados.getString("release_year");
                int languageId = resultados.getInt("language_id");
                int originalLanguageId = resultados.getInt("original_language_id");
                String rating = resultados.getString("rating");
                String description = resultados.getString("description");
                System.out.printf("Titulo: %s || Año Salida: %s || Idioma: %s || Idioma Original: %s || Valoracion: %s\n",title, releaseYear, languageId, originalLanguageId, rating);
                System.out.printf("Descripcion: %s\n", description);
                System.out.println("-".repeat(100));
            }
        }
        System.out.println("Fin de programa");
    }
}
