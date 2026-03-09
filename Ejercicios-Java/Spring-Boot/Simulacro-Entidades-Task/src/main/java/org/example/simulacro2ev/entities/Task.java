package org.example.simulacro2ev.entities;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table (name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_id;
    @Column(length = 255, nullable = false )
    private String title;
    @Column(length = 4000)
    private String description;
    @ManyToOne
    @JoinColumn(name = "task_category_id", nullable = false)
    private TaskCategory tareaCategoria;
}
