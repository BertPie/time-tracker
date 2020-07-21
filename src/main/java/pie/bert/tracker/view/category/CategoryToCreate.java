package pie.bert.tracker.view.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import pie.bert.tracker.domain.category.Category;

public class CategoryToCreate {

    private final String code;
    private final String name;
    private final String description;

    public CategoryToCreate(@JsonProperty("code") String code,
                            @JsonProperty("name") String name,
                            @JsonProperty("description") String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Category toCategory() {
        return new Category(
                code.trim(),
                name.trim(),
                description.trim());
    }
}
