package es.garayfrancojesus.practicasimulacro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer task_status_id;
    @Column(nullable = false, length = 100)
    private String name;

    public Integer getTask_status_id() {
        return task_status_id;
    }

    public void setTask_status_id(Integer task_status_id) {
        this.task_status_id = task_status_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
