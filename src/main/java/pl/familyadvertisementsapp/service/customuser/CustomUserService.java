package pl.familyadvertisementsapp.service.customuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.exception.CustomUserServiceException;
import pl.familyadvertisementsapp.model.CustomUser;
import pl.familyadvertisementsapp.repository.CustomUserRepository;

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

    public void create(String username, String password) throws CustomUserServiceException {
        if (isUsernameAvailable(username)) {
            CustomUser customUser = new CustomUser(username, password);
            customUserRepository.save(customUser);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        CustomUser customUser = customUserRepository.findByUsername(username);
        return buildUser(customUser);
    }

    private boolean isUsernameAvailable(String username) throws CustomUserServiceException {
        if (customUserRepository.findByUsername(username) != null) {
            throw new CustomUserServiceException("CustomUser with that username already exists");
        }
        return true;
    }

    private UserDetails buildUser(CustomUser customUser) {
        return User.builder()
                .username(customUser.getUsername())
                .password(customUser.getPassword())
                .roles(DEFAULT_ROLE)
                .build();
    }
}