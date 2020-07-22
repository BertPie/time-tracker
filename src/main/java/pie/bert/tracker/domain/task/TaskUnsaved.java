package pie.bert.tracker.domain.task;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskUnsaved {
    private final String category;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String description;

    public TaskUnsaved(String category, LocalDateTime start, LocalDateTime end, String description) {
        this.category = category;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskUnsaved)) return false;
        TaskUnsaved that = (TaskUnsaved) o;
        return Objects.equals(category, that.category) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, start, end, description);
    }
}
