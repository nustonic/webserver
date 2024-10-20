package gov.la.webserver.review.dto;

import gov.la.webserver.review.entity.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Review DTO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    @Schema(name = "id", description = "ID of the review", example = "1")
    private Long id;

    @Schema(name = "reviewText", description = "Text of the review", example = "This film was amazing!")
    private String reviewText;

    @Schema(name = "rating", description = "Rating of the film", example = "4.5")
    private Integer rating;

    @Schema(name = "filmId", description = "ID of the film being reviewed", example = "1")
    private Long filmId;

    @Schema(name = "userId", description = "ID of the user who reviewed the film", example = "2")
    private Long userId;

    // Constructor to create a DTO from an entity
    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.reviewText = review.getReviewText();
        this.rating = review.getRating();
        this.filmId = review.getFilm().getId();
        this.userId = review.getUser().getId();
    }
}
