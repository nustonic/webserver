package gov.la.webserver.review.service;

import gov.la.webserver.review.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getReviewsByFilm(Long filmId);

    ReviewDTO getReview(Long id);

    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO updateReview(Long id, ReviewDTO reviewDTO);
    Boolean deleteReview(Long id);
    List<ReviewDTO> getAllReviews();
}
