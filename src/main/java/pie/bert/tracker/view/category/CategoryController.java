package pie.bert.tracker.view.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pie.bert.tracker.domain.category.Category;
import pie.bert.tracker.domain.category.CategoryCodeAlreadyExistsException;
import pie.bert.tracker.domain.category.CategoryDomainService;
import pie.bert.tracker.domain.category.CategoryNotFoundException;
import pie.bert.tracker.domain.category.CategoryValidationException;
import pie.bert.tracker.view.ErrorResponse;
import pie.bert.tracker.view.Mapping;
import pie.bert.tracker.view.PathVar;

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

    @GetMapping(path = PathVar.CATEGORY_CODE_BRACKETS)
    public CategoryView viewCategoryByCode(@PathVariable(PathVar.CATEGORY_CODE) String code) {
        Category found = categoryDomainService.findByCode(code);
        return categoryViewMapper.toView(found);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryView createCategory(@RequestBody CategoryToCreate categoryToCreate) {
        Category created = categoryDomainService.create(categoryToCreate.toCategory());
        return categoryViewMapper.toView(created);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CategoryCodeAlreadyExistsException.class)
    public ErrorResponse categoryCodeAlreadyExistsException(CategoryCodeAlreadyExistsException e) {
        return new ErrorResponse(e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse categoryNotFoundException(CategoryNotFoundException e) {
        return new ErrorResponse(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryValidationException.class)
    public ErrorResponse categoryValidationException(CategoryValidationException e) {
        return new ErrorResponse(e);
    }
}
