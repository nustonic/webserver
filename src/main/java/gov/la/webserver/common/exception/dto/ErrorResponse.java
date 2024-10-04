package gov.la.webserver.common.exception.dto;

import gov.la.webserver.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private Integer errorCode;
    private String message;
    public ErrorResponse(ErrorCode errorCode){
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}
