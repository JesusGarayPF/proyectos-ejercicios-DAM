package org.example.simulacro2ev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task_assignment")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TaskAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_assignment_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "family_member_id", nullable = false)
    private FamilyMember familyMember;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_status_id", nullable = false)
    private TaskStatus taskStatus;

    @Column(nullable = false)
    private LocalDateTime assignment_datetime;

    @Column
    private LocalDateTime completion_datetime;

    public void setTask_assignment_id(Integer task_assignment_id) {
        this.task_assignment_id = task_assignment_id;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setAssignment_datetime(LocalDateTime assignment_datetime) {
        this.assignment_datetime = assignment_datetime;
    }

    public void setCompletion_datetime(LocalDateTime completion_datetime) {
        this.completion_datetime = completion_datetime;
    }
}


