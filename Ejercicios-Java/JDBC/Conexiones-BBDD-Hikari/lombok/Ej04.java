package tema_02.lombok;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ej04 {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String search = "Es imposible que introzcas esta porquería para buscar un nombre y si lo haces te buscaré y te mataré muy fuerte";
        while(!search.isBlank() || !search.isEmpty()) {
            System.out.println("Introduzca un nombre o apellido de un cliente.\nEnvie una entrada en blanco para terminar");
            search = sc.nextLine();
            try {
                List<ClientDetails> resultados = ClientDataAccess.findClientsDetails(search);
                for (ClientDetails resultado : resultados) {
                    System.out.println(resultado);
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
