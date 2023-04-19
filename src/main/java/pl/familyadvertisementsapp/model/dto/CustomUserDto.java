package pl.familyadvertisementsapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.familyadvertisementsapp.validator.ValidPassword;

import java.util.Arrays;

/**
 * Data transfer (form transfer) class.
 * Allows to retrieve the credentials during registration process and pass them into the destination controller method.
 * Enforces validation of the password and username fields.
 *
 * @author Tomasz Lipka
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDto {

    @NotNull
    @Size(min = 3, max = 10, message = "Username must have at least 3 and a maximum of 10 characters.")
    @NotBlank(message = "Username may not be blank")
    private String username;

    @NotNull
    @ValidPassword
    private char[] password;

    public void clearPassword() {
        Arrays.fill(this.password, '0');
    }
}