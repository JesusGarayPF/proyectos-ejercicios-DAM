package test;

import entities.Staff;
import entities.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class InformeEmpleado {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();

        try {
            while (true) {
                System.out.println("Introduce el ID del empleado (0 para salir):");
                int staffID = Integer.parseInt(scanner.nextLine());

                if (staffID == 0) {
                    System.out.println("Saliendo del programa...");
                    break;
                }
                em.getTransaction().begin();
                Staff staff = em.find(Staff.class, staffID);
                if (staff == null) {
                    System.out.println("Empleado no encontrado");
                } else {
                    mostrarDatosEmpleado(staff);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void mostrarDatosEmpleado(Staff staff) {
        System.out.println("""
                **************************
                |  DATOS  DEL  EMPLEADO  |
                **************************
                """);
        System.out.printf("Staff ID: %d || Nombre: %s || Apellido: %s\nPicture: %s\nUsuario: %s || Contraseña: %s || Activo: %b\nDireccion ID: %d Store ID: %d\nUltima actualizacion: %s",
                staff.getId(), staff.getFirstName(), staff.getLastName(),
                staff.getPicture() != null ? "Imagen disponible" : "Imagen no disponible",
                staff.getUsername(), staff.getPassword(),
                staff.getActive() ? "Si" : "No",
                staff.getAddress().getId(), staff.getStore().getId(),
                staff.getLastUpdate());
        //Mostrar direccion
        if (staff.getAddress() != null) {
            System.out.println("\nDIRECCION DEL EMPLEADO");
            System.out.printf("Dirección: %s %s\nCiudad: %s\nProvincia: %s\n",
                    staff.getAddress().getAddress(), staff.getAddress().getAddress2(), staff.getAddress().getCiudad(), staff.getAddress().getDistrict());
        }

        //Mostrar tienda asociada
        if (staff.getAddress() != null) {
            System.out.println("\nTIENDA ASOCIADA");
            Store store = staff.getStore();
            System.out.printf("Tienda ID: %d\nDirección: %s\nCiudad: %s\nProvincia: %s\n",
                    store.getId(), store.getAddress().getAddress(),
                    store.getAddress().getCiudad().getCity(),
                    store.getAddress().getDistrict());
        }
        //Alquileres realizados
        if (!staff.getListaRentals().isEmpty()) {
            System.out.println("\nALQUILERES REALIZADOS: ");
            staff.getListaRentals().forEach(rental -> {
                System.out.printf("Alquiler ID: %d, Película: %s, Fecha de Alquiler: %s\n",
                        rental.getId(), rental.getInventory().getFilm().getTitle(), rental.getRentalDate());
            });
        }
    }
}
