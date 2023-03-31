package pl.familyadvertisementsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the login view. Returns the authentication form view.
 *
 * @author Tomasz Lipka
 */
@Controller
public class AuthenticationController {

    @GetMapping("/authentication")
    public String getLoginView() {
        return "unlogged/login";
    }
}