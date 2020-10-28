package cinema.mappers;

import cinema.model.MovieSession;
import cinema.model.dto.MovieSessionRequestDto;
import cinema.model.dto.MovieSessionResponseDto;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {

    public MovieSessionResponseDto mapFromMovieSessionToMovieSessionResponseDto(
            MovieSession movieSession) {
        MovieSessionResponseDto responseDto = new MovieSessionResponseDto();
        responseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        responseDto.setMovieId(movieSession.getMovie().getId());
        responseDto.setMovieSessionId(movieSession.getId());
        responseDto.setMovieTitle(movieSession.getMovie().getTitle());
        responseDto.setShowTime(movieSession.getShowTime());
        return responseDto;
    }

    public MovieSession mapFromMovieSessionRequestDtoToMovieSession(
            MovieSessionRequestDto movieSessionRequestDto,
            MovieService movieService, CinemaHallService cinemaHallService) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getAll().stream()
                .filter(movie -> movie.getId() == movieSessionRequestDto.getMovieId())
                .findAny().get());
        movieSession.setCinemaHall(cinemaHallService.getAll().stream()
                .filter(cinemaHall -> cinemaHall.getId() == movieSessionRequestDto
                        .getCinemaHallId())
                .findAny().get());
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        return movieSession;
    }
}
