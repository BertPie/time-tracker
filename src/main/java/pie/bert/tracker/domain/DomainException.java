package pie.bert.tracker.domain;

public class DomainException extends RuntimeException {

    protected static final String CATEGORY_ALREADY_EXISTS = "CAT_0001";
    protected static final String CATEGORY_NOT_FOUND = "CAT_0002";
    protected static final String CATEGORY_NOT_VALID = "CAT_0003";

    private final String code;

    public DomainException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
