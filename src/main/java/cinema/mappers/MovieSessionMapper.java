package cinema.mappers;

import cinema.model.MovieSession;
import cinema.model.dto.MovieSessionRequestDto;
import cinema.model.dto.MovieSessionResponseDto;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private MovieSession movieSession;
    private MovieSessionRequestDto movieSessionRequestDto;
    private MovieSessionResponseDto movieSessionResponseDto;

    public MovieSession getMovieSession() {
        return movieSession;
    }

    public void setMovieSession(MovieSession movieSession) {
        this.movieSession = movieSession;
    }

    public MovieSessionRequestDto getMovieSessionRequestDto() {
        return movieSessionRequestDto;
    }

    public void setMovieSessionRequestDto(MovieSessionRequestDto movieSessionRequestDto) {
        this.movieSessionRequestDto = movieSessionRequestDto;
    }

    public MovieSessionResponseDto getMovieSessionResponseDto() {
        return movieSessionResponseDto;
    }

    public void setMovieSessionResponseDto(MovieSessionResponseDto movieSessionResponseDto) {
        this.movieSessionResponseDto = movieSessionResponseDto;
    }

    public MovieSessionResponseDto mapFromMovieSessionToMovieSessionResponseDto() {
        this.setMovieSessionResponseDto(new MovieSessionResponseDto(this
                .getMovieSession().getCinemaHall().getId(),
                this.getMovieSession().getShowTime(),
                this.getMovieSession().getMovie().getTitle()));
        return this.getMovieSessionResponseDto();
    }

    public MovieSession mapFromMovieSessionRequestDtoToMovieSession(
            MovieService movieService, CinemaHallService cinemaHallService) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getAll()
                .stream()
                .filter(movie -> movie.getId() == movieSessionRequestDto.getMovieId())
                .findFirst().get());
        movieSession.setCinemaHall(cinemaHallService.getAll().stream()
                .filter(cinemaHall -> cinemaHall.getId() == movieSessionRequestDto
                        .getCinemaHallId())
                .findFirst().get());
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        return movieSession;
    }
}
