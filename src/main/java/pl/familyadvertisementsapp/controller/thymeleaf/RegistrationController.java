package pl.familyadvertisementsapp.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.exception.AppUserServiceException;
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
    public String getRegistrationView(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appuser", appUser);
        return "registration";
    }

    @PostMapping()
    //TODO is ModelAttribute safe for passwords?
    public String createAppUser(@ModelAttribute AppUser appUser, Model model) {
        model.addAttribute("appuser", appUser);
        try {
            appUserService.createAppUser(appUser);
        } catch (AppUserServiceException e) {
            return "redirect:/registration?error";
        }
        return "redirect:/registration?success";
    }
}