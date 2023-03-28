package pl.familyadvertisementsapp.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * ValidPassword annotation to annotate the user password field.
 * Used to check if the assigned password meets requirements defined in the validator class.
 *
 * @author Tomasz Lipka
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface ValidPassword {

    String message() default "Invalid password format.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}