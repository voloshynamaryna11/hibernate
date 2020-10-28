package cinema.controllers;

import cinema.mappers.OrderMapper;
import cinema.model.User;
import cinema.model.dto.OrderResponseDto;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private OrderMapper orderMapper;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper, UserService userService,
                           ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/complete")
    public void add(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(principal.getUsername()).get();
        orderService.completeOrder(shoppingCartService
                .getByUser(user)
                .getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getByUserId(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return orderService.getOrderHistory(userService.findByEmail(principal.getUsername())
                .get()).stream()
                .map(order -> orderMapper.mapFromOrderToOrderResponseDto(order))
                .collect(Collectors.toList());
    }
}
