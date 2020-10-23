package cinema.controllers;

import cinema.mappers.MovieMapper;
import cinema.model.Movie;
import cinema.model.dto.MovieResponseDto;
import cinema.service.MovieService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovieController {
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies")
    public void add(@RequestBody MovieResponseDto movieResponseDto) {
        MovieMapper mapper = new MovieMapper();
        mapper.setMovieResponseDto(movieResponseDto);
        movieService.add(mapper.mapFromMovieResponseDtoToMovie());
    }

    @ResponseBody
    @GetMapping("/movies")
    public List<MovieResponseDto> getAll() {
        MovieMapper movieMapper = new MovieMapper();
        List<MovieResponseDto> list = new ArrayList<>();
        for (Movie movie : movieService.getAll()) {
            movieMapper.setMovie(movie);
            list.add(movieMapper.mapFromMovieToMovieResponseDto());
        }
        return list;
    }
}
