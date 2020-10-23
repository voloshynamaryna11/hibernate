package cinema.model.dto;

import java.time.LocalDateTime;

public class MovieSessionResponseDto {
    private Long movieId;
    private Long cinemaHallId;
    private LocalDateTime showTime;
    private String movieTitle;

    public MovieSessionResponseDto(Long cinemaHallId,
                                   LocalDateTime showTime, String movieTitle) {
        this.cinemaHallId = cinemaHallId;
        this.showTime = showTime;
        this.movieTitle = movieTitle;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}
