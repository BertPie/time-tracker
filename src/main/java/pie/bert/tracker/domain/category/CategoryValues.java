package pie.bert.tracker.domain.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pie.bert.tracker.domain.category")
public class CategoryValues {
    private static final Logger logger = LoggerFactory.getLogger(CategoryValues.class);

    private int maxCodeLength;
    private int minCodeLength;
    private int maxNameLength;
    private int minNameLength;
    private int maxDescriptionLength;
    private int minDescriptionLength;

    public CategoryValues() {
        // empty
    }

    public int getMaxCodeLength() {
        return maxCodeLength;
    }

    public void setMaxCodeLength(int maxCodeLength) {
        this.maxCodeLength = maxCodeLength;
    }

    public int getMinCodeLength() {
        return minCodeLength;
    }

    public void setMinCodeLength(int minCodeLength) {
        this.minCodeLength = minCodeLength;
    }

    public int getMaxNameLength() {
        return maxNameLength;
    }

    public void setMaxNameLength(int maxNameLength) {
        this.maxNameLength = maxNameLength;
    }

    public int getMinNameLength() {
        return minNameLength;
    }

    public void setMinNameLength(int minNameLength) {
        this.minNameLength = minNameLength;
    }

    public int getMaxDescriptionLength() {
        return maxDescriptionLength;
    }

    public void setMaxDescriptionLength(int maxDescriptionLength) {
        this.maxDescriptionLength = maxDescriptionLength;
    }

    public int getMinDescriptionLength() {
        return minDescriptionLength;
    }

    public void setMinDescriptionLength(int minDescriptionLength) {
        this.minDescriptionLength = minDescriptionLength;
    }
}
