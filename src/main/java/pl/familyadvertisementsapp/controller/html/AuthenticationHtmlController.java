package pl.familyadvertisementsapp.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the login view. Returns the authentication form view.
 *
 * @author Tomasz Lipka
 */
@Controller
public class AuthenticationHtmlController {

    @GetMapping("/authentication")
    public ModelAndView getLoginView() {
        return new ModelAndView("unlogged/login");
    }
}