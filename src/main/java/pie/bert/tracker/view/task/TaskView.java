package pie.bert.tracker.view.task;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TaskView {

    private final String id;
    private final String start;
    private final String end;
    private final String description;
    private final String url;

    public TaskView(String id, String start, String end, String description, String url) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.description = description;
        this.url = url;
    }
}
