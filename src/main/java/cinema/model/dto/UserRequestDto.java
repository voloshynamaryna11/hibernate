package cinema.model.dto;

import cinema.annotations.EmailConstraint;
import cinema.annotations.PasswordConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordConstraint
public class UserRequestDto {
    @NotNull(message = "Email must be specified")
    @EmailConstraint
    private String email;
    @NotNull(message = "Password must be specified")
    @Size(min = 5, message = "Password has to have at least 5 characters")
    private String password;
    @NotNull(message = "Repeating password must be specified")
    @Size(min = 5, message = "Repeating password has to have at least 5 characters")
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
