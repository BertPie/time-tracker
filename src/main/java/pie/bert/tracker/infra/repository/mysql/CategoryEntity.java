package pie.bert.tracker.infra.repository.mysql;

import pie.bert.tracker.domain.category.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    public CategoryEntity() {
    }

    public CategoryEntity(Category category) {
        this.code = category.getCode();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public CategoryEntity(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Category toCategory() {
        return new Category(
                code,
                name,
                description
        );
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
