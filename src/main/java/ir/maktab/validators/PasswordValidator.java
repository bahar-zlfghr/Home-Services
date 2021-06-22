package ir.maktab.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : Bahar Zolfaghari
 **/
public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public void initialize(Password password) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) {
            return false;
        }
        else {
            return password.matches(
                    "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}");
        }
    }
}
