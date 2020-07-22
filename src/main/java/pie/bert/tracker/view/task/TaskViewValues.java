package pie.bert.tracker.view.task;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pie.bert.tracker.view.task")
public class TaskViewValues {
    private String dataTimeFormat;
    private int digitsInTaskViewId;

    public TaskViewValues() {
        // empty
    }

    public String getDataTimeFormat() {
        return dataTimeFormat;
    }

    public void setDataTimeFormat(String dataTimeFormat) {
        this.dataTimeFormat = dataTimeFormat;
    }

    public int getDigitsInTaskViewId() {
        return digitsInTaskViewId;
    }

    public void setDigitsInTaskViewId(int digitsInTaskViewId) {
        this.digitsInTaskViewId = digitsInTaskViewId;
    }
}
