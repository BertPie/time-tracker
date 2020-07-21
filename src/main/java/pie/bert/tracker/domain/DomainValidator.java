package pie.bert.tracker.domain;

import org.springframework.stereotype.Component;

import java.util.Objects;
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

    /**
     * Validates field and throws exception provided by supplier when field is null.
     *
     * @param field             to be validated
     * @param exceptionSupplier as source of thrown exception when validation fails
     * @throws DomainException when condition is not satisfied
     */
    public void fieldCannotBeNull(Object field, Supplier<DomainException> exceptionSupplier) {
        if (field == null) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Validates string and throws exception provided by supplier when field is longer than specified.
     *
     * @param string            non null string to be validated
     * @param maxLength         of string
     * @param exceptionSupplier as source of thrown exception when validation fails
     * @throws DomainException when condition is not satisfied
     */
    public void stringCannotBeLongerThan(String string, int maxLength, Supplier<DomainException> exceptionSupplier) {
        Objects.requireNonNull(string);
        if (string.length() > maxLength) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Validates string and throws exception provided by supplier when field is shorter than specified.
     *
     * @param string            non null string to be validated
     * @param minLength         of string
     * @param exceptionSupplier as source of thrown exception when validation fails
     * @throws DomainException when condition is not satisfied
     */
    public void stringCannotBeShorterThan(String string, int minLength, Supplier<DomainException> exceptionSupplier) {
        Objects.requireNonNull(string);
        if (string.length() < minLength) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Validates string and throws exception provided by supplier when field consists not only of alphabetic chars.
     *
     * @param string            non null string to be validated
     * @param exceptionSupplier as source of thrown exception when validation fails
     * @throws DomainException when condition is not satisfied
     */
    public void stringHasToBeAlphabetic(String string, Supplier<DomainException> exceptionSupplier) {
        Objects.requireNonNull(string);
        if (!ALPHABETIC_PATTERN.matcher(string).matches()) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Validates string and throws exception provided by supplier when field doesn't meet any of the conditions:
     * <ul>
     *     <li>Starts with alphanumeric char</li>
     *     <li>In the middle contains only alphanumeric chars and spaces</li>
     *     <li>Ends with alphanumeric char</li>
     * </ul>
     *
     * @param string            non null string to be validated
     * @param exceptionSupplier as source of thrown exception when validation fails
     * @throws DomainException when condition is not satisfied
     */
    public void stringHasToBeAlphaNumericWithSpacesBetween(String string, Supplier<DomainException> exceptionSupplier) {
        Objects.requireNonNull(string);
        if (!ALPHA_NUMERIC_WITH_SPACES_IN_BETWEEN_PATTERN.matcher(string).matches()) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Validates string and throws exception provided by supplier when field doesn't meet any of the conditions:
     * <ul>
     *     <li>Starts with alphanumeric char</li>
     *     <li>In the middle contains only alphanumeric chars, spaces and punctuation</li>
     *     <li>Ends with alphanumeric char or punctuation</li>
     * </ul>
     * Allowed punctuation: {@code ,.!?;'"}
     *
     * @param string            non null string to be validated
     * @param exceptionSupplier as source of thrown exception when validation fails
     * @throws DomainException when condition is not satisfied
     */
    public void stringHasToBeLikeSentence(String string, Supplier<DomainException> exceptionSupplier) {
        Objects.requireNonNull(string);
        if (!ALPHA_NUMERIC_WITH_SPACES_AND_PUNCTUATION_PATTERN.matcher(string).matches()) {
            throw exceptionSupplier.get();
        }
    }
}
