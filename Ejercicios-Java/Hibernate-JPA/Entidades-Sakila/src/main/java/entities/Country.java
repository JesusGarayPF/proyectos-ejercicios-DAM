package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "country", schema = "sakila")
public class Country {
    @Id
    @Column(name = "country_id", nullable = false)
    private Short id;

    @Column(name = "country", nullable = false, length = 50)
    private String name;

    @ColumnDefault("current_timestamp()")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @OneToMany(mappedBy = "country")
    private Set<City> cities = new LinkedHashSet<>();

}