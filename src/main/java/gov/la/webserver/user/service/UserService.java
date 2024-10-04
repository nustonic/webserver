package gov.la.webserver.user.service;

import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();
    UserDTO registerUser(UserRegisterDTO userRegisterDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    Boolean deleteUser(Long id);
    UserDTO getDetail(Long id);
}
