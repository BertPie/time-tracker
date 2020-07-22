package pie.bert.tracker.domain.task;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepositoryService {

    Task create(TaskUnsaved taskUnsaved) throws CategoryNotFoundException;

    Collection<Task> findAll();

    Optional<Task> findByTaskIdentity(TaskIdentity taskIdentity);
}
