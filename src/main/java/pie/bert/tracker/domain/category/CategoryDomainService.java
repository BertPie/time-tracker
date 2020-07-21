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

    public Category create(Category category) {
        // todo: validation
        return categoryRepositoryService.create(category);
    }

    public Collection<Category> findAll() {
        return categoryRepositoryService.findAll();
    }
}
