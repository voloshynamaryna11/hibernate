package cinema.controllers;

import cinema.mappers.ShoppingCartMapper;
import cinema.model.dto.ShoppingCartResponseDto;
import cinema.service.MovieSessionService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public void add(Authentication authentication, @RequestParam Long movieSessionId) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        shoppingCartService.addSession(sessionService
                .get(movieSessionId), userService.findByEmail(principal
                .getUsername()).get());
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return mapper.mapFromShoppingCartToShoppingCartResponseDto(
                shoppingCartService.getByUser(userService.findByEmail(principal
                        .getUsername()).get()));
    }
}
