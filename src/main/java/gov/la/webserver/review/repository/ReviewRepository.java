package gov.la.webserver.review.repository;

import gov.la.webserver.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFilmId(Long filmId);
}
