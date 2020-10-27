package cinema.mappers;

import cinema.model.ShoppingCart;
import cinema.model.dto.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapFromShoppingCartToShoppingCartResponseDto(
            ShoppingCart shoppingCart) {
        return new ShoppingCartResponseDto(shoppingCart.getId(),
                shoppingCart.getUser().getId(), shoppingCart.getUser().getEmail(),
                shoppingCart.getTickets().stream()
                        .map(ticket -> ticket.getId())
                        .collect(Collectors.toList()),
                shoppingCart.getTickets().stream()
                        .map(ticket -> ticket.getMovieSession().getId())
                        .collect(Collectors.toList()),
                shoppingCart.getTickets().stream()
                        .map(ticket -> ticket.getMovieSession().getMovie().getTitle())
                        .collect(Collectors.toList()),
                shoppingCart.getTickets().stream()
                        .map(ticket -> ticket.getMovieSession().getShowTime())
                        .collect(Collectors.toList()),
                shoppingCart.getTickets().stream()
                        .map(ticket -> ticket.getMovieSession().getCinemaHall().getId())
                        .collect(Collectors.toList()));
    }
}
