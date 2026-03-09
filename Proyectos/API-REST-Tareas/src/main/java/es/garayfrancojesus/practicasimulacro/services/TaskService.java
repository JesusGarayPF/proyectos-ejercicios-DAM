package es.garayfrancojesus.practicasimulacro.services;

import es.garayfrancojesus.practicasimulacro.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
    List<Task> findTasks(String search, int pageNumber, int pageSize);
}
