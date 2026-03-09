package tema_02;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConexionAlternativo {
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

                            // Imprimir encabezado
                            List<Integer> anchuraColumnas = new ArrayList<>();
                            StringBuilder encabezado = new StringBuilder();
                            for (int i = 1; i <= totalColumnas; i++) {
                                int ancho = Math.max(metaData.getColumnName(i).length(), 10); // mínimo de 10 caracteres por columna
                                anchuraColumnas.add(ancho);
                                encabezado.append("| ").append(String.format("%-" + ancho + "s", metaData.getColumnName(i)));
                            }
                            encabezado.append(" |");
                            System.out.println(encabezado);

                            // Imprimir separador del encabezado
                            StringBuilder separador = new StringBuilder();
                            for (int i = 0; i < totalColumnas; i++) {
                                separador.append("+").append("-".repeat(anchuraColumnas.get(i) + 2));
                            }
                            separador.append("+");
                            System.out.println(separador);

                            // Imprimir filas de datos
                            while (rs.next()) {
                                StringBuilder fila = new StringBuilder();
                                for (int i = 1; i <= totalColumnas; i++) {
                                    String valor = getValor(rs, i);
                                    fila.append("| ").append(String.format("%-" + anchuraColumnas.get(i - 1) + "s", valor));
                                }
                                fila.append(" |");
                                System.out.println(fila);
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

    private static String getValor(ResultSet rs, int i) throws SQLException {
        int tipoValor = rs.getMetaData().getColumnType(i);
        String valor;
        switch (tipoValor) {
            case Types.INTEGER:
                valor = rs.wasNull() ? "NULL" : String.valueOf(rs.getInt(i));
                break;
            case Types.VARCHAR:
                valor = rs.wasNull() ? "NULL" : rs.getString(i);
                break;
            case Types.DOUBLE:
                valor = rs.wasNull() ? "NULL" : String.valueOf(rs.getDouble(i));
                break;
            case Types.DATE:
                LocalDate fecha = (rs.getDate(i) != null) ? rs.getDate(i).toLocalDate() : null;
                valor = (fecha == null) ? "NULL" : fecha.toString();
                break;
            case Types.TIMESTAMP:
                valor = (rs.getTimestamp(i) == null) ? "NULL" : rs.getTimestamp(i).toString();
                break;
            case Types.BOOLEAN:
                valor = rs.wasNull() ? "NULL" : String.valueOf(rs.getBoolean(i));
                break;
            case Types.BIGINT:
                valor = rs.wasNull() ? "NULL" : String.valueOf(rs.getLong(i));
                break;
            case Types.DECIMAL:
                valor = (rs.getBigDecimal(i) == null) ? "NULL" : rs.getBigDecimal(i).toString();
                break;
            case Types.FLOAT:
                valor = rs.wasNull() ? "NULL" : String.valueOf(rs.getFloat(i));
                break;
            default:
                valor = "NULL";
                break;
        }
        return valor;
    }
}

