package pie.bert.tracker.view.category;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import pie.bert.tracker.domain.category.Category;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CategoryView {

    private final String code;
    private final String name;
    private final String description;
    private final String url;

    public CategoryView(Category category, String url) {
        this.code = category.getCode();
        this.name = category.getName();
        this.description = category.getDescription();
        this.url = url;
    }
}
