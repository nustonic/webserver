package gov.la.webserver.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "nickName")
    private String nickName;

    
    @Embedded
    private Account account;

    @Enumerated(EnumType.STRING)
    private Role role;


    public User(final  String name, final Integer age, final String nickName){
        this.name= name;
        this.age= age;
        this.nickName= nickName;
        this.role=Role.USER;
    }

    public User(String name,
                Integer age,
                String nickName,
                Account account) {
        this(name, age,nickName);
        this.account = account;
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


    public static User createUserWithAccount(final String name,
                                             final Integer age,
                                             final String nickName,
                                             final String username,
                                             final String password){
        return new User(name,age,nickName,Account.create(username,password));
    }
}
