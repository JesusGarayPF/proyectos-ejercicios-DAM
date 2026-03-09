package test;

import entities.Address;
import entities.Customer;
import entities.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class InformeCliente {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();

        try {
            while (true) {
                System.out.print("Introduce el ID del cliente (0 para salir): ");
                int customerId = Integer.parseInt(scanner.nextLine());

                if (customerId == 0) {
                    System.out.println("Saliendo del programa...");
                    break;
                }

                em.getTransaction().begin();
                Customer customer = em.find(Customer.class, customerId);

                if (customer == null) {
                    System.out.println("Cliente no encontrado.");
                } else {
                    mostrarDatosCliente(customer);
                }

                em.getTransaction().commit();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void mostrarDatosCliente(Customer customer) {
        System.out.println("""
                **********************
                |  DATOS DEL CLIENTE |
                **********************
                """);
        System.out.printf("ID: %d || Nombre: %s || Apellido: %s\nEmail: %s || Activo: %s\nFecha de Creación: %s || Fecha de Modificación: %s\n",
                customer.getId(), customer.getFirstName(), customer.getLastName(),
                customer.getEmail(),
                customer.getActive() ? "Sí" : "No",
                customer.getCreateDate(), customer.getLastUpdate());

        // Mostrar dirección
        if (customer.getAddress() != null) {
            System.out.println("\nDIRECCION DEL CLIENTE:");
            Address address = customer.getAddress();
            System.out.printf("Dirección: %s\nCiudad: %s\nProvincia: %s\n",
                    address.getAddress(), address.getCiudad().getCity(), address.getDistrict());
        }

        // Mostrar tienda asociada
        if (customer.getStore() != null) {
            System.out.println("\nTIENDA ASOCIADA:");
            Store store = customer.getStore();
            System.out.printf("Tienda ID: %d\nDirección: %s\nCiudad: %s\nProvincia: %s\n",
                    store.getId(), store.getAddress().getAddress(),
                    store.getAddress().getCiudad().getCity(),
                    store.getAddress().getDistrict());
        }
        // Mostrar alquileres
        if (!customer.getListaRentals().isEmpty()) {
            System.out.println("\nALQUILERES REALIZADOS:");
            customer.getListaRentals().forEach(rental -> {
                System.out.printf("Alquiler ID: %d, Película: %s, Fecha de Alquiler: %s\n",
                        rental.getId(), rental.getInventory().getFilm().getTitle(), rental.getRentalDate());
            });
        } else {
            System.out.println("\nEl cliente no ha realizado alquileres.");
        }

        // Mostrar pagos
        if (!customer.getListaPayments().isEmpty()) {
            System.out.println("\nPAGOS REALIZADOS:");
            customer.getListaPayments().forEach(payment -> {
                System.out.printf("Pago ID: %d, Cantidad: %.2f, Fecha de Pago: %s\n",
                        payment.getId(), payment.getAmount(), payment.getPaymentDate());
            });
        } else {
            System.out.println("\nEl cliente no ha realizado pagos.");
        }
    }
}

