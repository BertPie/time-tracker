package pie.bert.tracker.view.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pie.bert.tracker.domain.task.Task;
import pie.bert.tracker.domain.task.TaskIdentity;
import pie.bert.tracker.domain.task.TaskUnsaved;
import pie.bert.tracker.view.Mapping;
import pie.bert.tracker.view.PathVar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@Component
public class TaskViewMapper {

    private final TaskValues taskValues;
    private final DateTimeFormatter formatter;
    private final Pattern taskViewIdPattern;

    @Autowired
    public TaskViewMapper(TaskValues taskValues) {
        this.taskValues = taskValues;
        this.formatter = DateTimeFormatter.ofPattern(taskValues.getDataTimeFormat());
        this.taskViewIdPattern = compileTaskViewIdPattern(taskValues.getDigitsInTaskViewId());
    }

    private Pattern compileTaskViewIdPattern(int digitsInTaskViewId) {
        String regex = "^[A-Z]+-[0-9]{6}$".replace("6", String.valueOf(digitsInTaskViewId));
        return Pattern.compile(regex);
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
        String id = parseTaskViewId(task.getTaskIdentity().getCategory(), task.getTaskIdentity().getTaskId());
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

    public TaskIdentity toTaskIdentity(String taskViewId) throws TaskIdentityNotValidException {
        if (!taskViewIdPattern.matcher(taskViewId).matches()) {
            throw new TaskIdentityNotValidException("Task View Id does not match required pattern: " + taskViewId);
        }
        String[] validatedSplit = taskViewId.split("-");
        String category = validatedSplit[0];
        int taskId = Integer.parseInt(validatedSplit[1]);
        return new TaskIdentity(category, taskId);
    }
}
