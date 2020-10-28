package cinema.controllers;

import cinema.mappers.ShoppingCartMapper;
import cinema.model.dto.ShoppingCartResponseDto;
import cinema.service.MovieSessionService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private ShoppingCartMapper mapper;
    private MovieSessionService sessionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService, ShoppingCartMapper mapper,
                                  MovieSessionService sessionService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.mapper = mapper;
        this.sessionService = sessionService;
    }

    @PostMapping("/movie-sessions")
    public void add(@RequestParam Long userId, @RequestParam Long movieSessionId) {
        shoppingCartService.addSession(sessionService
                .get(movieSessionId), userService.get(userId));
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        return mapper.mapFromShoppingCartToShoppingCartResponseDto(
                shoppingCartService.getByUser(userService.get(userId)));
    }
}
