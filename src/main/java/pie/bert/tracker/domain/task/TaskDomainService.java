package pie.bert.tracker.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaskDomainService {

    private final TaskRepositoryService repositoryService;

    @Autowired
    public TaskDomainService(TaskRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public Task create(TaskUnsaved taskUnsaved) {
        // todo: validate start > end
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
