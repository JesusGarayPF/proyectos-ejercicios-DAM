package Tema14_Ej12_15;

import Tema14_Ej12_15.dao.ActorDao;
import Tema14_Ej12_15.dao.ActorInFilmDao;
import Tema14_Ej12_15.dao.FilmDao;
import Tema14_Ej12_15.entitites.Actor;
import Tema14_Ej12_15.entitites.ActorInFilm;
import Tema14_Ej12_15.entitites.Film;

import java.util.*;

public class FilmActorReport {

    private static final String CADENA_CONEXION = "jdbc:mariadb://localhost/sakila";
    private static final String USUARIO = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";


    public static void main(String[] args) {
        ActorDao actorDao = new ActorDao(CADENA_CONEXION, USUARIO, PASSWORD);
        FilmDao filmDao = new FilmDao(CADENA_CONEXION, USUARIO, PASSWORD);
        ActorInFilmDao aifDao = new ActorInFilmDao(CADENA_CONEXION, USUARIO, PASSWORD);

        List<Film> peliculas = filmDao.getAll();
        System.out.printf("Se han leido %d peliculas\n", peliculas.size());
        List<Actor> actores = actorDao.getAll();
        System.out.printf("Se han leido %d actores\n", actores.size());
        List<ActorInFilm> actorsFilms = aifDao.getAll();
        System.out.printf("Se han leido %d actores en peliculas\n", actorsFilms.size());

        Map<Film, Set<Actor>> report = createFilmActorReport(peliculas, actores, actorsFilms);
        System.out.printf("En el mapa hay %d entradas\n", report.size());
        mostrarReporte(report);


    }

    private static void mostrarReporte(Map<Film, Set<Actor>> report) {
        report.forEach((pelicula, actores) -> {
            System.out.printf("""
                    ---Pelicula---
                    %s
                    ---Actores---
                    """, pelicula.getTitle());
            actores.forEach(actor -> {
                System.out.printf("%s %s \n", actor.getFirstName(), actor.getLastName());
            });
            System.out.println();
        });
    }

    private static Map<Film, Set<Actor>> createFilmActorReport(List<Film> peliculas, List<Actor> actores, List<ActorInFilm> actorsFilms) {
        Map<Film, Set<Actor>> report = new HashMap<>();
        for (Film film : peliculas) {
            Set<Actor> actorSet = createActorsSet(film.getFilmId(), actores, actorsFilms);
            report.put(film, actorSet);
        }
        return report;
    }

    private static Set<Actor> createActorsSet(int filmId, List<Actor> actores, List<ActorInFilm> aifs) {
        Set<Actor> results = new TreeSet<>(Comparator.comparing(Actor::getFirstName).thenComparing(Actor::getLastName));
        for (ActorInFilm aif : aifs) {
            if (aif.getFilmId() == filmId) {
                results.add(findActor(aif.getActorId(), actores));
            }
        }

        return results;
    }

    private static Actor findActor(int actorId, List<Actor> actores) {
        int position = actores.indexOf(new Actor(actorId));
        if (position >= 0) {
            return actores.get(position);
        }
        throw new RuntimeException("No se ha encontrado el actor");
    }
}
