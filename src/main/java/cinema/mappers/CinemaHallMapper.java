package cinema.mappers;

import cinema.model.CinemaHall;
import cinema.model.dto.CinemaHallRequestDto;
import cinema.model.dto.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    public CinemaHall mapFromCinemaHallRequestDtoToCinemaHall(
            CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return cinemaHall;
    }

    public CinemaHallResponseDto mapFromCinemaHallToCinemaHallResponseDto(
            CinemaHall cinemaHall) {
        return new CinemaHallResponseDto(cinemaHall.getId(),
                cinemaHall.getCapacity(), cinemaHall.getDescription());
    }
}
