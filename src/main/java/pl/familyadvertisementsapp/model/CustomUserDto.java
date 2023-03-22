package pl.familyadvertisementsapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pl.familyadvertisementsapp.validator.ValidPassword;

import java.util.Arrays;

@Getter
@Setter
public class CustomUserDto {

    @NotNull
    @Size(min = 3, max = 10, message = "Must have at least 3 and maximum of 10 characters.")
    @NotBlank(message = "May not be blank.")
    private String username;

    @NotNull
    @ValidPassword
    private char[] password;

    public void clearPassword() {
        Arrays.fill(this.password, '0');
    }
}