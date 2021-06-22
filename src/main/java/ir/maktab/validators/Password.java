package ir.maktab.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author : Bahar Zolfaghari
 **/
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({
        ElementType.FIELD,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "Invalid format, The password must be at least 8 and contain letters, numbers and special characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
