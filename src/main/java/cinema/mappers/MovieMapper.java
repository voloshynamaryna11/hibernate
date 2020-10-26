package cinema.mappers;

import cinema.model.Movie;
import cinema.model.dto.MovieRequestDto;
import cinema.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieResponseDto mapFromMovieToMovieResponseDto(Movie movie) {
        return new MovieResponseDto(movie.getId(),
                movie.getTitle(), movie.getTitle());
    }

    public Movie mapFromMovieRequestDtoToMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }
}
