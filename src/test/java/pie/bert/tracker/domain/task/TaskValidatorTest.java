package pie.bert.tracker.domain.task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.BDDMockito;
import pie.bert.tracker.domain.DomainException;
import pie.bert.tracker.domain.DomainValidator;
import pie.bert.tracker.domain.category.CategoryValidator;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class TaskValidatorTest {

    private static final String CATEGORY = "category";
    private static final String DESC = "desc";

    private static final int MIN_DESC_LENGTH = 5;
    private static final int MAX_DESC_LENGTH = 6;

    private static final ArgumentMatcher<Supplier<DomainException>> THROWS_EXCEPTION_WITH_CODE_TSK_0002
            = exceptionSupplier -> exceptionSupplier.get().getCode().equals("TSK_0002");


    private CategoryValidator categoryValidatorMock;
    private DomainValidator domainValidatorMock;
    private TaskValues taskValuesMock;

    private TaskValidator testObj;

    @Before
    public void setUp() throws Exception {
        this.categoryValidatorMock = BDDMockito.mock(CategoryValidator.class);
        this.domainValidatorMock = BDDMockito.mock(DomainValidator.class);
        this.taskValuesMock = BDDMockito.mock(TaskValues.class);
        mockCategoryValues();

        this.testObj = new TaskValidator(categoryValidatorMock, domainValidatorMock, taskValuesMock);
    }

    private void mockCategoryValues() {
        given(taskValuesMock.getMinDescriptionLength())
                .willReturn(MIN_DESC_LENGTH);
        given(taskValuesMock.getMaxDescriptionLength())
                .willReturn(MAX_DESC_LENGTH);
    }

    @Test
    public void validate_shouldNotThrowException_whenStartIsNotAfterEnd() {
        // given
        LocalDateTime start = LocalDateTime.parse("2020-07-03T10:15:30");
        LocalDateTime end = start.plusHours(1);
        TaskUnsaved provided = new TaskUnsaved(CATEGORY, start, end, DESC);

        // when
        Throwable result = catchThrowable(() -> testObj.validate(provided));

        // then
        assertThat(result).isNull();
    }

    @Test
    public void validate_shouldThrowException_whenStartIsAfterEnd() {
        // given
        LocalDateTime start = LocalDateTime.parse("2020-07-03T10:15:30");
        LocalDateTime end = start.minusHours(1);
        TaskUnsaved provided = new TaskUnsaved(CATEGORY, start, end, DESC);

        // when
        Throwable result = catchThrowable(() -> testObj.validate(provided));

        // then
        assertThat(result).isInstanceOf(TaskValidationException.class);
        assertThat(((TaskValidationException) result).getCode()).isEqualTo("TSK_0002");
    }

    @Test
    public void validate_shouldDelegateCategoryValidation() {
        // given
        LocalDateTime start = LocalDateTime.parse("2020-07-03T10:15:30");
        LocalDateTime end = start.plusHours(1);
        TaskUnsaved provided = new TaskUnsaved(CATEGORY, start, end, DESC);

        // when
        testObj.validate(provided);

        // then
        then(categoryValidatorMock)
                .should(times(1))
                .validateCode(CATEGORY);
        verifyNoMoreInteractions(categoryValidatorMock);
    }

    @Test
    public void validate_shouldInvokeCorrectMethods_domainValidator() {
        // given
        LocalDateTime start = LocalDateTime.parse("2020-07-03T10:15:30");
        LocalDateTime end = start.plusHours(1);
        TaskUnsaved provided = new TaskUnsaved(CATEGORY, start, end, DESC);

        // when
        testObj.validate(provided);

        // then
        then(domainValidatorMock)
                .should(times(1))
                .fieldCannotBeNull(eq(DESC), argThat(THROWS_EXCEPTION_WITH_CODE_TSK_0002));
        then(domainValidatorMock)
                .should(times(1))
                .stringCannotBeLongerThan(eq(DESC), eq(MAX_DESC_LENGTH), argThat(THROWS_EXCEPTION_WITH_CODE_TSK_0002));
        then(domainValidatorMock)
                .should(times(1))
                .stringCannotBeShorterThan(eq(DESC), eq(MIN_DESC_LENGTH), argThat(THROWS_EXCEPTION_WITH_CODE_TSK_0002));
        then(domainValidatorMock)
                .should(times(1))
                .stringHasToBeLikeSentence(eq(DESC), argThat(THROWS_EXCEPTION_WITH_CODE_TSK_0002));
    }

    @Test
    public void validate_shouldInvokeCorrectMethods_taskValues() {
        // given
        LocalDateTime start = LocalDateTime.parse("2020-07-03T10:15:30");
        LocalDateTime end = start.plusHours(1);
        TaskUnsaved provided = new TaskUnsaved(CATEGORY, start, end, DESC);

        // when
        testObj.validate(provided);

        // then
        then(taskValuesMock)
                .should(times(1))
                .getMaxDescriptionLength();
        then(taskValuesMock)
                .should(times(1))
                .getMinDescriptionLength();
    }
}
