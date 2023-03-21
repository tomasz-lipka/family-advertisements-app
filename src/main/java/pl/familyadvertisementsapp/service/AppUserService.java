package pl.familyadvertisementsapp.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.exception.AppUserServiceException;
import pl.familyadvertisementsapp.model.AppUser;
import pl.familyadvertisementsapp.repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {

    private AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void createAppUser(AppUser appUser) throws AppUserServiceException {
        if (!userExists(appUser.getUsername())) {
            appUserRepository.save(appUser);
        } else {
            throw new AppUserServiceException("AppUser with that username already exists.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
        return User.builder()
                .username(appUser.getUsername())
                //TODO encode password
                .password("{noop}" + appUser.getPassword())
                //TODO not hardcode role
                .roles("USER")
                .build();
    }

    private boolean userExists(String username) {
        return appUserRepository.findByUsername(username) != null;
    }
}