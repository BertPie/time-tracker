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

    private final CategoryMemoryRepository categoryMemoryRepository;

    @Autowired
    public CategoryMemoryRepositoryService(CategoryMemoryRepository categoryMemoryRepository) {
        this.categoryMemoryRepository = categoryMemoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryMemoryRepository.save(category)
                .orElseThrow(alreadyContainsException(category.getCode()));
    }

    private Supplier<IllegalArgumentException> alreadyContainsException(String code) {
        return () -> new IllegalArgumentException("Repository already contains category with code: " + code);
    }

    @Override
    public Collection<Category> findAll() {
        return categoryMemoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByCode(String code) {
        return categoryMemoryRepository.findByCode(code);
    }
}
