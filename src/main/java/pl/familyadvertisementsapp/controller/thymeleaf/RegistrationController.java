package pl.familyadvertisementsapp.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.model.AppUser;
import pl.familyadvertisementsapp.service.AppUserService;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    private AppUserService appUserService;

    public RegistrationController(AppUserService userService) {
        this.appUserService = userService;
    }

    @GetMapping()
    public String getRegister(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appuser", appUser);
        return "register";
    }

    @PostMapping()
    //TODO is ModelAttribute safe for passwords?
    public String createAppUser(@ModelAttribute AppUser appUser, Model model) {
        System.out.println("crete");
        model.addAttribute("appuser", appUser);
        appUserService.createAppUser(appUser);
        //TODO validate success
        //TODO message
        return "login";
    }
}