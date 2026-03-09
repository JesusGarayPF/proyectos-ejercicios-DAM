package Tema14_Ej12_15.entitites;

import java.util.Objects;

public class Actor implements Comparable<Actor> {
    private int actorId;
    private String firstName;
    private String lastName;

    public Actor(int actorId) {
        this.actorId = actorId;
    }

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Actor(int actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getActorId() {
        return actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor actor)) return false;
        return actorId == actor.actorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId);
    }

    @Override
    public int compareTo(Actor o) {
        if (this.actorId > o.actorId){
            return 1;
        } else if (this.actorId == o.actorId) {
            return 0;
        }else
            return -1;
    }
}
