package cinema.security;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectingData {
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public InjectingData(UserService userService,
                         RoleService roleService,
                         PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        User administrator = new User();
        administrator.setEmail("admin@gmail.com");
        administrator.setPassword("1");
        Role admin = new Role();
        admin.setRoleName(Role.RoleName.ADMIN);
        roleService.add(admin);
        Role user = new Role();
        user.setRoleName(Role.RoleName.USER);
        roleService.add(user);
        administrator.setRoles(Set.of(admin));
        userService.add(administrator, passwordEncoder);
    }
}
