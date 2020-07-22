package pie.bert.tracker.infra.repository.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pie.bert.tracker.domain.category.Category;
import pie.bert.tracker.domain.category.CategoryCodeAlreadyExistsException;
import pie.bert.tracker.domain.category.CategoryRepositoryService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryMySqlRepositoryService implements CategoryRepositoryService {

    private final CategoryMySqlRepository repository;

    @Autowired
    public CategoryMySqlRepositoryService(CategoryMySqlRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category category) throws CategoryCodeAlreadyExistsException {
        boolean codeAlreadyExists = repository.existsById(category.getCode());
        if (codeAlreadyExists) {
            throw new CategoryCodeAlreadyExistsException("Repository already contains category with code: " + category.getCode());
        } else {
            CategoryEntity categoryToSave = new CategoryEntity(category);
            return repository.save(categoryToSave).toCategory();
        }
    }

    @Override
    public Collection<Category> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(CategoryEntity::toCategory)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findByCode(String code) {
        return repository.findById(code)
                .map(CategoryEntity::toCategory);
    }
}
