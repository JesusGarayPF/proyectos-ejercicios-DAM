package es.garayfrancojesus.practicasimulacro.services;

import es.garayfrancojesus.practicasimulacro.entities.Task;
import es.garayfrancojesus.practicasimulacro.repositories.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findTasks(String search, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByTitle(search, search, pageable);
    }
}
