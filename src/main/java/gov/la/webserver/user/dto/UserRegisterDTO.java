package gov.la.webserver.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;


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
    @Schema(name = "username", description = "username", example = "iran")
    private String username;
    @Schema(name = "password", description = "password", example = "ira455n")
    private String password;


}
