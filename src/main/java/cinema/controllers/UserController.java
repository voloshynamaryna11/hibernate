package cinema.controllers;

import cinema.mappers.UserMapper;
import cinema.model.dto.UserResponseDto;
import cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/by-email/{email}")
    public UserResponseDto getByEmail(@PathVariable String email) {
        return mapper.mapFromUserToUserResponseDto(userService
                .findByEmail(email).get());
    }
}
