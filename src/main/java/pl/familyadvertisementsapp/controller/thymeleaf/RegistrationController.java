package pl.familyadvertisementsapp.controller.thymeleaf;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class RegistrationController {

    private AppUserService appUserService;
    private SecurityConfiguration securityConfiguration;

    @GetMapping()
    public String getRegistrationView(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appuser", appUser);
        return "authentication/registration";
    }

    //TODO split method
    //TODO what is @ModelAtrributet for
    @PostMapping()
    public String createAppUser(@ModelAttribute AppUserDto appUserDto, Model model) {
        System.out.println("weqwe");
        try {
            model.addAttribute("appuser", appUserDto);

            PasswordEncoder passwordEncoder = securityConfiguration.encoder();
            char[] password = appUserDto.getPassword();
            String encodedPassword = passwordEncoder.encode(java.nio.CharBuffer.wrap(password));

            appUserService.createAppUser(appUserDto.getUsername(), encodedPassword);

            appUserDto.clearPassword();

        } catch (AppUserServiceException e) {
            return "redirect:/registration?exists";
        }
        return "redirect:/registration?success";
    }
}