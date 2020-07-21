package pie.bert.tracker.domain.category;

import pie.bert.tracker.domain.DomainException;

public final class CategoryCodeAlreadyExistsException extends DomainException {

    public CategoryCodeAlreadyExistsException(String message) {
        super(message, CATEGORY_ALREADY_EXISTS);
    }
}
