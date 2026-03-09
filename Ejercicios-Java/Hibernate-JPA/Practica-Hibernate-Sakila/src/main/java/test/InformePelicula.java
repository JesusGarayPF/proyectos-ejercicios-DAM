package test;

import entities.Film;
import entities.Inventory;
import entities.Language;
import entities.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class InformePelicula {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();

        try {
            while (true) {
                System.out.print("Introduce el ID de la pelicula (0 para salir): ");
                int peliculaId = Integer.parseInt(scanner.nextLine());

                if (peliculaId == 0) {
                    System.out.println("Saliendo del programa...");
                    break;
                }

                em.getTransaction().begin();
                Film film = em.find(Film.class, peliculaId);

                if (film == null) {
                    System.out.println("Pelicula no encontrada");
                } else {
                    mostrarDatosPelicula(em,film);
                }
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }finally {
            em.close();
            emf.close();
        }
    }

    private static void mostrarDatosPelicula(EntityManager em, Film film) {
        System.out.println("""
                **************************
                |  DATOS DE LA PELICULA  |
                **************************
                """);
        System.out.printf("ID: %d\nTitulo: %s\nDescripcion: %s\nAño de salida: %s\nId de Idioma: %s\nIdioma Original: %s\nDuracion de alquiler: %d\nTasa de alquiler: %f\nDuracion: %d\nCoste de reemplazo: %f\nValoracion: %s\nCaracteristicas Especiales: %s\nUltima actualizacion: %s\n",
                film.getId(), film.getTitle(), film.getDescription(), film.getReleaseYear(),
                film.getLanguage() != null ? film.getLanguage().getName() : "No disponible",
                film.getOriginalLanguage() != null ? film.getOriginalLanguage().getName() : "No disponible",
                film.getRentalDuration(), film.getRentalRate(), film.getLength(), film.getReplacementCost(), film.getRating(),
                film.getSpecialFeatures(), film.getLastUpdate());

        //Mostrar categorías
        if(!film.getListaCategories().isEmpty()){
            System.out.println("\nCATEGORIA DE LAS PELICULAS");
            film.getListaCategories().forEach( category->{
                System.out.printf("Categoria ID: %d\nNombre: %s\nUltima actualizacion: %s\n",
                        category.getId(), category.getName(), category.getLastUpdate());
            });
        }else{
            System.out.println("Pelicula sin categorizar o error desconocido");
        }

        //Mostrar idioma Original
        if(film.getOriginalLanguage() != null){
            System.out.println("\nIDIOMA ORIGINAL");
            Language language = film.getOriginalLanguage();
            System.out.println("VO: " + language.getName());
        }else{
            System.out.println("Idioma Original sin definir");
        }

        //Mostrar idioma Pelicula
        if(film.getLanguage() != null){
            System.out.println("\nIDIOMA PELICULA");
            Language languaje = film.getLanguage();
            System.out.println("Idioma: " + languaje.getName());
        }else{
            System.out.println("Idioma sin definir");
        }

        //Mostrar actores
        if (film.getListaActores() != null){
            System.out.println("\nLISTA DE ACTORES");
            film.getListaActores().forEach(actor -> {
                System.out.printf("Actor ID: %d || Nombre: %s || Apellido: %s || Ultima actualizacion: %s\n",
                        actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate());
            });
        }else{
            System.out.println("No se han encontrado actores asociados a esta pelicula");
        }
        //Informacion disponibilidad
        mostrarDisponibilidadPorTienda(em, film);
    }
    private static void mostrarDisponibilidadPorTienda(EntityManager em, Film film) {
        String hql = "SELECT i.store.id, COUNT(i) " +
                "FROM Inventory i " +
                "WHERE i.film.id = :filmId " +
                "GROUP BY i.store.id";

        List<Object[]> resultados = em.createQuery(hql, Object[].class)
                .setParameter("filmId", film.getId())
                .getResultList();

        if (!resultados.isEmpty()) {
            System.out.println("\nDISPONIBILIDAD POR TIENDA");
            for (Object[] resultado : resultados) {
                Byte tiendaId = (Byte) resultado[0];
                Long cantidad = (Long) resultado[1];

                Store tienda = em.find(Store.class, tiendaId);
                if (tienda != null) {
                    System.out.printf("Tienda ID: %d || Copias disponibles: %d\n", tiendaId, cantidad);
                    System.out.printf("Direccion: %s, %s, %s, %s\n",
                            tienda.getAddress().getAddress(),
                            tienda.getAddress().getAddress2() != null ? tienda.getAddress().getAddress2() : "N/A",
                            tienda.getAddress().getDistrict(),
                            tienda.getAddress().getCiudad().getCity());
                } else {
                    System.out.printf("Tienda ID: %d || Copias disponibles: %d\n", tiendaId, cantidad);
                    System.out.println("No se encontraron detalles de la tienda.");
                }
            }
        } else {
            System.out.println("No hay copias disponibles de esta película en ninguna tienda.");
        }
    }
}
