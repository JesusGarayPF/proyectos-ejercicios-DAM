package org.example.simulacro2ev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_status")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_status_id;
    @Column(length = 100, nullable = false)
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
