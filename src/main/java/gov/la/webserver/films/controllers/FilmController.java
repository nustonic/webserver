package gov.la.webserver.films.controllers;

import gov.la.webserver.common.response.dto.ApiResponseDTO;
import gov.la.webserver.films.dto.FilmDTO;
import gov.la.webserver.films.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Tag(name = "Film API", description = "Film API for managing films")
@RestController
@RequestMapping("api/v1/films")
@RequiredArgsConstructor
@Slf4j
public class FilmController {

    private final FilmService filmService;

    @Operation(
            summary = "Create a new film",
            description = "Creates a new film with the given details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Film successfully created"
    )
    @Secured("ROLE_ADMIN")
    @PostMapping
    public Mono<ApiResponseDTO<Object>> createFilm(@RequestBody FilmDTO filmDTO) {
        FilmDTO savedFilm = filmService.createFilm(filmDTO);
        return Mono.just(ApiResponseDTO.builder()
                .code(200)
                .message("Film Created")
                .body(savedFilm)
                .build());
    }

    @Operation(
            summary = "Update an existing film",
            description = "Updates the details of a film by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Film successfully updated"
    )
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> updateFilm(@PathVariable Long id, @RequestBody FilmDTO filmDTO) {
        FilmDTO updatedFilm = filmService.updateFilm(id, filmDTO);
        return Mono.just(ApiResponseDTO.builder()
                .code(200)
                .message("Film Updated")
                .body(updatedFilm)
                .build());
    }

    @Operation(
            summary = "Delete a film",
            description = "Deletes a film by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Film successfully deleted"
    )
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return Mono.just(ApiResponseDTO.builder()
                .code(200)
                .message("Film Deleted")
                .build());
    }

    @Operation(
            summary = "Get all films",
            description = "Retrieves a list of all films",
            security = @SecurityRequirement(name = "basicScheme")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of films"
    )
    @GetMapping
    public Flux<ApiResponseDTO<Object>> getAllFilms() {
        List<FilmDTO> films = filmService.getAllFilms();
        return Flux.fromIterable(films)
                .map(film -> ApiResponseDTO.builder()
                        .code(200)
                        .message("Film List Found")
                        .body(film)
                        .build());
    }

    @Operation(
            summary = "Get a film by ID",
            description = "Retrieves the details of a film by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved film"
    )
    @GetMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> getFilm(@PathVariable Long id) {
        FilmDTO filmDTO = filmService.getFilm(id);
        return Mono.just(ApiResponseDTO.builder()
                .code(200)
                .message("Film Found")
                .body(filmDTO)
                .build());
    }
}
