package pie.bert.tracker.view;

public class ViewException extends RuntimeException {

    private final String code;

    public ViewException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
