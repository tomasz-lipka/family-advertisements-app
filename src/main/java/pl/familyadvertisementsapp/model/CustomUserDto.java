package pl.familyadvertisementsapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
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