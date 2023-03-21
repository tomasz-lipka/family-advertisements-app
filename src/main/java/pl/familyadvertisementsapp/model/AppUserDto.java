package pl.familyadvertisementsapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class AppUserDto {

    private String username;
    private char[] password;

    public void clearPassword() {
        Arrays.fill(this.password, '0');
    }
}