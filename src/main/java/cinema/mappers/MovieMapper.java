package cinema.mappers;

import cinema.model.Movie;
import cinema.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    private Movie movie;
    private MovieResponseDto movieResponseDto;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieResponseDto getMovieResponseDto() {
        return movieResponseDto;
    }

    public void setMovieResponseDto(MovieResponseDto movieResponseDto) {
        this.movieResponseDto = movieResponseDto;
    }

    public MovieResponseDto mapFromMovieToMovieResponseDto() {
        this.setMovieResponseDto(new MovieResponseDto(this.getMovie().getTitle(),
                this.getMovie().getDescription()));
        return this.getMovieResponseDto();
    }

    public Movie mapFromMovieResponseDtoToMovie() {
        Movie movie = new Movie();
        movie.setTitle(this.getMovieResponseDto().getTitle());
        movie.setDescription(this.getMovieResponseDto()
                .getDescription());
        this.setMovie(movie);
        return this.getMovie();
    }
}
