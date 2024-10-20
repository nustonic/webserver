package gov.la.webserver.review.entity;

import gov.la.webserver.films.entity.Film;
import gov.la.webserver.user.entity.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "review")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewText;

    private int rating; // Rating out of 5

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Review(String reviewText, int rating, User user, Film film) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.user = user;
        this.film = film;
    }

    public Review() {
    }

    // Other methods (if needed)
    public void updateReview(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }
}
