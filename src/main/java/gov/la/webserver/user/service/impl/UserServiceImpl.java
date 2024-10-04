package gov.la.webserver.user.service.impl;

import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.dto.UserRegisterDTO;
import gov.la.webserver.user.entity.User;
import gov.la.webserver.user.repository.UserRepository;

import gov.la.webserver.user.service.UserChangeLogService;
import gov.la.webserver.user.service.UserService;
import gov.la.webserver.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
     private final UserRepository userRepository;
     private final UserChangeLogService userChangeLogService;

    @Override
    public List<UserDTO> findAllUsers() {
       return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());

    }

    @Override
    public UserDTO registerUser(UserRegisterDTO registerDTO) {
        User user =new  User(
                registerDTO.getName(),
                registerDTO.getAge(),
                registerDTO.getNickname()
        );
        User savedUser= userRepository.save(user);
        return new UserDTO(savedUser);
    }


//    @Override
//    public UserDTO updateUser(Long id, UserDTO userDTO) {
//       User savedUser= userRepository.findById(id).orElse(null);
//       if (savedUser==null){
//           return null;
//       }else {
//           final UserChangeLog checkNickNameChange =userDTO.checkNickNameChange(savedUser);
//           if(checkNickNameChange!= null){
//               userChangeLogRepository.save(checkNickNameChange);
//               System.out.println("save checkNickNameChange");
//           }
//           final UserChangeLog checkNameChange =userDTO.checkNameChange(savedUser);
//           if(checkNameChange!= null){
//               userChangeLogRepository.save(checkNameChange);
//               System.out.println("save checkNameChange");
//           }
//           final UserChangeLog checkAgeChange =userDTO.checkAgeChange(savedUser);
//           if(checkAgeChange!= null){
//               userChangeLogRepository.save(checkAgeChange);
//               System.out.println("save checkAgeChange");
//           }
//
//           savedUser.changeNickName(userDTO.getNickName());
//           savedUser.changeName(userDTO.getName());
//           savedUser.changeAge(userDTO.getAge());
//           User updateUser = userRepository.save(savedUser);
//           return  new UserDTO(updateUser);
//       }
//
//    }

    @Override
    public Boolean deleteUser(Long id) {
        final User user =userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return Boolean.TRUE;
    }

    @Override
    public UserDTO getDetail(Long id) {
        return userRepository.findById(id)
                .map(UserDTO::new)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {

        User savedUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
            userChangeLogService.createLogUserNickName(userDTO, savedUser.getNickName());
            userChangeLogService.createLogUserName(userDTO, savedUser.getName());
            userChangeLogService.createLogUserAge(userDTO, savedUser.getAge());


            savedUser.changeNickName(userDTO.getNickName());
            savedUser.changeName(userDTO.getName());
            savedUser.changeAge(userDTO.getAge());
            User updateUser = userRepository.save(savedUser);
            return new UserDTO(updateUser);

    }
}
