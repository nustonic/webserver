package gov.la.webserver.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDateTime;


// swagger
@Schema(description = "user")

// for jpa
@Entity
@Table(name = "user_change_log")

// for lombok
@EqualsAndHashCode(of = "id")
@Setter
public class UserChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "beforeField")
    private String beforeField;

    @Column(name = "beforeValue")
    private String beforeValue;

    @Column(name = "afterField")
    private String afterField;

    @Column(name = "afterValue")
    private String afterValue;

    @Column(name = "createTime")
    private LocalDateTime createTime = LocalDateTime.now();



}
