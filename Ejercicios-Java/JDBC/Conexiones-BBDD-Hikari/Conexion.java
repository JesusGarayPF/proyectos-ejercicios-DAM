package tema_02;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conexion {
    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection connection = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD)) {
            System.out.println("Conectado a la base de datos");
            while (true) {
                System.out.println("Introduce una sentencia SQL o DML. Debe ser de escribirse en una sola línea\nEnvía un mensaje vacío para terminar ");
                String sentencia = sc.nextLine();
                if (sentencia.isEmpty()) {
                    System.out.println("Terminando programa");
                    break;
                }

                try (PreparedStatement ps1 = connection.prepareStatement(sentencia)) {
                    boolean tieneResultados = ps1.execute();
                    if (tieneResultados) {
                        try (ResultSet rs = ps1.getResultSet()) {
                            ResultSetMetaData metaData = rs.getMetaData();
                            int totalColumnas = metaData.getColumnCount();
                            List<Integer> anchuraColumnas = new ArrayList<>();
                            for (int i = 1; i <= totalColumnas; i++) {
                                int ancho = Math.max(metaData.getColumnName(i).length(), 10);
                                anchuraColumnas.add(ancho);
                                System.out.print("| " + String.format("%-" + ancho + "s", metaData.getColumnName(i)) + " ");
                            }
                            System.out.println("|");
                            for (int i = 0; i < totalColumnas; i++) {
                                System.out.print("+" + "-".repeat(anchuraColumnas.get(i) + 2));
                            }
                            System.out.println("+");
                            while (rs.next()) {
                                for (int i = 1; i <= totalColumnas; i++) {
                                    int tipoValor = rs.getMetaData().getColumnType(i);
                                    String valor;
                                    switch (tipoValor) {
                                        case Types.INTEGER:
                                            int numInteger = rs.getInt(i);
                                            valor = rs.wasNull() ? "0" : String.valueOf(numInteger);
                                            break;
                                        case Types.VARCHAR:
                                            String texto = rs.getString(i);
                                            valor = rs.wasNull() ? "NULL" : texto;
                                            break;
                                        case Types.DOUBLE:
                                            double numDouble = rs.getDouble(i);
                                            valor = rs.wasNull() ? "0.0" : String.valueOf(numDouble);
                                            break;
                                        case Types.DATE:
                                            Date date = rs.getDate(i);
                                            LocalDate fecha = (date != null) ? date.toLocalDate() : null;
                                            valor = (fecha == null) ? "NULL" : fecha.toString();
                                            break;
                                        case Types.TIMESTAMP:
                                            Timestamp hora = rs.getTimestamp(i);
                                            valor = (hora == null) ? "NULL" : hora.toString();
                                            break;
                                        case Types.BOOLEAN:
                                            boolean boleano = rs.getBoolean(i);
                                            valor = rs.wasNull() ? "NULL" : String.valueOf(boleano);
                                            break;
                                        case Types.BIGINT:
                                            long numBigint = rs.getLong(i);
                                            valor = rs.wasNull() ? "0" : String.valueOf(numBigint);
                                            break;
                                        case Types.DECIMAL:
                                            BigDecimal d1 = rs.getBigDecimal(i);
                                            valor = (d1 == null) ? "NULL" : d1.toString();
                                            break;
                                        case Types.FLOAT:
                                            float numFloat = rs.getFloat(i);
                                            valor = rs.wasNull() ? "0.0" : String.valueOf(numFloat);
                                            break;
                                        default:
                                            valor = "NULL";
                                            break;
                                    }
                                    System.out.print("| " + String.format("%-" + anchuraColumnas.get(i - 1) + "s", valor) + " ");
                                }
                                System.out.println("|");
                            }
                        }
                    } else {
                        System.out.println("Sentencia ejecutada con éxito. Se han actualizado " + ps1.getUpdateCount() + " filas.");
                    }
                } catch (SQLException e) {
                    // Manejar errores de la sentencia SQL sin detener el programa
                    System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
