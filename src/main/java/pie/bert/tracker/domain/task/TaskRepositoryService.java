package pie.bert.tracker.domain.task;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepositoryService {

    /**
     * Creates a new category and stores it in a repository. If the category to be saved under is not present
     * in repository - throws an exception.
     *
     * @param taskUnsaved to be saved
     * @return newly saved task
     * @throws CategoryNotFoundException if the category of task is not found
     */
    Task create(TaskUnsaved taskUnsaved) throws CategoryNotFoundException;

    /**
     * Finds all task and returns a {@link Collection}. If no tasks are found, returns empty collection.
     *
     * @return collection of tasks
     */
    Collection<Task> findAll();

    /**
     * Finds task by its identity consisting of category code and task id.
     *
     * @param taskIdentity unique for the task
     * @return either optional with found task or empty optional
     * @throws CategoryNotFoundException if the category of task is not found
     */
    Optional<Task> findByTaskIdentity(TaskIdentity taskIdentity) throws CategoryNotFoundException;
}
