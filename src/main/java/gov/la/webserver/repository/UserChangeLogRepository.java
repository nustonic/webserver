package gov.la.webserver.repository;


import gov.la.webserver.entity.UserChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChangeLogRepository extends JpaRepository<UserChangeLog, Long>{

}
