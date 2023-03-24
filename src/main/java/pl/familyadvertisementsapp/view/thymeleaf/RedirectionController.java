package pl.familyadvertisementsapp.view.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {

    @GetMapping("/")
    public String redirect() {
        return "redirect:/logged/advertisements/all";
    }
}