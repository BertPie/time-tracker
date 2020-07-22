package pie.bert.tracker.domain.task;

import pie.bert.tracker.domain.DomainException;

public class TaskNotFoundException extends DomainException {

    public TaskNotFoundException(String message) {
        super(message, DomainException.TASK_NOT_FOUND);
    }
}
