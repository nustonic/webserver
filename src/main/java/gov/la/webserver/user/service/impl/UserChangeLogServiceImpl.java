package gov.la.webserver.user.service.impl;

import gov.la.webserver.user.dto.UserChangeLogDTO;
import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.entity.UserChangeLog;
import gov.la.webserver.user.repository.UserChangeLogRepository;
import gov.la.webserver.user.service.UserChangeLogService;
import io.micrometer.observation.ObservationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserChangeLogServiceImpl implements UserChangeLogService {

    private final UserChangeLogRepository userChangeLogRepository;



    @Override
    public Boolean createLogUserNickName(UserDTO userDTO, String nickName) {

        final UserChangeLog checkNickNameChange = userDTO.checkNickNameChange(nickName);
        if (checkNickNameChange != null) {
            userChangeLogRepository.save(checkNickNameChange);
            System.out.println("save checkNickNameChange");

            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean createLogUserName(UserDTO userDTO, String name) {

        final UserChangeLog checkNameChange = userDTO.checkNameChange(name);
        if (checkNameChange != null) {
            userChangeLogRepository.save(checkNameChange);
            System.out.println("save checkNameChange");
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean createLogUserAge(UserDTO userDTO, Integer age) {
        final UserChangeLog checkAgeChange = userDTO.checkAgeChange(age);
        if (checkAgeChange != null) {
            userChangeLogRepository.save(checkAgeChange);
            System.out.println("save checkAgeChange");
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public List<UserChangeLogDTO> getAllUserChangeLog() {
        return userChangeLogRepository.findAll()
        .stream().map(UserChangeLogDTO::new)
                .collect(Collectors.toList());

    }


}
