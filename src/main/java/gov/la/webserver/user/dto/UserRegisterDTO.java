package gov.la.webserver.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Schema(description = "user Register DTO")
@Data
@AllArgsConstructor
public class UserRegisterDTO {
    @Schema(name = "name", description = "user name", example = "tony")
    private  String name;
    @Schema(name = "age", description = "user age", example = "25")
    private Integer age;
    @Schema(name = "nickname", description = "user nickname", example = "iran")
    private String nickname;


}
