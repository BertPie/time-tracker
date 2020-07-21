package pie.bert.tracker.domain;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class DomainValidator {

    public DomainValidator() {
        // empty
    }

    public void fieldCannotBeNull(Object field, Supplier<DomainException> exceptionThrower) {
        if (field == null) {
            throw exceptionThrower.get();
        }
    }

    public void stringCannotBeLongerThan(String string, int maxLength, Supplier<DomainException> exceptionThrower) {
        if (string.length() > maxLength) {
            throw exceptionThrower.get();
        }
    }

    public void stringCannotBeShorterThan(String string, int minLength, Supplier<DomainException> exceptionThrower) {
        if (string.length() < minLength) {
            throw exceptionThrower.get();
        }
    }
}
