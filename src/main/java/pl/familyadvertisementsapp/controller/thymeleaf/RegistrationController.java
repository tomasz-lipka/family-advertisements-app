package pl.familyadvertisementsapp.controller.thymeleaf;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.exception.AppUserServiceException;
import pl.familyadvertisementsapp.model.CustomUser;
import pl.familyadvertisementsapp.model.CustomUserDto;
import pl.familyadvertisementsapp.security.SecurityConfiguration;
import pl.familyadvertisementsapp.service.AppUserService;

import java.nio.CharBuffer;

@Controller
@RequestMapping("registration")
@AllArgsConstructor
public class RegistrationController {

    private AppUserService appUserService;
    private SecurityConfiguration securityConfiguration;

    @GetMapping()
    public String getRegistrationView(Model model) {
        CustomUserDto customUserDto = new CustomUserDto();
        model.addAttribute("customUserDto", customUserDto);
        return "authentication/registration";
    }

    //TODO split method
    @PostMapping()
    public String createAppUser(@Valid CustomUserDto customUserDto, BindingResult result) {
        if (result.hasErrors()) {
            return "authentication/registration";
        }
        try {
            PasswordEncoder passwordEncoder = securityConfiguration.encoder();
            char[] password = customUserDto.getPassword();
            String encodedPassword = passwordEncoder.encode(CharBuffer.wrap(password));
            appUserService.createAppUser(customUserDto.getUsername(), encodedPassword);
            customUserDto.clearPassword();
        } catch (AppUserServiceException e) {
            return "redirect:/registration?exists";
        }
        //TODO not working properly
        return "redirect:/authentication?success";
    }
}