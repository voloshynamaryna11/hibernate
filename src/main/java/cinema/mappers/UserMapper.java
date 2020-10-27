package cinema.mappers;

import cinema.model.User;
import cinema.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapFromUserToUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getEmail());
    }
}
