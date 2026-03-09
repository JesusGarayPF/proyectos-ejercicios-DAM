import java.sql.*;

public class Tarea02 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_QUERY_01 = """

            select f.title, f.release_year, l.name, l2.name, f.rating, f.description
            from film f
            inner join language l on f.language_id = l.language_id
            left join language l2 on l2.language_id = f.language_id
            order by f.title asc;
             """;

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultados = statement.executeQuery(SQL_QUERY_01)) {

            while(resultados.next()){
                String title = resultados.getString("f.title");
                String releaseYear = resultados.getString("f.release_year");
                String languageName = resultados.getString("l.name");
                String originalLanguageName = resultados.getString("l2.name");
                String rating = resultados.getString("f.rating");
                String description = resultados.getString("f.description");
                System.out.printf("Titulo: %s || Año Salida: %s || Idioma: %s || Idioma Original: %s || Valoracion: %s\n",title, releaseYear,languageName,originalLanguageName, rating);
                System.out.printf("Descripcion: %s\n", description);
                System.out.println("-".repeat(100));
            }
        }
        System.out.println("Fin de programa");
    }
}
