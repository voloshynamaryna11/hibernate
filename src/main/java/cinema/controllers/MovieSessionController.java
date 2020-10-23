package cinema.controllers;

import cinema.mappers.MovieSessionMapper;
import cinema.model.MovieSession;
import cinema.model.dto.MovieSessionRequestDto;
import cinema.model.dto.MovieSessionResponseDto;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovieSessionController {
    private MovieSessionService movieSessionService;
    private MovieService movieService;
    private CinemaHallService cinemaHallService;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping("/movie-sessions")
    public void add(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSessionMapper movieSessionMapper = new MovieSessionMapper();
        movieSessionMapper.setMovieSessionRequestDto(movieSessionRequestDto);
        movieSessionService.add(movieSessionMapper
                .mapFromMovieSessionRequestDtoToMovieSession(movieService,
                        cinemaHallService));
    }

    @ResponseBody
    @GetMapping("/movie-sessions/available{movieId}{date}")
    public List<MovieSessionResponseDto> getAllAvailable(@PathVariable Long movieId,
                                                         @PathVariable LocalDate date) {
        MovieSessionMapper mapper = new MovieSessionMapper();
        List<MovieSessionResponseDto> list = new ArrayList<>();
        for (MovieSession movieSession : movieSessionService
                .findAvailableSessions(movieId, date)) {
            mapper.setMovieSession(movieSession);
            list.add(mapper.mapFromMovieSessionToMovieSessionResponseDto());
        }
        return list;
    }
}
