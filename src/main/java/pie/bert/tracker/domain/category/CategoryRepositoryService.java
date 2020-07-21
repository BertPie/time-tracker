package pie.bert.tracker.domain.category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryRepositoryService {

    /**
     * Creates a new category and stores it in a repository. Creation of category with the same {@code code}
     * is not allowed, will result in throwing {@link CategoryCodeAlreadyExistsException}.
     *
     * @param category to be saved
     * @return newly saved category
     * @throws CategoryCodeAlreadyExistsException if category with a given code already exists
     */
    Category create(Category category) throws CategoryCodeAlreadyExistsException;

    /**
     * Finds all categories and returns unmodifiable {@link Collection}. If none categories are found, returns
     * an empty collection.
     *
     * @return unmodifiable collection
     */
    Collection<Category> findAll();

    /**
     * Finds category by provided category code.
     *
     * @param code unique for the category
     * @return either optional with found category or empty optional
     */
    Optional<Category> findByCode(String code);
}
