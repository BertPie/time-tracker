package pie.bert.tracker.domain.category;

import pie.bert.tracker.domain.DomainException;

public class CategoryNotFoundException extends DomainException {

    public CategoryNotFoundException(String message) {
        super(message, CATEGORY_NOT_FOUND);
    }
}
