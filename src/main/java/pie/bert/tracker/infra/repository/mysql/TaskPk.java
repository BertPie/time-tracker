package pie.bert.tracker.infra.repository.mysql;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

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
}
