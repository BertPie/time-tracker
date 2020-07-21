package pie.bert.tracker.domain.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pie.bert.tracker.domain.DomainValidator;

@Component
class CategoryValidator {

    private final DomainValidator domainValidator;
    private final CategoryValues categoryValues;

    @Autowired
    CategoryValidator(DomainValidator domainValidator, CategoryValues categoryValues) {
        this.domainValidator = domainValidator;
        this.categoryValues = categoryValues;
    }

    void validate(Category category) throws CategoryValidationException {
        validateCode(category.getCode());
        validateName(category.getName());
        validateDescription(category.getDescription());
    }

    private void validateCode(String code) throws CategoryValidationException {
        domainValidator.fieldCannotBeNull(code,
                () -> new CategoryValidationException("Category Code cannot be null"));

        int maxCodeLength = categoryValues.getMaxCodeLength();
        domainValidator.stringCannotBeLongerThan(code, maxCodeLength,
                () -> new CategoryValidationException("Category Code cannot be longer than " + maxCodeLength));

        int minCodeLength = categoryValues.getMinCodeLength();
        domainValidator.stringCannotBeShorterThan(code, minCodeLength,
                () -> new CategoryValidationException("Category Code cannot be shorter than " + minCodeLength));
    }

    private void validateName(String name) throws CategoryValidationException {
        domainValidator.fieldCannotBeNull(name,
                () -> new CategoryValidationException("Category Name cannot be null"));

        int maxNameLength = categoryValues.getMaxNameLength();
        domainValidator.stringCannotBeLongerThan(name, maxNameLength,
                () -> new CategoryValidationException("Category Name cannot be longer than " + maxNameLength));

        int minNameLength = categoryValues.getMinNameLength();
        domainValidator.stringCannotBeShorterThan(name, minNameLength,
                () -> new CategoryValidationException("Category Name cannot be shorter than " + minNameLength));

    }

    private void validateDescription(String description) throws CategoryValidationException {
        domainValidator.fieldCannotBeNull(description,
                () -> new CategoryValidationException("Category description cannot be null"));

        int maxDescriptionLength = categoryValues.getMaxDescriptionLength();
        domainValidator.stringCannotBeLongerThan(description, maxDescriptionLength,
                () -> new CategoryValidationException("Category Description cannot be longer than " + maxDescriptionLength));

        int minDescriptionLength = categoryValues.getMinDescriptionLength();
        domainValidator.stringCannotBeShorterThan(description, minDescriptionLength,
                () -> new CategoryValidationException("Category Description cannot be shorter than " + minDescriptionLength));
    }
}
