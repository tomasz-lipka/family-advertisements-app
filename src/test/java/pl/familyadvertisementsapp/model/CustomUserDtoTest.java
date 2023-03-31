package pl.familyadvertisementsapp.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests of the custom user dto class.
 *
 * @author Tomasz Lipka
 */
class CustomUserDtoTest {

    @Test
    void shouldClearPassword() {
        var customUserDto = new CustomUserDto("user", new char[]{'a', 's', 'd', '1', '2', '3', '!', '@', '#'});
        customUserDto.clearPassword();
        assertEquals(
                Arrays.toString(new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0'}),
                Arrays.toString(customUserDto.getPassword())
        );
    }
}