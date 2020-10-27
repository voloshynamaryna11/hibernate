package cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long orderId;
    private List<Long> ticketsId;
    private List<String> moviesTitles;
    private List<LocalDateTime> movieSessionsShowTime;
    private List<Long> cinemaHallsId;
    private Long userId;
    private LocalDateTime orderDateTime;

    public OrderResponseDto(Long orderId, List<Long> ticketsId,
                            List<String> moviesTitles,
                            List<LocalDateTime> movieSessionsShowTime,
                            List<Long> cinemaHallsId, Long userId,
                            LocalDateTime orderDateTime) {
        this.orderId = orderId;
        this.ticketsId = ticketsId;
        this.moviesTitles = moviesTitles;
        this.movieSessionsShowTime = movieSessionsShowTime;
        this.cinemaHallsId = cinemaHallsId;
        this.userId = userId;
        this.orderDateTime = orderDateTime;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Long> getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(List<Long> ticketsId) {
        this.ticketsId = ticketsId;
    }

    public List<String> getMoviesTitles() {
        return moviesTitles;
    }

    public void setMoviesTitles(List<String> moviesTitles) {
        this.moviesTitles = moviesTitles;
    }

    public List<LocalDateTime> getMovieSessionsShowTime() {
        return movieSessionsShowTime;
    }

    public void setMovieSessionsShowTime(List<LocalDateTime> movieSessionsShowTime) {
        this.movieSessionsShowTime = movieSessionsShowTime;
    }

    public List<Long> getCinemaHallsId() {
        return cinemaHallsId;
    }

    public void setCinemaHallsId(List<Long> cinemaHallsId) {
        this.cinemaHallsId = cinemaHallsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
