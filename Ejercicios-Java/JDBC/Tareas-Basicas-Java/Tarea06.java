import java.sql.*;
import java.util.Scanner;

public class Tarea06 {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_QUERY_01 = """

            select first_name, last_name, email, active, a.address,
            a.address2, a.district, a.postal_code, a.phone, c2.city, c3.country
            from customer c
            inner join sakila.address a on c.address_id = a.address_id
            inner join sakila.city c2 on a.city_id = c2.city_id
            inner join sakila.country c3 on c2.country_id = c3.country_id
            where customer_id = ?
             """;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean terminado = false;
        do {
            System.out.println("Introduce el id de cliente para tu consulta");
            int idConsulta = sc.nextInt();
            //1.Conectar a la base de datos
            try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD)) {
                //2.Preparar sentencia SQL. Recibe la query con las interrogaciones
                try (PreparedStatement ps1 = connection.prepareStatement(SQL_QUERY_01)) {
                    //3. Parametrizar sentencia (cambiar las ? por valores)
                    ps1.setInt(1, idConsulta);
                    //4. Obtener resultados
                    try (ResultSet resultados = ps1.executeQuery()) {
                        //5. Recorrer resultados
                        //5.1 Mirar si hay resultados. next() devuelve true si hay datos
                        if (resultados.next()) {
                            //5.2 - Mostrar datos
                            String name = resultados.getString("first_name");
                            String lastName = resultados.getString("last_name");
                            String email = resultados.getString("email");
                            int active = resultados.getInt("active");
                            String address = resultados.getString("address");
                            String address2 = resultados.getString("address2");
                            String district = resultados.getString("district");
                            String postalCode = resultados.getString("postal_code");
                            String phone = resultados.getString("phone");
                            String city = resultados.getString("city");
                            String country = resultados.getString("country");
                            System.out.printf("""
                                    Nombre: %s|| Apellido:%s || Correo: %s || Activo: %d||
                                    Direccion:
                                    -Linea 1: %s
                                    -Linea 2: %s
                                    -Distrito: %s
                                    -Cod.Postal: %s
                                    -Telefono: %s
                                    -Ciudad: %s
                                    -Pais: %s
                                    """, name, lastName, email, active, address, address2, district, postalCode, phone, city, country);
                            System.out.println("-".repeat(100));
                            System.out.println("Si quiere hacer otra consulta introduzca 1. Si no introduzca 0");
                            int respuesta = sc.nextInt();
                            if (respuesta == 0) terminado = true;
                        }else{
                            System.out.println("El numero de clinete indicado no existe");
                        }
                    }
                }
            }
        } while (!terminado);
        System.out.println("Fin de programa");
    }
}
