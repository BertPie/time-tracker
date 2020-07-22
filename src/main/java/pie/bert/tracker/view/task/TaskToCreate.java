package pie.bert.tracker.view.task;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskToCreate {

    private final String category;
    private final String start;
    private final String end;
    private final String description;

    public TaskToCreate(@JsonProperty("category") String category,
                        @JsonProperty("start") String start,
                        @JsonProperty("end") String end,
                        @JsonProperty("description") String description) {
        this.category = category;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }
}
