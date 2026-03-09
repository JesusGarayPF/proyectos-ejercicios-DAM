package Tema14_Ej12_15.entitites;

import java.util.Objects;

public class ActorInFilm implements Comparable<ActorInFilm> {
    private int actorId;
    private int filmId;

    public ActorInFilm(int actorId, int filmId) {
        this.actorId = actorId;
        this.filmId = filmId;
    }

    public int getActorId() {
        return actorId;
    }

    public int getFilmId() {
        return filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActorInFilm that)) return false;
        return actorId == that.actorId && filmId == that.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, filmId);
    }

    @Override
    public int compareTo(ActorInFilm o) {
        if (this.actorId > o.actorId) {
            return 1;
        } else if (this.actorId < o.actorId) {
            return -1;
        } else {
            if (this.filmId > o.filmId) {
                return 1;
            } else if (this.filmId == o.filmId) {
                return 0;
            } else
                return -1;
        }
    }
}

