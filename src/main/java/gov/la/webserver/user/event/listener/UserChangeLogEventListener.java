package gov.la.webserver.user.event.listener;

import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.entity.User;
import gov.la.webserver.user.event.UserChangeLogEvent;

import gov.la.webserver.user.service.UserChangeLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserChangeLogEventListener {
    private final UserChangeLogService userChangeLogService;
    @EventListener
    public void handleUserChangeLogEvent(UserChangeLogEvent userChangeLogEvent){
        log.info("call handleUserChangeLogEvent >>>{}", userChangeLogEvent);

        final UserDTO newUser =userChangeLogEvent.getNewUser();
        final User oldUser = userChangeLogEvent.getOldUser();

        userChangeLogService.createLogUserNickName(newUser, oldUser.getNickName());
       userChangeLogService.createLogUserName(newUser, oldUser.getName());
       userChangeLogService.createLogUserAge(newUser, oldUser.getAge());

       log.info("completed handleUserChangeLogEvent!!!");
    }
}
