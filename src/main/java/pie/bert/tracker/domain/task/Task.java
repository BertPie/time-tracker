package pie.bert.tracker.domain.task;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private final TaskIdentity taskIdentity;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String description;

    public Task(String category, int taskId, LocalDateTime start, LocalDateTime end, String description) {
        this.taskIdentity = new TaskIdentity(category, taskId);
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public TaskIdentity getTaskIdentity() {
        return taskIdentity;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskIdentity=" + taskIdentity +
                ", start=" + start +
                ", end=" + end +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getTaskIdentity().equals(task.getTaskIdentity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskIdentity());
    }
}
