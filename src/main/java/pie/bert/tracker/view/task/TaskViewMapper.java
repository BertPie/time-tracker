package pie.bert.tracker.view.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pie.bert.tracker.domain.task.Task;
import pie.bert.tracker.domain.task.TaskUnsaved;
import pie.bert.tracker.view.Mapping;
import pie.bert.tracker.view.PathVar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class TaskViewMapper {

    private final TaskValues taskValues;
    private final DateTimeFormatter formatter;

    @Autowired
    public TaskViewMapper(TaskValues taskValues) {
        this.taskValues = taskValues;
        this.formatter = DateTimeFormatter.ofPattern(taskValues.getDataTimeFormat());
    }

    public TaskUnsaved toTaskUnsaved(TaskToCreate taskToCreate) throws DateTimeParseException {
        LocalDateTime start = LocalDateTime.parse(taskToCreate.getStart(), formatter);
        LocalDateTime end = LocalDateTime.parse(taskToCreate.getEnd(), formatter);
        return new TaskUnsaved(
                taskToCreate.getCategory(),
                start,
                end,
                taskToCreate.getDescription()
        );
    }

    public TaskView toView(Task task) {
        String id = parseTaskViewId(task.getCategory(), task.getTaskId());
        String start = task.getStart().format(formatter);
        String end = task.getEnd().format(formatter);
        String url = Mapping.TASK.replace(PathVar.TASK_VIEW_ID_CODE_BRACKETS, id);

        return new TaskView(
                id,
                start,
                end,
                task.getDescription(),
                url
        );
    }

    private String parseTaskViewId(String category, int taskId) {
        String format = String.format("%%0%dd", taskValues.getDigitsInTaskViewId());
        return category + "-" + String.format(format, taskId);
    }
}
