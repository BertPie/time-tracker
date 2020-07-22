package pie.bert.tracker.infra.repository.mysql;

import org.springframework.stereotype.Service;
import pie.bert.tracker.domain.task.CategoryNotFoundException;
import pie.bert.tracker.domain.task.Task;
import pie.bert.tracker.domain.task.TaskRepositoryService;
import pie.bert.tracker.domain.task.TaskUnsaved;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskMySqlRepositoryService implements TaskRepositoryService {

    // todo: use database to store counter
    private final AtomicInteger naiveNextId;

    private final CategoryMySqlRepository categoryMySqlRepository;
    private final TaskMySqlRepository taskMySqlRepository;

    public TaskMySqlRepositoryService(CategoryMySqlRepository categoryMySqlRepository,
                                      TaskMySqlRepository taskMySqlRepository) {
        this.categoryMySqlRepository = categoryMySqlRepository;
        this.taskMySqlRepository = taskMySqlRepository;
        this.naiveNextId = new AtomicInteger(0);
    }

    @Override
    public Task create(TaskUnsaved taskUnsaved) {
        String category = taskUnsaved.getCategory();
        boolean categoryDoesNotExist = !categoryMySqlRepository.existsById(category);
        if (categoryDoesNotExist) {
            throw new CategoryNotFoundException("Could not find category with code " + category + " for new task");
        } else {
            int nextId = naiveNextId.getAndIncrement();
            Optional<CategoryEntity> opt = categoryMySqlRepository.findById(category);
            TaskEntity taskEntity = new TaskEntity(opt.get(), nextId, taskUnsaved);
            return taskMySqlRepository.save(taskEntity).toTask();
        }
    }

    @Override
    public Collection<Task> findAll() {
        return StreamSupport.stream(taskMySqlRepository.findAll().spliterator(), false)
                .map(TaskEntity::toTask)
                .collect(Collectors.toList());
    }
}
