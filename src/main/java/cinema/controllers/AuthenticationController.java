package cinema.controllers;

import cinema.model.dto.UserRequestDto;
import cinema.security.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequestDto userRequestDto) {
        if (userRequestDto.getPassword()
                .equals(userRequestDto.getRepeatPassword())) {
            authenticationService.register(userRequestDto.getEmail(),
                    userRequestDto.getPassword());
        }
    }
}
