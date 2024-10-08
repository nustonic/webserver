package gov.la.webserver.user.service;

import gov.la.webserver.user.entity.LoginUser;
import gov.la.webserver.user.entity.User;
import gov.la.webserver.user.repository.UserRepository;
import gov.la.webserver.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService {
    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user=userRepository.findByAccount_username(username)
                .orElseThrow(UserNotFoundException::new);

        return new LoginUser(user);
    }
}
