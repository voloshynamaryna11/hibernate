package cinema.service;

import cinema.model.User;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    User add(User user, PasswordEncoder passwordEncoder);

    Optional<User> findByEmail(String email);

    User get(Long id);
}
