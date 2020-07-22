package pie.bert.tracker.domain.task;

import pie.bert.tracker.domain.DomainException;

public class CategoryNotFoundException extends DomainException {

    public CategoryNotFoundException(String message) {
        super(message, DomainException.CATEGORY_NOT_FOUND);
    }
}
