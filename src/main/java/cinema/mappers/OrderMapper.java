package cinema.mappers;

import cinema.model.Order;
import cinema.model.Ticket;
import cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDto mapFromOrderToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setTicketsId(order.getTickets().stream()
                .map(Ticket::getId).collect(Collectors.toList()));
        orderResponseDto.setOrderDateTime(order.getOrderDate());
        return orderResponseDto;
    }
}
