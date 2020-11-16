package cinema.security;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InjectingData {
    private UserService userService;
    private RoleService roleService;

    public InjectingData(UserService userService,
                         RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        User administrator = new User();
        administrator.setEmail("admin@gmail.com");
        administrator.setPassword("1");
        Role admin = Role.of("ADMIN");
        roleService.add(admin);
        Role user = Role.of("USER");
        roleService.add(user);
        administrator.setRoles(Set.of(admin));
        userService.add(administrator);
    }
}
