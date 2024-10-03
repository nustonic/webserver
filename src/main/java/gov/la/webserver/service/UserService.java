package gov.la.webserver.service;

import gov.la.webserver.dto.UserDTO;
import gov.la.webserver.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();
    UserDTO registerUser(UserRegisterDTO userRegisterDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    Boolean deleteUser(Long id);
    UserDTO getDetail(Long id);
}
