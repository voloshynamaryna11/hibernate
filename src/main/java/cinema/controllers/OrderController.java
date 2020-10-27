package cinema.controllers;

import cinema.mappers.OrderMapper;
import cinema.model.dto.OrderRequestDto;
import cinema.model.dto.OrderResponseDto;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    OrderService orderService;
    OrderMapper orderMapper;
    UserService userService;
    ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper, UserService userService,
                           ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/complete")
    public void add(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.completeOrder(orderMapper
                        .mapFromOrderRequestDtoToTicketList(orderRequestDto,
                                userService, shoppingCartService),
                orderMapper.mapFromOrderRequestDtoToUser(orderRequestDto,
                        userService));
    }

    @GetMapping("/{userId}")
    public List<OrderResponseDto> getByUserId(@PathVariable Long userId) {
        return orderService.getOrderHistory(userService.findById(userId)).stream()
                .map(order -> orderMapper.mapFromOrderToOrderResponseDto(order))
                .collect(Collectors.toList());
    }
}
