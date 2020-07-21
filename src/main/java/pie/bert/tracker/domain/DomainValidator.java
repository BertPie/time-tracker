package pie.bert.tracker.domain;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;
import java.util.regex.Pattern;

@Component
public class DomainValidator {

    private static final String ALPHABETIC = "\\p{Alpha}";
    private static final String ALPHA_NUMERIC = "\\p{Alnum}";
    private static final String ALPHA_NUMERIC_WITH_SPACE = "[\\p{Alnum} ]";
    private static final String ALPHA_NUMERIC_WITH_PUNCTUATION = "[\\p{Alnum},.!?;'\"]";
    private static final String ALPHA_NUMERIC_WITH_SPACE_AND_PUNCTUATION = "[\\p{Alnum} ,.!?;'\"]";

    private static final Pattern ALPHABETIC_PATTERN
            = Pattern.compile("^" + ALPHABETIC + "*" + "$");
    private static final Pattern ALPHA_NUMERIC_WITH_SPACES_IN_BETWEEN_PATTERN
            = Pattern.compile(""
            + "^" + ALPHA_NUMERIC
            + ALPHA_NUMERIC_WITH_SPACE + "*"
            + ALPHA_NUMERIC + "$");
    private static final Pattern ALPHA_NUMERIC_WITH_SPACES_AND_PUNCTUATION_PATTERN
            = Pattern.compile(""
            + "^" + ALPHA_NUMERIC
            + ALPHA_NUMERIC_WITH_SPACE_AND_PUNCTUATION + "*"
            + ALPHA_NUMERIC_WITH_PUNCTUATION + "$");

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

    public void stringHasToBeAlphabetic(String string, Supplier<DomainException> exceptionThrower) {
        if (!ALPHABETIC_PATTERN.matcher(string).matches()) {
            throw exceptionThrower.get();
        }
    }

    public void stringHasToBeAlphaNumericWithSpacesBetween(String string, Supplier<DomainException> exceptionThrower) {
        if (!ALPHA_NUMERIC_WITH_SPACES_IN_BETWEEN_PATTERN.matcher(string).matches()) {
            throw exceptionThrower.get();
        }
    }

    public void stringHasToBeLikeSentence(String string, Supplier<DomainException> exceptionThrower) {
        if (!ALPHA_NUMERIC_WITH_SPACES_AND_PUNCTUATION_PATTERN.matcher(string).matches()) {
            throw exceptionThrower.get();
        }
    }
}
