package pl.familyadvertisementsapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

/**
 * Data transfer (form transfer) class.
 * Allows to retrieve the credentials during registration process and pass them into the destination controller method.
 *
 * @author Tomasz Lipka
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDto {

    //TODO temporarily disable password and username validation -> then enable; also in HTML!!!
    @NotNull
//    @Size(min = 3, max = 10, message = "Username must have at least 3 and a maximum of 10 characters.")
    @NotBlank(message = "Username may not be blank")
    private String username;

    @NotNull
//    @ValidPassword
    private char[] password;

    public void clearPassword() {
        Arrays.fill(this.password, '0');
    }
}