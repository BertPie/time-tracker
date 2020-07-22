package pie.bert.tracker.domain.task;

import java.util.Collection;

public interface TaskRepositoryService {

    Task create(TaskUnsaved taskUnsaved) throws CategoryNotFoundException;

    Collection<Task> findAll();
}
