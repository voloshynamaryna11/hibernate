package cinema.mappers;

import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.dto.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapFromShoppingCartToShoppingCartResponseDto(
            ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setUserId(shoppingCart.getUser().getId());
        responseDto.setTicketsId(shoppingCart.getTickets().stream()
                .map(Ticket::getId).collect(Collectors.toList()));
        return responseDto;
    }
}
