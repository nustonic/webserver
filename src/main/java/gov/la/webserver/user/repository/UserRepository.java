package gov.la.webserver.user.repository;

import gov.la.webserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccount_username(String username);
}
