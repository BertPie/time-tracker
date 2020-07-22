package pie.bert.tracker.view;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import pie.bert.tracker.domain.DomainException;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ErrorResponse {

    private final String errorCode;
    private final String message;

    public ErrorResponse(DomainException e) {
        this.errorCode = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorResponse(ViewException e) {
        this.errorCode = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
