package pie.bert.tracker.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class DomainValidatorTest {

    private static final DomainException TEST_EXCEPTION = new TestException("messageTest", "codeTest");

    private DomainValidator testObj;

    @Before
    public void setUp() throws Exception {
        this.testObj = new DomainValidator();
    }

    @Test
    public void fieldCannotBeNull_shouldNotThrowException_whenFieldIsNotNull() {
        // given
        Object providedObject = new Object();

        // when
        Throwable result = catchThrowable(
                () -> testObj.fieldCannotBeNull(providedObject, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void fieldCannotBeNull_shouldThrowException_whenFieldIsNull() {
        // given
        Object providedObject = null;

        // when
        Throwable result = catchThrowable(
                () -> testObj.fieldCannotBeNull(providedObject, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringCannotBeLongerThan_shouldNotThrowException_whenStringShorterThanMaxLength() {
        // given
        int maxLength = 60;
        int stringLength = 11;
        String providedString = "x".repeat(stringLength);

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringCannotBeLongerThan(providedString, maxLength, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringCannotBeLongerThan_shouldNotThrowException_whenStringIsEqualToMaxLength() {
        // given
        int maxLength = 60;
        int stringLength = 60;
        String providedString = "x".repeat(stringLength);

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringCannotBeLongerThan(providedString, maxLength, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringCannotBeLongerThan_shouldThrowException_whenStringIsEqualToMaxLength() {
        // given
        int maxLength = 60;
        int stringLength = 61;
        String providedString = "x".repeat(stringLength);

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringCannotBeLongerThan(providedString, maxLength, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringCannotBeShorterThan_shouldNotThrowException_whenStringShorterThanMaxLength() {
        // given
        int minLength = 11;
        int stringLength = 60;
        String providedString = "x".repeat(stringLength);

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringCannotBeShorterThan(providedString, minLength, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringCannotBeShorterThan_shouldNotThrowException_whenStringIsEqualToMaxLength() {
        // given
        int minLength = 60;
        int stringLength = 60;
        String providedString = "x".repeat(stringLength);

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringCannotBeShorterThan(providedString, minLength, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringCannotBeShorterThan_shouldThrowException_whenStringIsEqualToMaxLength() {
        // given
        int minLength = 61;
        int stringLength = 60;
        String providedString = "x".repeat(stringLength);

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringCannotBeShorterThan(providedString, minLength, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphabetic_shouldNotThrowException_whenStringHasOnlySmallLetters() {
        // given
        String providedString = "abszz";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphabetic(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringHasToBeAlphabetic_shouldNotThrowException_whenStringHasOnlyCapitalLetters() {
        // given
        String providedString = "ABSZZ";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphabetic(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringHasToBeAlphabetic_shouldNotThrowException_whenStringHasBothSmallAndBigLetter() {
        // given
        String providedString = "ABSabsZz";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphabetic(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringHasToBeAlphabetic_shouldThrowException_whenStringContainsNumber() {
        // given
        String providedString = "abc1cba";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphabetic(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphabetic_shouldThrowException_whenStringContainsFullStop() {
        // given
        String providedString = "abc.cba";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphabetic(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphabetic_shouldThrowException_whenStringContainsSpace() {
        // given
        String providedString = "abc cba";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphabetic(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphabetic_shouldThrowException_whenStringStartsWithSpace() {
        // given
        String providedString = " abccba";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphabetic(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphabetic_shouldThrowException_whenStringEndsWithSpace() {
        // given
        String providedString = "abccba ";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphabetic(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphabetic_shouldThrowException_whenStringContainsPolishSign() {
        // given
        String providedString = "abcścba";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphabetic(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphaNumericWithSpacesBetween_shouldNotThrowException_whenStringContainsOnlyAlphaNumericChars() {
        // given
        String providedString = "abcABC123";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphaNumericWithSpacesBetween(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringHasToBeAlphaNumericWithSpacesBetween_shouldNotThrowException_whenStringContainsOnlyAlphaNumericCharsWithSpaceInBetween() {
        // given
        String providedString = "abcAB C123";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphaNumericWithSpacesBetween(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringHasToBeAlphaNumericWithSpacesBetween_shouldThrowException_whenStringStartsWithSpace() {
        // given
        String providedString = " abcABC123";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphaNumericWithSpacesBetween(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphaNumericWithSpacesBetween_shouldThrowException_whenStringEndsWithSpace() {
        // given
        String providedString = "abcABC123 ";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphaNumericWithSpacesBetween(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphaNumericWithSpacesBetween_shouldThrowException_whenStringContainsFullStop() {
        // given
        String providedString = "abcAB.C123";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphaNumericWithSpacesBetween(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeAlphaNumericWithSpacesBetween_shouldThrowException_whenStringContainsPolishSign() {
        // given
        String providedString = "abcABśC123";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeAlphaNumericWithSpacesBetween(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeLikeSentence_shouldNotThrowException_whenStringContainsAllowedChars() {
        // given
        String providedString = "abcABC ,.!?;'\"C123.";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeLikeSentence(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void stringHasToBeLikeSentence_shouldThrowException_whenStringStartWithPunctuation() {
        // given
        String providedString = "\"abcABC ,.!?;'\"C123.";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeLikeSentence(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeLikeSentence_shouldThrowException_whenStringStartWithSpace() {
        // given
        String providedString = " abcABC ,.!?;'\"C123.";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeLikeSentence(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeLikeSentence_shouldThrowException_whenStringEndsWithSpace() {
        // given
        String providedString = "abcABC ,.!?;'\"C123. ";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeLikeSentence(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    @Test
    public void stringHasToBeLikeSentence_shouldThrowException_whenStringContainsPolishSign() {
        // given
        String providedString = "abcABCś,.!?;'\"C123.";

        // when
        Throwable result = catchThrowable(
                () -> testObj.stringHasToBeLikeSentence(providedString, () -> TEST_EXCEPTION)
        );

        // then
        assertThat(result).isEqualTo(TEST_EXCEPTION);
    }

    private static class TestException extends DomainException {
        public TestException(String message, String code) {
            super(message, code);
        }
    }
}
