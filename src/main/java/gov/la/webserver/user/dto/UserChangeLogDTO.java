package gov.la.webserver.user.dto;


import gov.la.webserver.user.entity.UserChangeLog;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
@Schema(description = "User change Log DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChangeLogDTO {

    @Schema(name = "id", description ="id", example = "user id")
    private Long id;

    @Schema(name = "beforeField",description = "Field before change")
    private String beforeField;

    @Schema(name = "beforeValue", description = "Field before change")
    private String beforeValue;

    @Schema(name = "afterField", description = "Field after change")
    private String afterField;

    @Schema(name = "afterValue", description = "Value after change")
    private String afterValue;

    @Schema(name = "createTime", description = "time of change")
    private LocalDateTime createTime;

    public UserChangeLogDTO(UserChangeLog entity){
        this.id=entity.getId();
        this.beforeField=entity.getBeforeField();
        this.beforeValue=entity.getBeforeValue();
        this.afterField = entity.getAfterField();
        this.afterValue= entity.getAfterValue();
        this.createTime= entity.getCreateTime();

    }
}

