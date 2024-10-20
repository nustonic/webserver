package gov.la.webserver.review.service.impl;

import gov.la.webserver.films.entity.Film;
import gov.la.webserver.review.dto.ReviewDTO;
import gov.la.webserver.review.entity.Review;
import gov.la.webserver.review.repository.ReviewRepository;
import gov.la.webserver.review.service.ReviewService;
import gov.la.webserver.user.entity.User;
import gov.la.webserver.films.repository.FilmRepository;
import gov.la.webserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(review -> new ReviewDTO(review.getId(), review.getReviewText(), review.getRating(), review.getFilm().getId(), review.getUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByFilm(Long filmId) {
        return reviewRepository.findByFilmId(filmId).stream()
                .map(review -> new ReviewDTO(review.getId(), review.getReviewText(), review.getRating(), review.getFilm().getId(), review.getUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return new ReviewDTO(review.getId(), review.getReviewText(), review.getRating(), review.getFilm().getId(), review.getUser().getId());
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Film film = filmRepository.findById(reviewDTO.getFilmId())
                .orElseThrow(() -> new RuntimeException("Film not found"));
        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = new Review(reviewDTO.getReviewText(), reviewDTO.getRating(), user, film);

        reviewRepository.save(review);
        return new ReviewDTO(review.getId(), review.getReviewText(), review.getRating(), review.getFilm().getId(), review.getUser().getId());
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setReviewText(reviewDTO.getReviewText());
        review.setRating(reviewDTO.getRating());

        reviewRepository.save(review);
        return new ReviewDTO(review.getId(), review.getReviewText(), review.getRating(), review.getFilm().getId(), review.getUser().getId());
    }

    @Override
    public Boolean deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
        return true;
    }
}
