package pie.bert.tracker.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pie.bert.tracker.domain.DomainValidator;
import pie.bert.tracker.domain.category.CategoryValidationException;
import pie.bert.tracker.domain.category.CategoryValidator;

import java.time.LocalDateTime;

@Component
class TaskValidator {

    private final CategoryValidator categoryValidator;
    private final DomainValidator domainValidator;
    private final TaskValues taskValues;

    @Autowired
    TaskValidator(CategoryValidator categoryValidator, DomainValidator domainValidator, TaskValues taskValues) {
        this.categoryValidator = categoryValidator;
        this.domainValidator = domainValidator;
        this.taskValues = taskValues;
    }

    void validate(TaskUnsaved taskUnsaved) throws TaskValidationException, CategoryValidationException {
        validateCategory(taskUnsaved.getCategory());
        validateStartBeforeEnd(taskUnsaved.getStart(), taskUnsaved.getEnd());
        validateDescription(taskUnsaved.getDescription());
    }

    private void validateCategory(String category) throws CategoryValidationException {
        categoryValidator.validateCode(category);
    }

    private void validateStartBeforeEnd(LocalDateTime start, LocalDateTime end) throws TaskValidationException {
        if (start.isAfter(end)) {
            throw new TaskValidationException("Task cannot start after it ends.");
        }
    }

    private void validateDescription(String description) throws TaskValidationException {
        domainValidator.fieldCannotBeNull(description, () ->
                new TaskValidationException("Task description cannot be null"));

        int maxDescriptionLength = taskValues.getMaxDescriptionLength();
        domainValidator.stringCannotBeLongerThan(description, maxDescriptionLength, () ->
                new TaskValidationException("Task description cannot be longer than " + maxDescriptionLength));

        int minDescriptionLength = taskValues.getMinDescriptionLength();
        domainValidator.stringCannotBeShorterThan(description, minDescriptionLength, () ->
                new TaskValidationException("Task description cannot be shorter than " + minDescriptionLength));

        domainValidator.stringHasToBeLikeSentence(description, () ->
                new TaskValidationException("Task description has to be like sentence"));
    }
}
