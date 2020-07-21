package pie.bert.tracker.domain.category;

import org.springframework.stereotype.Component;

@Component
class CategoryNormalizer {

    CategoryNormalizer() {
        // empty
    }

    /**
     * Normalizes category fields to what is "normal" representation. The only fields requiring normalization is
     * {@code code} that is required to consists only of capital letters.
     * <p/>
     * Normalization process is also known as canonicalization or standardization. See the link below.
     *
     * @param category to be normalized
     * @return new instance of category with normalized fields
     * @see <a href="https://en.wikipedia.org/wiki/Canonicalization">Canonicalization on Wikipedia</a>
     */
    Category normalize(Category category) {
        return new Category(
                normalizeCode(category.getCode()),
                category.getName(),
                category.getDescription()
        );
    }

    private String normalizeCode(String code) {
        return code.toUpperCase();
    }
}
