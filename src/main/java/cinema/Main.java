package cinema;

import cinema.lib.Injector;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector
                .getInstance(MovieService.class);
        Movie movie1 = new Movie();
        movie1.setTitle("Mad Max: Fury Road");
        movie1.setDescription("Cool film");
        movieService.add(movie1);
        Movie movie2 = new Movie();
        movie2.setTitle("Interstellar");
        movie2.setDescription("Science fiction");
        movieService.add(movie2);
        movieService.getAll().stream().forEach(System.out::println);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(20);
        cinemaHall.setDescription("Black hall");
        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        System.out.println(cinemaHallService.getAll());
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie1);
        LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 8, 12, 0);
        movieSession.setShowTime(localDateTime);
        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        LocalDate localDate = LocalDate.of(2020, 10, 8);
        System.out.println(movieSessionService.findAvailableSessions(1L, localDate));
    }
}
