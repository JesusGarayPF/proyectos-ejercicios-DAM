package tema_02.lombok;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientDataAccess {
    private static String QUERY = """
SELECT c.customer_id, c.first_name, c.last_name, c.email, c.active, c.last_update, a.district, a.address, a.address2, ci.city, co.country
FROM customer c
INNER JOIN address a ON c.address_id = a.address_id
INNER JOIN city ci ON a.city_id = ci.city_id
INNER JOIN country co ON ci.country_id = co.country_id
WHERE c.first_name LIKE CONCAT('%', ?, '%')
   OR c.last_name LIKE CONCAT('%', ?, '%');
""";

    public static List<ClientDetails> findClientsDetails(String search) throws SQLException {
        List<ClientDetails> clientes = new ArrayList<>();
        try (Connection connection = SakilaConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY);) {
            ps.setString(1, search);
            ps.setString(2, search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                boolean active = rs.getBoolean("active");
                Timestamp lastUpdate = rs.getTimestamp("last_update");
                String address = rs.getString("address");
                String address2 = rs.getString("address2");
                String district = rs.getString("district");
                String city = rs.getString("city");
                String country = rs.getString("country");
                clientes.add(new ClientDetails(customerId, firstName, lastName, email, active, lastUpdate, address, address2, district, city, country));
            }
            return clientes;
        }
    }
}
