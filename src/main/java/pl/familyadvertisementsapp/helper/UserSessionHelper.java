package pl.familyadvertisementsapp.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * This class provides methods to retrieve the currently logged-in users username and validate this username against a given one.
 *
 * @author Tomasz Lipka
 */
@Service
public class UserSessionHelper {

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public boolean matchesLoggedInUsername(String username) {
        return username.equals(getLoggedInUsername());
    }
}
