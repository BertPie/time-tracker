package pie.bert.tracker.domain.task;

import java.util.Objects;

public class TaskIdentity {
    private final String category;
    private final int taskId;

    public TaskIdentity(String category, int taskId) {
        this.category = category;
        this.taskId = taskId;
    }

    public String getCategory() {
        return category;
    }

    public int getTaskId() {
        return taskId;
    }

    public String toHumanReadable() {
        return "category: " + category + " and id: " + taskId;
    }

    @Override
    public String toString() {
        return "TaskIdentity{" +
                "category='" + category + '\'' +
                ", taskId=" + taskId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskIdentity)) return false;
        TaskIdentity that = (TaskIdentity) o;
        return getTaskId() == that.getTaskId() &&
                Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getTaskId());
    }
}
