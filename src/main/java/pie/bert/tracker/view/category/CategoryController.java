package pie.bert.tracker.view.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pie.bert.tracker.domain.category.Category;
import pie.bert.tracker.domain.category.CategoryDomainService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = Mapping.CATEGORIES)
public class CategoryController {

    private final CategoryDomainService categoryDomainService;
    private final CategoryViewMapper categoryViewMapper;

    @Autowired
    public CategoryController(CategoryDomainService categoryDomainService,
                              CategoryViewMapper categoryViewMapper) {
        this.categoryDomainService = categoryDomainService;
        this.categoryViewMapper = categoryViewMapper;
    }

    @GetMapping
    public List<CategoryView> viewAllCategories() {
        return categoryDomainService
                .findAll()
                .stream()
                .map(categoryViewMapper::toView)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryView createCategory(@RequestBody @Valid CategoryToCreate categoryToCreate) {
        Category created = categoryDomainService.create(categoryToCreate.toCategory());
        return categoryViewMapper.toView(created);
    }
}
