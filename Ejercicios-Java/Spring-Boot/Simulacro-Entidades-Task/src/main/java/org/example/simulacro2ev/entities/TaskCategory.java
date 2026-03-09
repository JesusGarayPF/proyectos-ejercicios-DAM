package org.example.simulacro2ev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "task_category")
public class TaskCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_category_id;
    @Column(length = 30, nullable = false)
    private String name;

    public Integer getTask_category_id() {
        return task_category_id;
    }

    public void setTask_category_id(Integer task_category_id) {
        this.task_category_id = task_category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
