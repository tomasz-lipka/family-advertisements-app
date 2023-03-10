package pl.familyadvertisementsapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.model.AppUser;
import pl.familyadvertisementsapp.repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    public void createUser(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser appUser = userRepository.findByUsername(username);
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
}