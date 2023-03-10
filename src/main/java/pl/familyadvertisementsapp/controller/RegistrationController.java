package pl.familyadvertisementsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.model.AppUser;
import pl.familyadvertisementsapp.service.AppUserService;

//TODO @RestController or add  @ResponseBody to methods
@Controller
public class RegistrationController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appuser", appUser);
        return "register";
    }

    @PostMapping("/register")
    //TODO RequestBody instead of ModelAtrribute?
    //TODO is ModelAttribute safe for passwords?
    public void registerUserAccount(@ModelAttribute AppUser appUser, Model model) {
        model.addAttribute("appuser", appUser);
        userService.createUser(appUser);
    }
}