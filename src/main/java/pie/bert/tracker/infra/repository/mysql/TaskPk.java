package pie.bert.tracker.infra.repository.mysql;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TaskPk implements Serializable {

    private static final long serialVersionUID = 45649211354L;

    @ManyToOne
    private CategoryEntity categoryEntity;

    @Column
    private Integer taskId;

    public TaskPk() {
    }

    public TaskPk(CategoryEntity categoryEntity, Integer taskId) {
        this.categoryEntity = categoryEntity;
        this.taskId = taskId;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "TaskPk{" +
                "categoryEntity=" + categoryEntity +
                ", taskId=" + taskId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskPk)) return false;
        TaskPk taskPk = (TaskPk) o;
        return Objects.equals(getCategoryEntity(), taskPk.getCategoryEntity()) &&
                Objects.equals(getTaskId(), taskPk.getTaskId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryEntity(), getTaskId());
    }
}
