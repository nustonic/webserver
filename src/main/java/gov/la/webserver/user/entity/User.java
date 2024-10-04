package gov.la.webserver.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


// swagger
//@Schema(description = "user")

// for jpa
@Entity
@Table(name = "users_user")

// for lombok
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
//    @Schema(name = "id",
//            description = "user id", example = "1")
    private Long id;

    @Column(name = "name")
//    @Schema(name = "name",
//            description = "user name", example = "KIM HAN")
    private String name;

    @Column(name = "age")
//    @Schema(name = "age",
//            description = "user age", example = "23")
    private Integer age;

    @Column(name = "nickName")
//    @Schema(name = "nickName",
//            description = "user nickName", example = "SuperMan")
    private String nickName;

    public User(final  String name, final Integer age, final String nickName){
        this.name= name;
        this.age= age;
        this.nickName= nickName;

    }

    public void changeName(String name) {
        this.name=name;
    }

    public void changeNickName(String nickName) {
        this.nickName=nickName;
    }

    public void changeAge(Integer age) {
        this.age=age;
    }
}
