package pie.bert.tracker.domain.category;

import org.assertj.core.api.ThrowableAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import pie.bert.tracker.domain.DomainException;
import pie.bert.tracker.domain.DomainValidator;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

public class CategoryValidatorTest {

    private static final String CODE = "code";
    private static final String NAME = "name";
    private static final String DESC = "desc";

    private static final int MIN_CODE_LENGTH = 1;
    private static final int MAX_CODE_LENGTH = 2;
    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 4;
    private static final int MIN_DESC_LENGTH = 5;
    private static final int MAX_DESC_LENGTH = 6;
    private static final ArgumentMatcher<Supplier<DomainException>> THROWS_EXCEPTION_WITH_CODE_CAT_0003 = exceptionSupplier -> exceptionSupplier.get().getCode().equals("CAT_0003");

    private DomainValidator domainValidatorMock;
    private CategoryValues categoryValuesMock;

    private CategoryValidator testObj;

    @Before
    public void setUp() throws Exception {
        this.domainValidatorMock = mock(DomainValidator.class);
        this.categoryValuesMock = mock(CategoryValues.class);
        mockCategoryValues();

        this.testObj = new CategoryValidator(domainValidatorMock, categoryValuesMock);
    }

    private void mockCategoryValues() {
        given(categoryValuesMock.getMinCodeLength())
                .willReturn(MIN_CODE_LENGTH);
        given(categoryValuesMock.getMaxCodeLength())
                .willReturn(MAX_CODE_LENGTH);
        given(categoryValuesMock.getMinNameLength())
                .willReturn(MIN_NAME_LENGTH);
        given(categoryValuesMock.getMaxNameLength())
                .willReturn(MAX_NAME_LENGTH);
        given(categoryValuesMock.getMinDescriptionLength())
                .willReturn(MIN_DESC_LENGTH);
        given(categoryValuesMock.getMaxDescriptionLength())
                .willReturn(MAX_DESC_LENGTH);
    }

    @Test
    public void validate_shouldNotThrowAnyException_whenDomainValidatorDoesNot() {
        // given
        Category category = new Category(CODE, NAME, DESC);

        // when
        Throwable result = ThrowableAssert.catchThrowable(
                () -> testObj.validate(category)
        );

        // then
        assertThat(result).isNull();
    }

    @Test
    public void validate_shouldThrowException_whenDomainValidatorDoesThrowException() {
        // given
        Category category = new Category(CODE, NAME, DESC);

        doThrow(CategoryValidationException.class)
                .when(domainValidatorMock)
                .fieldCannotBeNull(eq(CODE), any());

        // when
        Throwable result = ThrowableAssert.catchThrowable(
                () -> testObj.validate(category)
        );

        // then
        assertThat(result).isInstanceOf(CategoryValidationException.class);
    }

    @Test
    public void validate_shouldInvokeFieldCannotBeNull_forCorrectFields() {
        // given
        Category category = new Category(CODE, NAME, DESC);

        // when
        testObj.validate(category);

        // then
        then(domainValidatorMock)
                .should(times(1))
                .fieldCannotBeNull(eq(CODE), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
        then(domainValidatorMock)
                .should(times(1))
                .fieldCannotBeNull(eq(NAME), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
        then(domainValidatorMock)
                .should(times(1))
                .fieldCannotBeNull(eq(DESC), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
    }

    @Test
    public void validate_shouldInvokeStringCannotBeLongerThan_forCorrectFields() {
        // given
        Category category = new Category(CODE, NAME, DESC);

        // when
        testObj.validate(category);

        // then
        then(domainValidatorMock)
                .should(times(1))
                .stringCannotBeLongerThan(eq(CODE), eq(MAX_CODE_LENGTH), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
        then(domainValidatorMock)
                .should(times(1))
                .stringCannotBeLongerThan(eq(NAME), eq(MAX_NAME_LENGTH), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
        then(domainValidatorMock)
                .should(times(1))
                .stringCannotBeLongerThan(eq(DESC), eq(MAX_DESC_LENGTH), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
        then(categoryValuesMock)
                .should(times(1))
                .getMaxCodeLength();
        then(categoryValuesMock)
                .should(times(1))
                .getMaxNameLength();
        then(categoryValuesMock)
                .should(times(1))
                .getMaxDescriptionLength();
    }

    @Test
    public void validate_shouldInvokeStringCannotBeShorterThan_forCorrectFields() {
        // given
        Category category = new Category(CODE, NAME, DESC);

        // when
        testObj.validate(category);

        // then
        then(domainValidatorMock)
                .should(times(1))
                .stringCannotBeShorterThan(eq(CODE), eq(MIN_CODE_LENGTH), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
        then(domainValidatorMock)
                .should(times(1))
                .stringCannotBeShorterThan(eq(NAME), eq(MIN_NAME_LENGTH), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
        then(domainValidatorMock)
                .should(times(1))
                .stringCannotBeShorterThan(eq(DESC), eq(MIN_DESC_LENGTH), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
        then(categoryValuesMock)
                .should(times(1))
                .getMinCodeLength();
        then(categoryValuesMock)
                .should(times(1))
                .getMinNameLength();
        then(categoryValuesMock)
                .should(times(1))
                .getMinDescriptionLength();
    }

    @Test
    public void validate_shouldInvokeStringHasToBeAlphabetic_forCorrectFields() {
        // given
        Category category = new Category(CODE, NAME, DESC);

        // when
        testObj.validate(category);

        // then
        then(domainValidatorMock)
                .should(times(1))
                .stringHasToBeAlphabetic(eq(CODE), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
    }

    @Test
    public void validate_shouldInvokeStringHasToBeAlphaNumericWithSpacesBetween_forCorrectFields() {
        // given
        Category category = new Category(CODE, NAME, DESC);

        // when
        testObj.validate(category);

        // then
        then(domainValidatorMock)
                .should(times(1))
                .stringHasToBeAlphaNumericWithSpacesBetween(eq(NAME), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
    }

    @Test
    public void validate_shouldInvokeStringHasToBeLikeSentence_forCorrectFields() {
        // given
        Category category = new Category(CODE, NAME, DESC);

        // when
        testObj.validate(category);

        // then
        then(domainValidatorMock)
                .should(times(1))
                .stringHasToBeLikeSentence(eq(DESC), argThat(THROWS_EXCEPTION_WITH_CODE_CAT_0003));
    }
}
