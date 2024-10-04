package gov.la.webserver.user.service;

import gov.la.webserver.user.dto.UserDTO;

public interface UserChangeLogService {
    Boolean createLogUserNickName(UserDTO userDTO, String nickName);
    Boolean createLogUserName(UserDTO userDTO, String name);
    Boolean createLogUserAge(UserDTO userDTO, Integer age);
}
