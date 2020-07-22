package pie.bert.tracker.domain.task;

public interface TaskRepositoryService {

    Task create(TaskUnsaved taskUnsaved) throws CategoryNotFoundException;
}
