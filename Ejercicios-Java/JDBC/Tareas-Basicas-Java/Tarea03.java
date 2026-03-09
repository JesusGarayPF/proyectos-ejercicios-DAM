import java.sql.*;

public class Tarea03 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_QUERY_01 = """

            select sum(amount) as Cantidad, date(payment_date) as Fecha
            from payment
            group by Fecha
            order by Fecha desc;
             """;

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultados = statement.executeQuery(SQL_QUERY_01)) {

            while(resultados.next()){
                double cantidad = resultados.getDouble("Cantidad");
                String paymentDate = resultados.getString("Fecha");
                System.out.printf("Cantidad: %.2f || Fecha: %s\n", cantidad, paymentDate);
                System.out.println("-".repeat(100));
            }
        }
        System.out.println("Fin de programa");
    }
}
