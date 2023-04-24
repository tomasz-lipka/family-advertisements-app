package pl.familyadvertisementsapp.service.customuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.configuration.SecurityConfiguration;
import pl.familyadvertisementsapp.exception.CustomUserServiceException;
import pl.familyadvertisementsapp.model.CustomUser;
import pl.familyadvertisementsapp.model.dto.CustomUserDto;
import pl.familyadvertisementsapp.repository.CustomUserRepository;

import java.nio.CharBuffer;

/**
 * This class manages the user creation (registration) process.
 * It loads also the user from the persistence layer during the authentication process.
 *
 * @author Tomasz Lipka
 */
@Service
@AllArgsConstructor
public class CustomUserService implements UserDetailsService {

    private static final String DEFAULT_ROLE = "USER";
    private final CustomUserRepository customUserRepository;
    private SecurityConfiguration securityConfiguration;

    @Override
    public UserDetails loadUserByUsername(String username) {
        CustomUser customUser = customUserRepository.findByUsername(username);
        return buildUser(customUser);
    }

    private UserDetails buildUser(CustomUser customUser) {
        return User.builder()
                .username(customUser.getUsername())
                .password(customUser.getPassword())
                .roles(DEFAULT_ROLE)
                .build();
    }

    public void createUserWithEncodedPassword(CustomUserDto customUserDto) throws CustomUserServiceException {
        char[] charPassword = customUserDto.getPassword();
        String encodedPassword = encodePassword(charPassword);
        String username = customUserDto.getUsername();
        create(username, encodedPassword);
        customUserDto.clearPassword();
    }

    private String encodePassword(char[] charPassword) {
        PasswordEncoder passwordEncoder = securityConfiguration.encoder();
        return passwordEncoder.encode(CharBuffer.wrap(charPassword));
    }

    private void create(String username, String password) throws CustomUserServiceException {
        if (isUsernameAvailableOrElseThrow(username)) {
            CustomUser customUser = new CustomUser(username, password);
            customUserRepository.save(customUser);
        }
    }

    private boolean isUsernameAvailableOrElseThrow(String username) throws CustomUserServiceException {
        if (customUserRepository.findByUsername(username) != null) {
            throw new CustomUserServiceException("CustomUser with that username already exists");
        }
        return true;
    }
}