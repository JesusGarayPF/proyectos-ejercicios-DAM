package Tema12.ej19;

import java.io.*;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static final String CONNECTION = "jdbc:mariadb://localhost/sakila";
    private static final String USER = "sakilauser";
    ;
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_QUERY = """
            select * from customer
            """;
    private static final Path DESTINO = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\ej19.txt");
    private static final Path DESTINO_BINARIO = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\ej19.dat");

    public static void main(String[] args) throws SQLException, IOException {
        List<Customer> clientes = listarClientes();
        clientes.sort(Comparator.comparing(Customer::getFirstName).thenComparing(Customer::getLastName));
        generarFichero(clientes, DESTINO, DESTINO_BINARIO);
    }

    private static List<Customer> listarClientes() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(CONNECTION, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SQL_QUERY)) {
                ResultSet rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) {
                    System.out.println("No hay datos que exportar");
                } else {
                    while (rs.next()) {
                        int customerId = rs.getInt("customer_id");
                        int storeId = rs.getInt("store_id");
                        String firstName = rs.getString("first_name");
                        String lastName = rs.getString("last_name");
                        String email = rs.getString("email");
                        int addressId = rs.getInt("address_id");
                        boolean active = rs.getBoolean("active");
                        LocalDate createDate = rs.getDate("create_date").toLocalDate();
                        LocalDate lastUpdate = rs.getDate("last_update").toLocalDate();
                        Customer cs = new Customer(customerId, storeId, firstName, lastName, email, addressId, active, createDate, lastUpdate);
                        customers.add(cs);
                    }
                }
            }
        }
        return customers;
    }

    private static void generarFichero(List<Customer> clientes, Path pathFichero, Path pathBinario) throws IOException {
        if (pathFichero.toFile().exists()) {
            throw new RuntimeException("No se puede exportar la lista porque ya existe");
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(DESTINO.toFile()))) {
                for (Customer cliente : clientes) {
                    pw.printf("%s\n", cliente);
                }
                System.out.println("Fichero de texto creado");
            }

        }
        if (pathBinario.toFile().exists()) {
            throw new RuntimeException("No se puede exportar el binario porque ya exite");
        } else {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DESTINO_BINARIO.toFile()))) {
                for (Customer cliente : clientes) {
                    oos.writeObject(cliente);
                }
                System.out.println("Fichero binario creado");
            }
        }
    }
}

