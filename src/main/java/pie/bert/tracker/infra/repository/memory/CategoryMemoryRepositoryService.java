package pie.bert.tracker.infra.repository.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pie.bert.tracker.domain.category.Category;
import pie.bert.tracker.domain.category.CategoryRepositoryService;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class CategoryMemoryRepositoryService implements CategoryRepositoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryMemoryRepositoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category)
                .orElseThrow(alreadyContainsException(category.getCode()));
    }

    private Supplier<IllegalArgumentException> alreadyContainsException(String code) {
        return () -> new IllegalArgumentException("Repository already contains category with code: " + code);
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByCode(String code) {
        return categoryRepository.findByCode(code);
    }
}
