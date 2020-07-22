package pie.bert.tracker.view.task;

import pie.bert.tracker.view.ViewError;
import pie.bert.tracker.view.ViewException;

public class TaskIdentityNotValidException extends ViewException {

    public TaskIdentityNotValidException(String message) {
        super(message, ViewError.INVALID_TASK_VIEW_ID);
    }
}
