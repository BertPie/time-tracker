package pie.bert.tracker.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
