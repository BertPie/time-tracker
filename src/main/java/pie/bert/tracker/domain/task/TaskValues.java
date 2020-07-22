package pie.bert.tracker.domain.task;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pie.bert.tracker.domain.task")
public class TaskValues {

    private int maxDescriptionLength;
    private int minDescriptionLength;

    public TaskValues() {
        // empty
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
