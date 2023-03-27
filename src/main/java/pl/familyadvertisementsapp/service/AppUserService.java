package pl.familyadvertisementsapp.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.exception.AppUserServiceException;
import pl.familyadvertisementsapp.model.CustomUser;
import pl.familyadvertisementsapp.repository.AppUserRepository;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private static final String DEFAULT_ROLE = "USER";
    private AppUserRepository appUserRepository;

    //TODO split method
    public void createAppUser(String username, String password) throws AppUserServiceException {
        if (!userExists(username)) {
            CustomUser customUser = new CustomUser();
            customUser.setUsername(username);
            customUser.setPassword(password);
            appUserRepository.save(customUser);
        } else {
            throw new AppUserServiceException("AppUser with that username already exists.");
        }
    }

    //TODO split method
    @Override
    public UserDetails loadUserByUsername(String username) {
        CustomUser customUser = appUserRepository.findByUsername(username);
        if (customUser == null) {
            throw new UsernameNotFoundException("User with that username not found.");
        }
        return User.builder()
                .username(customUser.getUsername())
                .password(customUser.getPassword())
                .roles(DEFAULT_ROLE)
                .build();
    }

    private boolean userExists(String username) {
        return appUserRepository.findByUsername(username) != null;
    }
}