package pie.bert.tracker.infra.repository.mysql;

import org.springframework.data.repository.CrudRepository;

public interface TaskMySqlRepository extends CrudRepository<TaskEntity, TaskPk> {
}
