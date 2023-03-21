package pl.familyadvertisementsapp.controller.thymeleaf;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.exception.AppUserServiceException;
import pl.familyadvertisementsapp.model.AppUser;
import pl.familyadvertisementsapp.model.AppUserDto;
import pl.familyadvertisementsapp.security.SecurityConfiguration;
import pl.familyadvertisementsapp.service.AppUserService;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    private AppUserService appUserService;
    private SecurityConfiguration securityConfiguration;

    public RegistrationController(AppUserService userService, SecurityConfiguration securityConfiguration) {
        this.appUserService = userService;
        this.securityConfiguration = securityConfiguration;
    }

    @GetMapping()
    public String getRegistrationView(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appuser", appUser);
        return "registration";
    }

    //TODO split method
    @PostMapping()
    public String createAppUser(@ModelAttribute AppUserDto appUserDto, Model model) {
        model.addAttribute("appuser", appUserDto);
        try {
            PasswordEncoder passwordEncoder = securityConfiguration.encoder();
            char[] password = appUserDto.getPassword();
            String encodedPassword = passwordEncoder.encode(java.nio.CharBuffer.wrap(password));

            appUserService.createAppUser(appUserDto.getUsername(), encodedPassword);

            appUserDto.clearPassword();

        } catch (AppUserServiceException e) {
            return "redirect:/registration?error";
        }
        return "redirect:/registration?success";
    }
}