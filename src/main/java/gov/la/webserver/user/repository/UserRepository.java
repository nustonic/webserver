package gov.la.webserver.user.repository;

import gov.la.webserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
