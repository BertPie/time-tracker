package pie.bert.tracker.domain.category;

import org.junit.Before;
import org.junit.Test;
import pie.bert.tracker.domain.DomainException;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.doReturn;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CategoryDomainServiceTest {

    private CategoryRepositoryService categoryRepositoryServiceMock;
    private CategoryValidator categoryValidatorMock;
    private CategoryNormalizer categoryNormalizerMock;

    private CategoryDomainService testObj;

    @Before
    public void setUp() throws Exception {
        this.categoryRepositoryServiceMock = mock(CategoryRepositoryService.class);
        this.categoryValidatorMock = mock(CategoryValidator.class);
        this.categoryNormalizerMock = mock(CategoryNormalizer.class);

        this.testObj = new CategoryDomainService(categoryRepositoryServiceMock, categoryValidatorMock, categoryNormalizerMock);
    }

    @Test
    public void create_shouldReturnCorrectCategory_happyPath() {
        // given
        Category categoryProvided = new Category("codeProvided", "nameProvided", "descProvided");

        Category categoryNormalized = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryNormalized)
                .when(categoryNormalizerMock)
                .normalize(any());
        Category categoryCreated = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryCreated)
                .when(categoryRepositoryServiceMock)
                .create(any());

        Category categoryExpected = categoryCreated;

        // when
        Category result = testObj.create(categoryProvided);

        // then
        assertThat(result).isEqualTo(categoryExpected);
    }

    @Test
    public void create_shouldUseCorrectMethodsAndParams_categoryValidator() {
        // given
        Category categoryProvided = new Category("codeProvided", "nameProvided", "descProvided");

        Category categoryNormalized = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryNormalized)
                .when(categoryNormalizerMock)
                .normalize(any());
        Category categoryCreated = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryCreated)
                .when(categoryRepositoryServiceMock)
                .create(any());

        // when
        testObj.create(categoryProvided);

        // then
        then(categoryValidatorMock)
                .should(times(1))
                .validate(categoryProvided);
        verifyNoMoreInteractions(categoryValidatorMock);
    }

    @Test
    public void create_shouldUseCorrectMethodsAndParams_categoryNormalizer() {
        // given
        Category categoryProvided = new Category("codeProvided", "nameProvided", "descProvided");

        Category categoryNormalized = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryNormalized)
                .when(categoryNormalizerMock)
                .normalize(any());
        Category categoryCreated = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryCreated)
                .when(categoryRepositoryServiceMock)
                .create(any());

        // when
        testObj.create(categoryProvided);

        // then
        then(categoryNormalizerMock)
                .should(times(1))
                .normalize(categoryProvided);
        verifyNoMoreInteractions(categoryNormalizerMock);
    }

    @Test
    public void create_shouldUseCorrectMethodsAndParams_categoryRepositoryService() {
        // given
        Category categoryProvided = new Category("codeProvided", "nameProvided", "descProvided");

        Category categoryNormalized = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryNormalized)
                .when(categoryNormalizerMock)
                .normalize(any());
        Category categoryCreated = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryCreated)
                .when(categoryRepositoryServiceMock)
                .create(any());

        // when
        testObj.create(categoryProvided);

        // then
        then(categoryRepositoryServiceMock)
                .should(times(1))
                .create(categoryNormalized);
        verifyNoMoreInteractions(categoryRepositoryServiceMock);
    }

    @Test
    public void create_shouldThrowException_whenValidatorThrows() {
        // given
        Category categoryProvided = new Category("codeProvided", "nameProvided", "descProvided");

        CategoryValidationException exceptionThrown = new CategoryValidationException("message");
        doThrow(exceptionThrown)
                .when(categoryValidatorMock)
                .validate(any());
        Category categoryNormalized = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryNormalized)
                .when(categoryNormalizerMock)
                .normalize(any());
        Category categoryCreated = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryCreated)
                .when(categoryRepositoryServiceMock)
                .create(any());

        CategoryValidationException exceptionExpected = exceptionThrown;

        // when
        Throwable result = catchThrowable(
                () -> testObj.create(categoryProvided));


        // then
        assertThat(result).isEqualTo(exceptionExpected);
    }

    @Test
    public void create_shouldThrowException_whenNormalizerThrows() {
        // given
        Category categoryProvided = new Category("codeProvided", "nameProvided", "descProvided");

        CategoryCodeAlreadyExistsException exceptionThrown = new CategoryCodeAlreadyExistsException("message");
        doThrow(exceptionThrown)
                .when(categoryNormalizerMock)
                .normalize(any());
        Category categoryCreated = new Category("codeNormalized", "nameNormalized", "descNormalized");
        doReturn(categoryCreated)
                .when(categoryRepositoryServiceMock)
                .create(any());

        CategoryCodeAlreadyExistsException exceptionExpected = exceptionThrown;

        // when
        Throwable result = catchThrowable(
                () -> testObj.create(categoryProvided));


        // then
        assertThat(result).isEqualTo(exceptionExpected);
    }

    @Test
    public void findAll_shouldReturnCorrectCollection_happyPath() {
        // given
        Collection<Category> collectionFound = mock(Collection.class);
        doReturn(collectionFound)
                .when(categoryRepositoryServiceMock)
                .findAll();

        Collection<Category> collectionExpected = collectionFound;

        // when
        Collection<Category> result = testObj.findAll();

        // then
        assertThat(result).isEqualTo(collectionExpected);
    }

    @Test
    public void findAll_shouldUseCorrectMethodsAndParams_categoryRepositoryService() {
        // given
        Collection<Category> collectionFound = mock(Collection.class);
        doReturn(collectionFound)
                .when(categoryRepositoryServiceMock)
                .findAll();

        // when
        testObj.findAll();

        // then
        then(categoryRepositoryServiceMock)
                .should(times(1))
                .findAll();
        verifyNoMoreInteractions(categoryRepositoryServiceMock);
    }

    @Test
    public void findByCode_shouldReturnCorrectCategory_happyPath() {
        // given
        Category categoryFound = new Category("code", "name", "desc");
        doReturn(Optional.ofNullable(categoryFound))
                .when(categoryRepositoryServiceMock)
                .findByCode(anyString());
        Category expected = categoryFound;

        // when
        Category result = testObj.findByCode("code");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findByCode_shouldThrowException_whenCategoryNotFound() {
        // given
        Category categoryFound = null;
        doReturn(Optional.ofNullable(categoryFound))
                .when(categoryRepositoryServiceMock)
                .findByCode(anyString());
        var expected = CategoryNotFoundException.class;

        // when
        var result = catchThrowable(() -> testObj.findByCode("code"));

        // then
        assertThat(result).isInstanceOf(expected);
        assertThat(result.getMessage()).isEqualTo("Could not find category with code: code");
        assertThat(((DomainException) result).getCode()).isEqualTo("CAT_0002");
    }

    @Test
    public void findByCode_shouldUseCorrectMethodsAndParams_categoryRepositoryService() {
        // given
        String codeProvided = "code";
        Category categoryFound = new Category("code", "name", "desc");
        doReturn(Optional.ofNullable(categoryFound))
                .when(categoryRepositoryServiceMock)
                .findByCode(anyString());

        String codeExpected = codeProvided;

        // when
        testObj.findByCode(codeProvided);

        // then
        then(categoryRepositoryServiceMock)
                .should(times(1))
                .findByCode(codeExpected);
        verifyNoMoreInteractions(categoryRepositoryServiceMock);
    }
}
