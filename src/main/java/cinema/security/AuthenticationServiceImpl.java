package cinema.security;

import cinema.exceptions.AuthenticationException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && user.get().getPassword()
                .equals(HashUtil.hashPassword(password, user.get().getSalt()))) {
            return user.get();
        }
        throw new AuthenticationException("Wrong password or login");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
