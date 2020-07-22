package pie.bert.tracker.domain.task;

import pie.bert.tracker.domain.DomainException;

public class TaskValidationException extends DomainException {
    public TaskValidationException(String message) {
        super(message, DomainException.TASK_NOT_VALID);
    }
}
