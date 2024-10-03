package gov.la.webserver.dto;

import gov.la.webserver.entity.User;
import gov.la.webserver.entity.UserChangeLog;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Generated;
import lombok.Getter;

@Schema(description = "User DTO")
@Getter
public class UserDTO {
    @Schema(name = "id", description = "id", example = "1")
    private Long id;

    @Schema(name = "name", description = "name", example = "bollow")
    private String name;

    @Schema(name = "age", description = "age", example = "21")
    private Integer age;

    @Schema(name = "nickName", description = "nickName", example = "Tony Stark")
    private String nickName;


    public UserDTO(final  String name, final Integer age, final String nickName){
        this.name= name;
        this.age= age;
        this.nickName= nickName;

    }

    public UserDTO(final User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        this.nickName = user.getNickName();


    }



    public UserChangeLog checkNickNameChange(User savedUser) {

        final String nickName = this.nickName;
        final String savedNickName = savedUser.getNickName();

        if (!nickName.equals(savedNickName)) {
            final UserChangeLog nickChangeLog = new UserChangeLog();

            nickChangeLog.setBeforeField("nickName");
            nickChangeLog.setBeforeValue(savedNickName);
            nickChangeLog.setAfterField("nickName");
            nickChangeLog.setAfterValue(nickName);

            return nickChangeLog;
        }

        return null;
    }

    public UserChangeLog checkNameChange(User savedUser) {

        final String name = this.name;
        final String savedName = savedUser.getName();

        if (!name.equals(savedName)) {
            final UserChangeLog nameChangeLog = new UserChangeLog();

            nameChangeLog.setBeforeField("name");
            nameChangeLog.setBeforeValue(savedName);
            nameChangeLog.setAfterField("name");
            nameChangeLog.setAfterValue(name);

            return nameChangeLog;
        }

        return null;
    }

    public UserChangeLog checkAgeChange(User savedUser) {

        final Integer age = this.age;
        final Integer savedAge = savedUser.getAge();

        if (!age.equals(savedAge)) {
            final UserChangeLog ageChangeLog = new UserChangeLog();

            ageChangeLog.setBeforeField("age");
            ageChangeLog.setBeforeValue(savedAge.toString());
            ageChangeLog.setAfterField("age");
            ageChangeLog.setAfterValue(age.toString());

            return ageChangeLog;
        }

        return null;
    }


}
