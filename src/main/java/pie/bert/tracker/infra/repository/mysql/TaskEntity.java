package pie.bert.tracker.infra.repository.mysql;

import pie.bert.tracker.domain.task.Task;
import pie.bert.tracker.domain.task.TaskUnsaved;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @EmbeddedId
    private TaskPk taskPk;

    @Column
    private LocalDateTime start;
    @Column
    private LocalDateTime end;
    @Column
    private String description;

    public TaskEntity() {
        // empty
    }

    public TaskEntity(CategoryEntity categoryEntity, int nextId, TaskUnsaved taskUnsaved) {
        this.taskPk = new TaskPk(categoryEntity, nextId);
        this.start = taskUnsaved.getStart();
        this.end = taskUnsaved.getEnd();
        this.description = taskUnsaved.getDescription();
    }

    public Task toTask() {
        return new Task(
                taskPk.getCategoryEntity().getCode(),
                taskPk.getTaskId(),
                start,
                end,
                description
        );
    }

    public TaskPk getTaskPk() {
        return taskPk;
    }

    public void setTaskPk(TaskPk taskPk) {
        this.taskPk = taskPk;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
