package pl.familyadvertisementsapp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator
        implements ConstraintValidator<ValidPassword, char[]> {

    private Pattern pattern;
    private Matcher matcher;

    private static final String AT_LEAST_ONE_ENGLISH_LETTER = "(?=.*?[A-Za-z])";
    private static final String AT_LEAST_ONE_DIGIT = "(?=.*?[0-9])";
    private static final String MINIMUM_LENGTH = ".{3,}";
    private static final String PASSWORD_PATTERN =
            AT_LEAST_ONE_ENGLISH_LETTER
                    + AT_LEAST_ONE_DIGIT
                    + MINIMUM_LENGTH;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(char[] password, ConstraintValidatorContext constraintValidatorContext) {
        return (validatePassword(password));
    }

    private boolean validatePassword(char[] password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(CharBuffer.wrap(password));
        return matcher.matches();
    }
}