package pie.bert.tracker.domain.category;

import pie.bert.tracker.domain.DomainException;

public class CategoryValidationException extends DomainException {

    public CategoryValidationException(String message) {
        super(message, CATEGORY_NOT_VALID);
    }
}
