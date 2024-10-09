package gov.la.webserver.user.service;

import gov.la.webserver.user.dto.UserChangeLogDTO;
import gov.la.webserver.user.dto.UserDTO;

import java.util.List;

public interface UserChangeLogService {
    Boolean createLogUserNickName(UserDTO userDTO, String nickName);
    Boolean createLogUserName(UserDTO userDTO, String name);
    Boolean createLogUserAge(UserDTO userDTO, Integer age);

    List<UserChangeLogDTO> getAllUserChangeLog();


}
