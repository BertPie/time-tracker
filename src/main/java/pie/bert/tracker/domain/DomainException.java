package pie.bert.tracker.domain;

public class DomainException extends RuntimeException {

    public static final String CATEGORY_ALREADY_EXISTS = "CAT_0001";

    private final String code;

    public DomainException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}