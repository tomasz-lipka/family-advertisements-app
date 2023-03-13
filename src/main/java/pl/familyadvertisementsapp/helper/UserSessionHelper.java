package pl.familyadvertisementsapp.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.model.AppUser;
import pl.familyadvertisementsapp.repository.AppUserRepository;

@Service
public class UserSessionHelper {

    private AppUserRepository appUserRepository;

    @Autowired
    public UserSessionHelper(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    private String getCurrentPrincipalName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public AppUser getCurrentLoggedAppUser() {
        String username = getCurrentPrincipalName();
        return appUserRepository.findByUsername(username);
    }
}
