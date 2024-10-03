package gov.la.webserver.repository;

import gov.la.webserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;



public interface UserRepository extends JpaRepository<User, Long> {

}
