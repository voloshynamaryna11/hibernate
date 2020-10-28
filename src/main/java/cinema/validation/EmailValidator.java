package cinema.validation;

import cinema.annotations.EmailConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\\\+]"
            + "+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)"
            + "*(\\\\.[A-Za-z]{2,})$";

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext constraintValidatorContext) {
        return email.length() > 8 && email.length() < 50
                && email.matches(EMAIL_PATTERN);
    }
}
