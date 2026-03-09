package test;

import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AlquilerManager {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        try {
            int staffId = 999999999;
            while (staffId != 0) {
                System.out.println("Introduce el ID del empleado que realiza el alquiler (Introduce 0 para salir): ");
                staffId = Integer.parseInt(scanner.nextLine());
                em.getTransaction().begin();
                Staff staff = em.find(Staff.class, staffId);
                if (staff == null) {
                    System.out.println("Empleado no encontrado\nSaliendo del programa...");
                    break;
                } else {
                    mostrarDatosAlquiler(em, staff);
                }
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void mostrarDatosAlquiler(EntityManager em, Staff staff) {
        System.out.println("""
                ************************
                |  DATOS DEL  ALQUILER |
                ************************
                """);
        // Datos de la tienda del empleado
        System.out.println("ALQUILADO EN TIENDA");
        System.out.printf("Tienda ID: %d || Direccion: %s %s %s %s\n",
                staff.getStore().getId(),
                staff.getStore().getAddress().getAddress(),
                staff.getStore().getAddress().getAddress2() != null ? staff.getStore().getAddress().getAddress2() : "N/A",
                staff.getStore().getAddress().getDistrict(),
                staff.getStore().getAddress().getCiudad().getCity());

        // Comprobación de existencia en inventario
        System.out.println("Introduce el ID de la pelicula que se desea alquilar:");
        int filmId = Integer.parseInt(scanner.nextLine());
        int cantidad = consultarCantidadEnInventario(em, staff.getStore().getId(), filmId);
        if (cantidad > 0) {
            System.out.printf("Pelicula en inventario. NºCopias: %d\n", cantidad);
        } else {
            System.out.println("Pelicula fuera de catalogo");
            return;
        }
        List<Integer> listaIdDisponibles = obtenerPeliculasDisponibles(em, filmId, staff.getStore().getId());
        System.out.println("Peliculas disponibles en tienda: " + listaIdDisponibles.size());
        System.out.println("IDs de peliculas disponibles:");
        listaIdDisponibles.forEach(inventory_id -> {
            System.out.println("Id Disponible: " + inventory_id);
        });
        boolean idCorrecto = false;
        int inventoryId = 99999999;
        while (!idCorrecto) {
            System.out.println("Introduce el ID disponible en tienda:");
            inventoryId = Integer.parseInt(scanner.nextLine());
            if (!listaIdDisponibles.contains(inventoryId)) {
                System.out.println("El ID introducido no existe en el inventario. Inserte uno de los listados");
            } else {
                idCorrecto = true;
            }
        }

        //Comprobacion de existencia del cliente
        System.out.println("Introduce el Id del cliente:");
        int customerId = Integer.parseInt(scanner.nextLine());
        List<Customer> listaClientes = (List<Customer>) staff.getStore().getListaClientes();
        boolean customerExist = false;
        for (Customer customer : listaClientes) {
            if (customerId == customer.getId()) {
                customerExist = true;
            }
        }
        if (customerExist) {
            System.out.println("El cliente existe");
        } else {
            System.out.println("El cliente no existe");
            return;
        }
        //Crear alquiler
        crearRental(em, inventoryId, customerId, staff.getId());

    }

    private static int consultarCantidadEnInventario(EntityManager em, int storeId, int filmId) {
        String hql = "SELECT COUNT(i) " +
                "FROM Inventory i " +
                "WHERE i.store.id = :storeId AND i.film.id = :filmId";

        Long cantidad = em.createQuery(hql, Long.class)
                .setParameter("storeId", storeId)
                .setParameter("filmId", filmId)
                .getSingleResult();
        return cantidad.intValue();
    }

    private static List<Integer> obtenerPeliculasDisponibles(EntityManager em, int filmId, int storeId) {
        String hql = """
                    SELECT i.id
                    FROM Inventory i
                    WHERE i.film.id = :filmId
                    AND i.store.id = :storeId
                    AND NOT EXISTS (
                        SELECT r.id
                        FROM Rental r
                        WHERE r.inventory.id = i.id
                        AND r.returnDate IS NULL
                    )
                """;
        return em.createQuery(hql, Integer.class)
                .setParameter("filmId", filmId)
                .setParameter("storeId", storeId)
                .getResultList();
    }

    private static void crearRental(EntityManager em, int inventory_id, int customer_id, int staff_id) {
        Rental rental = new Rental();
        rental.setRentalDate(Instant.now());
        rental.setInventory(em.getReference(Inventory.class, inventory_id));
        rental.setCustomer(em.getReference(Customer.class, customer_id));
        rental.setReturnDate(null);
        rental.setStaff(em.getReference(Staff.class, staff_id));
        rental.setLastUpdate(Instant.now());
        em.persist(rental);
        System.out.println("Rental creado con éxito");
    }

}
