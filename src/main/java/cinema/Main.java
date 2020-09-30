package cinema;

import cinema.lib.Injector;
import cinema.model.Movie;
import cinema.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie1 = new Movie();
        movie1.setTitle("Mad Max: Fury Road");
        movie1.setDescription("Cool film");
        movieService.add(movie1);
        movieService.getAll().stream().forEach(System.out::println);
    }
}
