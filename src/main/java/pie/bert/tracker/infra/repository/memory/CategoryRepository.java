package pie.bert.tracker.infra.repository.memory;

import org.springframework.stereotype.Repository;
import pie.bert.tracker.domain.category.Category;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CategoryRepository {

    private final Map<String, Category> categories;

    public CategoryRepository() {
        this.categories = new HashMap<>();
    }

    public Optional<Category> save(Category category) {
        if (categories.containsKey(category.getCode())) {
            return Optional.empty();
        }
        categories.put(category.getCode(), category);
        return Optional.of(category);
    }

    public Collection<Category> findAll() {
        return Collections.unmodifiableCollection(
                categories.values());
    }

    public Optional<Category> findByCode(String code) {
        return Optional.ofNullable(
                categories.get(code));
    }
}
