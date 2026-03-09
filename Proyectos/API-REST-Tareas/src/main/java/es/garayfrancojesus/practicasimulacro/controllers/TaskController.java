package es.garayfrancojesus.practicasimulacro.controllers;

import es.garayfrancojesus.practicasimulacro.entities.Task;
import es.garayfrancojesus.practicasimulacro.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/find/{search}/{pageNumber}/{pageSize}")
    public List<Task> findTasks(
            @PathVariable String search,
            @PathVariable int pageNumber,
            @PathVariable int pageSize
    ) {
        return taskService.findTasks(search, pageNumber, pageSize);
    }
}
