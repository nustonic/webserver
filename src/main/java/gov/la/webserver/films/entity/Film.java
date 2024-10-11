package gov.la.webserver.films.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "films_film")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Setter
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "director")
    private String director;

    @Column(name = "release_date")
    private String releaseDate;
    @Column(name = "img_path")
    private String imgPath;
    public Film(String title, String description, String director, String releaseDate
    ,String imgPath
    ) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.releaseDate = releaseDate;
        this.imgPath = imgPath;
    }

    public void setDescription(String description) {
    }

    public void setDirector(String director) {
    }

    public void setReleaseDate(String releaseDate) {
    }

    public void setImgPath(String imgPath) {
    }
}