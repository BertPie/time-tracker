package pie.bert.tracker.domain.task;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private final String category;
    private final int taskId;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String description;

    public Task(String category, int taskId, LocalDateTime start, LocalDateTime end, String description) {
        this.category = category;
        this.taskId = taskId;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public int getTaskId() {
        return taskId;
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
                "category='" + category + '\'' +
                ", taskId=" + taskId +
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
        return taskId == task.taskId &&
                Objects.equals(category, task.category) &&
                Objects.equals(start, task.start) &&
                Objects.equals(end, task.end) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, taskId, start, end, description);
    }
}
