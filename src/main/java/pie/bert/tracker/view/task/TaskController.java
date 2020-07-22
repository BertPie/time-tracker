package pie.bert.tracker.view.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pie.bert.tracker.domain.task.CategoryNotFoundException;
import pie.bert.tracker.domain.task.Task;
import pie.bert.tracker.domain.task.TaskDomainService;
import pie.bert.tracker.domain.task.TaskUnsaved;
import pie.bert.tracker.view.ErrorResponse;
import pie.bert.tracker.view.Mapping;
import pie.bert.tracker.view.ViewError;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = Mapping.TASKS)
public class TaskController {

    private final TaskDomainService taskDomainService;
    private final TaskViewMapper taskViewMapper;

    @Autowired
    public TaskController(TaskDomainService taskDomainService,
                          TaskViewMapper taskViewMapper) {
        this.taskDomainService = taskDomainService;
        this.taskViewMapper = taskViewMapper;
    }

    @GetMapping
    public List<TaskView> viewAllTask() {
        return taskDomainService
                .findAll()
                .stream()
                .map(taskViewMapper::toView)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskView createTask(@RequestBody TaskToCreate taskToCreate) {
        TaskUnsaved taskUnsaved = taskViewMapper.toTaskUnsaved(taskToCreate);
        Task created = taskDomainService.create(taskUnsaved);
        return taskViewMapper.toView(created);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public ErrorResponse wrongDataFormat(DateTimeParseException e) {
        return new ErrorResponse(
                ViewError.WRONG_DATE_TIME_FORMAT,
                "Wrong format of following date time " + e.getParsedString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse categoryNotFoundException(CategoryNotFoundException e) {
        return new ErrorResponse(e);
    }
}
