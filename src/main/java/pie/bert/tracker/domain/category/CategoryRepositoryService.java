package pie.bert.tracker.domain.category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryRepositoryService {

    /**
     * Creates a new category and stores it in a repository. Creation of category with the same {@code code}
     * is not allowed, will result in throwing {@link IllegalArgumentException}.
     *
     * @param category to be saved
     * @return newly saved category
     * @throws IllegalArgumentException if category with a given code already exists
     */
    Category create(Category category) throws IllegalArgumentException;

    Collection<Category> findAll();

    Optional<Category> findByCode(String code);
}
