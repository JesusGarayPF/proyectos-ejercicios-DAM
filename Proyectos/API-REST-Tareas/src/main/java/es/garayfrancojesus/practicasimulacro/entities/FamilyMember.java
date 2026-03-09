package es.garayfrancojesus.practicasimulacro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer family_member_id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;

    public Integer getId() {return family_member_id;}

    public void setId(Integer id) {
        this.family_member_id = family_member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
