package pie.bert.tracker.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Task create(TaskUnsaved taskUnsaved) {
        taskValidator.validate(taskUnsaved);
        return repositoryService.create(taskUnsaved);
    }

    public Collection<Task> findAll() {
        return repositoryService.findAll();
    }

    public Task findByTaskIdentity(TaskIdentity taskIdentity) throws TaskNotFoundException {
        return repositoryService.findByTaskIdentity(taskIdentity)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Could not find task for " + taskIdentity.toHumanReadable()));
    }
}
