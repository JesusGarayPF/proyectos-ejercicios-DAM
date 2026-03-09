/*package Tema12;

import Tema12.ej19.Customer;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class Tarea20 {

    private static final String ORIGEN = "C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\ej19.dat";
    private static final String DESTINO ="C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios\\ej20.txt";


    public static void main(String[] args) {
       List<Customer> clientes = leerCustomerFichero(ORIGEN);{

        }
    }
    private static List<Customer> leerCustomerFichero (String pathFicheroDatos){

        File ficheroDatos = new File(pathFicheroDatos);
        if (!ficheroDatos.exists()){
            throw new RuntimeException("No existe el fichero");
        }
        try(ObjectInputStream stream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(ficheroDatos)))) {
        } catch (EOFException e){

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    private static void generarInformeTexto(List<Customer> customers, String pathFicheroInforme){
        try(PrintWriter pw  = new PrintWriter(new File(pathFicheroInforme))){
            pw.printf("| %5d | %-20.20s| %-20.20s| %-10.10s| %s| %s| %s | %s |\n", );
            for (Customer customer : customers) {
                pw.println("-".repeat(104));
                pw.printf("| %5d | %-20.20s| %-20.20s| %-10.10s| %s| %s| %s | %s |\n",
                        customer.getCustomerId(),
                        customer.getStoreId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail(),
                        customer.getAdressId(),
                        customer.isActive(),
                        customer.getFechaCreacion(),
                        customer.getFechaActualizacion());
            }
        }
    }
}
*/