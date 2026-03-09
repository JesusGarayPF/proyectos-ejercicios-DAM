package Tema12.ej10;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MediaUtilities {

    public static Media newRandomMedia() {
        Random rnd = new Random();
        int rndTipo = rnd.nextInt(1, 3);
        Faker faker = new Faker();
        Tipo tipo;
        if (rndTipo == 1) {
            tipo = Tipo.MUSICA;
        } else {
            tipo = Tipo.PELICULA;
        }
        int id = faker.number().numberBetween(1, 1000);
        String titulo;
        if (tipo.equals(Tipo.MUSICA)) {
            titulo = faker.book().title();
        } else {
            titulo = faker.movie().quote();
        }
        String artistaPrincipal = faker.artist().name();
        String productora = faker.company().name();
        LocalDate fechaPub = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Media rMedia = new Media(id, titulo, artistaPrincipal, productora, fechaPub, tipo);
        return rMedia;
    }

    public static List<Media> newRandomMedia(int count) {
        List<Media> randomMediaList= new ArrayList<>();
        while(randomMediaList.size() < count){
            randomMediaList.add(newRandomMedia());
        }
        return randomMediaList;
    }
}
