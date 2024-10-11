package gov.la.webserver.films.dto;

import gov.la.webserver.films.entity.Film;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Film DTO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    @Schema(name = "id", description = "ID of the film", example = "1")
    private Long id;

    @Schema(name = "title", description = "Title of the film", example = "Inception")
    private String title;

    @Schema(name = "description", description = "Description of the film", example = "A mind-bending thriller...")
    private String description;

    @Schema(name = "director", description = "Director of the film", example = "Christopher Nolan")
    private String director;

    @Schema(name = "releaseDate", description = "Release date of the film", example = "2010-07-16")
    private String releaseDate;
    @Schema(name = "img_Path", description = "img", example = "img.jpg")
    private String imgPath;
    // Constructor to create a DTO from an entity
    public FilmDTO(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.description = film.getDescription();
        this.director = film.getDirector();
        this.releaseDate = film.getReleaseDate();
        this.imgPath = film.getImgPath();
    }
}
