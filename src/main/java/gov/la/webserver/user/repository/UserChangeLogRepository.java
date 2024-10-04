package gov.la.webserver.user.repository;


import gov.la.webserver.user.entity.UserChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChangeLogRepository extends JpaRepository<UserChangeLog, Long>{

}
