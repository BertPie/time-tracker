package pie.bert.tracker.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pie.bert.tracker.domain.category.CategoryValidationException;

import java.util.Collection;

@Service
public class TaskDomainService {

    private final TaskRepositoryService repositoryService;
    private final TaskValidator taskValidator;

    @Autowired
    public TaskDomainService(TaskRepositoryService repositoryService, TaskValidator taskValidator) {
        this.repositoryService = repositoryService;
        this.taskValidator = taskValidator;
    }

    /**
     * Validates provided task, if valid saves it in repository.
     * If the category to be saved under is not present in repository - throws an exception.
     *
     * @param taskUnsaved to be saved
     * @return saved task
     * @throws TaskValidationException     if some fields of provided task are invalid
     * @throws CategoryValidationException if category of provided task is invalid
     * @throws CategoryNotFoundException   when category was not found
     */
    public Task create(TaskUnsaved taskUnsaved)
            throws TaskValidationException, CategoryValidationException, CategoryNotFoundException {
        taskValidator.validate(taskUnsaved);
        return repositoryService.create(taskUnsaved);
    }

    /**
     * Find all tasks.
     *
     * @return all task
     */
    public Collection<Task> findAll() {
        return repositoryService.findAll();
    }

    /**
     * Find task by it's identity - that is category code and id.
     *
     * @param taskIdentity as a key to find task
     * @return found task
     * @throws TaskNotFoundException     when task was not found
     * @throws CategoryNotFoundException when category of task was not found
     */
    public Task findByTaskIdentity(TaskIdentity taskIdentity) throws TaskNotFoundException, CategoryNotFoundException {
        return repositoryService.findByTaskIdentity(taskIdentity)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Could not find task for " + taskIdentity.toHumanReadable()));
    }
}
