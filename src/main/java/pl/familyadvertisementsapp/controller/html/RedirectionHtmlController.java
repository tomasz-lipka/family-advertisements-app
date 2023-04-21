package pl.familyadvertisementsapp.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Redirects the user from "/" URL to the home page of the application.
 *
 * @author Tomasz Lipka
 */
@Controller
public class RedirectionHtmlController {

    @GetMapping("/")
    public ModelAndView redirect() {
        return new ModelAndView("redirect:/advertisements/all");
    }
}
