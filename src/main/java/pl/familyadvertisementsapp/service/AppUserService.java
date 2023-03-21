package pl.familyadvertisementsapp.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.exception.AppUserServiceException;
import pl.familyadvertisementsapp.model.AppUser;
import pl.familyadvertisementsapp.repository.AppUserRepository;
import pl.familyadvertisementsapp.security.SecurityConfiguration;

@Service
public class AppUserService implements UserDetailsService {

    private static final String DEFAULT_ROLE = "USER";
    private AppUserRepository appUserRepository;
    private SecurityConfiguration securityConfiguration;

    public AppUserService(AppUserRepository appUserRepository, SecurityConfiguration securityConfiguration) {
        this.appUserRepository = appUserRepository;
        this.securityConfiguration = securityConfiguration;
    }

    //TODO split method
    public void createAppUser(AppUser appUserDto) throws AppUserServiceException {
        if (!userExists(appUserDto.getUsername())) {
            AppUser appUser = new AppUser();
            appUser.setUsername(appUserDto.getUsername());
            PasswordEncoder passwordEncoder = securityConfiguration.encoder();
            appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
            appUserRepository.save(appUser);
        } else {
            throw new AppUserServiceException("AppUser with that username already exists.");
        }
    }

    //TODO split method
    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User with that name not found.");
        }
//        PasswordEncoder passwordEncoder = securityConfiguration.encoder();
//        System.out.println("asdasdasd");
//
//        System.out.println(passwordEncoder.encode(appUser.getPassword()));
//        passwordEncoder.matches()


        return User.builder()
                .username(appUser.getUsername())
                //TODO encode password
//                .password("{noop}" + appUser.getPassword())
                .password(passwordEncoder.encode(appUser.getPassword()))
                .roles(DEFAULT_ROLE)
                .build();
    }

    private boolean userExists(String username) {
        return appUserRepository.findByUsername(username) != null;
    }
}