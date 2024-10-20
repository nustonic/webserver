package gov.la.webserver.films.service.impl;


import gov.la.webserver.films.dto.FilmDTO;
import gov.la.webserver.films.entity.Film;
import gov.la.webserver.films.repository.FilmRepository;
import gov.la.webserver.films.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Override
    public List<FilmDTO> getAllFilms() {
        return filmRepository.findAll().stream()
                .map(film -> new FilmDTO(film.getId(), film.getTitle(), film.getDescription(), film.getDirector(), film.getReleaseDate()
                        , film.getImgPath()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public FilmDTO getFilm(Long id) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        return new FilmDTO(film.getId(), film.getTitle(), film.getDescription(), film.getDirector(), film.getReleaseDate()
                , film.getImgPath()
        );
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) {
        Film film = new Film(filmDTO.getTitle(), filmDTO.getDescription(), filmDTO.getDirector(), filmDTO.getReleaseDate()
                , filmDTO.getImgPath()
        );

        filmRepository.save(film);
        return new FilmDTO(film.getId(), film.getTitle(), film.getDescription(), film.getDirector(), film.getReleaseDate()
                ,film.getImgPath()
        );
    }

    @Override
    public FilmDTO updateFilm(Long id, FilmDTO filmDTO) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        film.setTitle(filmDTO.getTitle());
        film.setDescription(filmDTO.getDescription());
        film.setDirector(filmDTO.getDirector());
        film.setReleaseDate(filmDTO.getReleaseDate());
        film.setImgPath(filmDTO.getImgPath());
        filmRepository.save(film);
        return new FilmDTO(film.getId(), film.getTitle(), film.getDescription(), film.getDirector(), film.getReleaseDate()
                ,film.getImgPath()
        );
    }

    @Override
    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }
}
