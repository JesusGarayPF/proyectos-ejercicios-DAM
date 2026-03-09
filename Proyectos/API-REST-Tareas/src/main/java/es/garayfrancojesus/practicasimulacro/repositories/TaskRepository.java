package es.garayfrancojesus.practicasimulacro.repositories;

import es.garayfrancojesus.practicasimulacro.entities.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByTitle(String title, String description, Pageable pageable);
}
