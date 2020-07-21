package pie.bert.tracker.domain.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryDomainService {

    private final CategoryRepositoryService categoryRepositoryService;

    @Autowired
    public CategoryDomainService(CategoryRepositoryService categoryRepositoryService) {
        this.categoryRepositoryService = categoryRepositoryService;
    }

    /**
     * Saves provided category in repository if the category code is not present, otherwise throws an exception.
     *
     * @param category to be saved
     * @return saved category
     * @throws CategoryCodeAlreadyExistsException if code already exists in repository
     */
    public Category create(Category category) throws CategoryCodeAlreadyExistsException {
        return categoryRepositoryService.create(category);
    }

    /**
     * Find all categories.
     *
     * @return all categories
     */
    public Collection<Category> findAll() {
        return categoryRepositoryService.findAll();
    }

    /**
     * Find category by it's code. If none is found throws an exception.
     *
     * @param code of the category to be found
     * @return found category
     * @throws CategoryNotFoundException if category with given code is not found
     */
    public Category findByCode(String code) throws CategoryNotFoundException {
        return categoryRepositoryService.findByCode(code)
                .orElseThrow(() -> new CategoryNotFoundException("Could not find category with code: " + code));
    }
}
