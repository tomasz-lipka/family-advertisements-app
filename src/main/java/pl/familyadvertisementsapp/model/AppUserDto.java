package pl.familyadvertisementsapp.model;

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