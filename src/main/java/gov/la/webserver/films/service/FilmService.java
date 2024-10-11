package gov.la.webserver.films.service;

import gov.la.webserver.films.dto.FilmDTO;

import java.util.List;

public interface FilmService {
    List<FilmDTO> getAllFilms();           // Get all films
    FilmDTO getFilm(Long id);              // Get a specific film by ID
    FilmDTO createFilm(FilmDTO filmDTO);   // Create a new film
    FilmDTO updateFilm(Long id, FilmDTO filmDTO); // Update a film
    void deleteFilm(Long id);              // Delete a film by ID
}
