package cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private Long userId;
    private String email;
    private List<Long> ticketsId;
    private List<Long> movieSessionsId;
    private List<String> titles;
    private List<LocalDateTime> showTimes;
    private List<Long> cinemaHallsId;

    public ShoppingCartResponseDto(Long id, Long userId,
                                   String email, List<Long> ticketsId,
                                   List<Long> movieSessionsId, List<String> titles,
                                   List<LocalDateTime> showTimes, List<Long> cinemaHallsId) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.ticketsId = ticketsId;
        this.movieSessionsId = movieSessionsId;
        this.titles = titles;
        this.showTimes = showTimes;
        this.cinemaHallsId = cinemaHallsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(List<Long> ticketsId) {
        this.ticketsId = ticketsId;
    }

    public List<Long> getMovieSessionsId() {
        return movieSessionsId;
    }

    public void setMovieSessionsId(List<Long> movieSessionsId) {
        this.movieSessionsId = movieSessionsId;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<LocalDateTime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<LocalDateTime> showTimes) {
        this.showTimes = showTimes;
    }

    public List<Long> getCinemaHallsId() {
        return cinemaHallsId;
    }

    public void setCinemaHallsId(List<Long> cinemaHallsId) {
        this.cinemaHallsId = cinemaHallsId;
    }
}
