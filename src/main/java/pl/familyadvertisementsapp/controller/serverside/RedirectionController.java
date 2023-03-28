package pl.familyadvertisementsapp.controller.serverside;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {

    @GetMapping("/")
    public String redirect() {
        return "redirect:/advertisements/all";
    }
}