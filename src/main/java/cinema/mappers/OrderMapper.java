package cinema.mappers;

import cinema.model.Order;
import cinema.model.Ticket;
import cinema.model.User;
import cinema.model.dto.OrderRequestDto;
import cinema.model.dto.OrderResponseDto;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public List<Ticket> mapFromOrderRequestDtoToTicketList(
            OrderRequestDto orderRequestDto, UserService userService,
            ShoppingCartService shoppingCartService) {
        return shoppingCartService.getByUser(userService
                .findById(orderRequestDto.getUserId())).getTickets();
    }

    public User mapFromOrderRequestDtoToUser(OrderRequestDto orderRequestDto,
                                             UserService userService) {
        return userService.findById(orderRequestDto.getUserId());
    }

    public OrderResponseDto mapFromOrderToOrderResponseDto(Order order) {
        return new OrderResponseDto(order.getId(), order.getTickets().stream()
                .map(ticket -> ticket.getId()).collect(Collectors.toList()),
                order.getTickets().stream()
                        .map(ticket -> ticket.getMovieSession().getMovie().getTitle())
                        .collect(Collectors.toList()),
                order.getTickets().stream()
                        .map(ticket -> ticket.getMovieSession().getShowTime())
                        .collect(Collectors.toList()),
                order.getTickets().stream()
                        .map(ticket -> ticket.getMovieSession().getCinemaHall().getId())
                        .collect(Collectors.toList()),
                order.getUser().getId(), order.getOrderDate());
    }
}
