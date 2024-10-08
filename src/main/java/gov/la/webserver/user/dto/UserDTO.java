package gov.la.webserver.user.dto;

import gov.la.webserver.user.entity.User;
import gov.la.webserver.user.entity.UserChangeLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "User DTO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Schema(name = "id", description = "id", example = "1")
    private Long id;

    @Schema(name = "name", description = "name", example ="Struck")
    private String name;

    @Schema(name = "age", description = "age", example = "21")
    private Integer age;

    @Schema(name = "nickName", description = "nickName", example = "Tony Stark")
    private String nickName;
    @Schema(name = "username", description = "username", example = "Tony Stark")
    private String username;

//    public UserDTO(final  String name, final Integer age, final String nickName){
//        this.name= name;
//        this.age= age;
//        this.nickName= nickName;
//        this.username=getUsername();
//
//
//    }

    public UserDTO(final User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        this.nickName = user.getNickName();
        this.username = user.getAccount().getUsername();


    }
    public UserChangeLog checkNickNameChange(final String savedNickName) {

        final String nickName = this.nickName;
        if (!nickName.equals(savedNickName)) {
            return UserChangeLog.createUserLogEntity("nickName", savedNickName, nickName);

        }

        return null;
    }

    public UserChangeLog checkNameChange(final String savedName) {

        final String name = this.name;
        if (!name.equals(savedName)) {
            return UserChangeLog.createUserLogEntity("name", savedName, name);
        }

        return null;
    }

    public UserChangeLog checkAgeChange(Integer savedAge) {
        final Integer age = this.age;
        if (!age.equals(savedAge)) {
            return UserChangeLog.createUserLogEntity("age", savedAge.toString(), age.toString());
        }

        return null;
    }
}
