package gov.la.webserver.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {
    NOT_FOUND_USER(100, "Not found User", HttpStatus.NOT_FOUND);
    private  final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
     ErrorCode(Integer code, String message, HttpStatus httpStatus) {

        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
