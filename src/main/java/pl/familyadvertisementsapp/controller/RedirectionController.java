package pl.familyadvertisementsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Redirects the user from "/" URL to the home page of the application.
 *
 * @author Tomasz Lipka
 */
@Controller
public class RedirectionController {

    @GetMapping("/")
    public String redirect() {
        return "redirect:/advertisements/all";
    }
}
