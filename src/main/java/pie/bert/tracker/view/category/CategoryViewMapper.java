package pie.bert.tracker.view.category;

import org.springframework.stereotype.Component;
import pie.bert.tracker.domain.category.Category;
import pie.bert.tracker.view.Mapping;
import pie.bert.tracker.view.PathVar;

@Component
public class CategoryViewMapper {

    public CategoryView toView(Category category) {
        String url = Mapping.CATEGORY.replace(PathVar.CATEGORY_CODE_BRACKETS, category.getCode());
        return new CategoryView(category, url);
    }
}
