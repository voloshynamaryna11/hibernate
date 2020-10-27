package cinema.controllers;

import cinema.mappers.ShoppingCartMapper;
import cinema.model.dto.ShoppingCartResponseDto;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private ShoppingCartMapper mapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService, ShoppingCartMapper mapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/movie-sessions/{userId}")
    public void add(@PathVariable Long userId) {
        shoppingCartService.registerNewShoppingCart(userService.findById(userId));
    }

    @GetMapping("/by-user/{userId}")
    public ShoppingCartResponseDto getByUser(@PathVariable Long userId) {
        return mapper.mapFromShoppingCartToShoppingCartResponseDto(
                shoppingCartService.getByUser(userService.findById(userId)));
    }
}
