package pie.bert.tracker.domain.category;

import org.springframework.stereotype.Component;

@Component
class CategoryNormalizer {

    CategoryNormalizer() {
        // empty
    }

    Category normalize(Category category) {
        return new Category(
                normalizeCode(category.getCode()),
                category.getName(),
                category.getDescription()
        );
    }

    String normalizeCode(String code) {
        return code.toUpperCase();
    }
}
