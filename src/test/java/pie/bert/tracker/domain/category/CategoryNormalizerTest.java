package pie.bert.tracker.domain.category;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryNormalizerTest {

    private CategoryNormalizer testObj;

    @Before
    public void setUp() throws Exception {
        this.testObj = new CategoryNormalizer();
    }

    @Test
    public void normalize_shouldNotNormalizeAnyFields_whenProvidedCodeConsistsOfCapitalLetters() {
        // given
        String code = "CODE";
        String name = "name";
        String desc = "desc";
        String codeExpected = code;
        String nameExpected = name;
        String descExpected = desc;

        Category category = new Category(code, name, desc);

        // when
        Category result = testObj.normalize(category);

        // then
        assertThat(result.getCode()).isEqualTo(codeExpected);
        assertThat(result.getName()).isEqualTo(nameExpected);
        assertThat(result.getDescription()).isEqualTo(descExpected);
    }

    @Test
    public void normalize_shouldNormalizeOnlyCodeField_whenProvidedCodeConsistsOfSmallLetters() {
        // given
        String code = "code";
        String name = "name";
        String desc = "desc";
        String codeExpected = "CODE";
        String nameExpected = name;
        String descExpected = desc;

        Category category = new Category(code, name, desc);

        // when
        Category result = testObj.normalize(category);

        // then
        assertThat(result.getCode()).isEqualTo(codeExpected);
        assertThat(result.getName()).isEqualTo(nameExpected);
        assertThat(result.getDescription()).isEqualTo(descExpected);
    }
}
