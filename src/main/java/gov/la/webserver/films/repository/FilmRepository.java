package gov.la.webserver.films.repository;

import gov.la.webserver.films.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
    // Custom query methods for Film can go here
}
