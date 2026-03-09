package Tema14_Ej12_15.entitites;

import java.time.LocalDate;
import java.util.Objects;

public class Film implements Comparable<Film> {
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;

    public Film(String title) {
        this.title = title;
    }

    public Film(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public Film(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Film(String title, String description, int releaseYear) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
    }

    public Film(int filmId, String title, String description, int releaseYear) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film film)) return false;
        return filmId == film.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }

    @Override
    public int compareTo(Film o) {
        if (this.filmId > o.filmId){
            return 1;
        } else if (this.filmId == o.filmId) {
            return 0;
        }else
            return -1;
    }
}
