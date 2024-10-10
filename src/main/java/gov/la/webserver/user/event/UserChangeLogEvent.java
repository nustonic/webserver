package gov.la.webserver.user.event;


import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class UserChangeLogEvent extends ApplicationEvent {
    private final UserDTO newUser;
    private final User oldUser;
    public UserChangeLogEvent(final UserDTO newUser, final User oldUser){
        super(newUser);
        this.newUser= newUser;
        this.oldUser=oldUser;
    }
}
