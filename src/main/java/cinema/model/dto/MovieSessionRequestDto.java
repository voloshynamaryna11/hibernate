package cinema.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class MovieSessionRequestDto {
    @Min(value = 1, message = "Movie id should be greater than 0")
    private Long movieId;
    @Min(value = 1, message = "CinemaHall id should be greater than 0")
    private Long cinemaHallId;
    @NotNull(message = "Data and time have to be specified")
    @Future(message = "Data and time can not be now or in the past")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime showTime;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
