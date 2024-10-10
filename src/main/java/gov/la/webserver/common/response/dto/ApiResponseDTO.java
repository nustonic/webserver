package gov.la.webserver.common.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponseDTO<T> {
    private int code;
    private String message;

    private T body;

    public ApiResponseDTO(final  int code, final String message){
        this.code=code;
        this.message=message;

    }

    public ApiResponseDTO(final int code, final T body){
        this.code=code;
        this.body=body;

    }

}
