package es.garayfrancojesus.practicasimulacro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TaskAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_assignment_id;
    @ManyToOne
    @JoinColumn(name = "family_member_id", nullable = false)
    private FamilyMember familyMember;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    @ManyToOne
    @JoinColumn(name = "task_status_id", nullable = false)
    private TaskStatus taskStatus;
    @Column(nullable = false)
    private LocalDateTime assignment_datetime;
    private LocalDateTime completion_datetime;

    public LocalDateTime getAssignment_datetime() {
        return assignment_datetime;
    }

    public void setAssignment_datetime(LocalDateTime assignment_datetime) {
        this.assignment_datetime = assignment_datetime;
    }

    public LocalDateTime getCompletion_datetime() {
        return completion_datetime;
    }

    public void setCompletion_datetime(LocalDateTime completion_datetime) {
        this.completion_datetime = completion_datetime;
    }

    public Integer getTask_assignment_id() {
        return task_assignment_id;
    }

    public void setTask_assignment_id(Integer task_assignment_id) {
        this.task_assignment_id = task_assignment_id;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
