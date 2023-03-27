package pl.familyadvertisementsapp.view.serverside.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.familyadvertisementsapp.configuration.SecurityConfiguration;
import pl.familyadvertisementsapp.exception.AppUserServiceException;
import pl.familyadvertisementsapp.model.CustomUserDto;
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
        return "unlogged/registration";
    }

    @PostMapping()
    public String createAppUser(@Valid CustomUserDto customUserDto, BindingResult result) {
        if (result.hasErrors()) {
            return "unlogged/registration";
        }
        try {
            createUserWithEncodedPassword(customUserDto);
            customUserDto.clearPassword();
        } catch (AppUserServiceException e) {
            return "redirect:/registration?exists";
        }
        return "redirect:/authentication?created";
    }

    private void createUserWithEncodedPassword(CustomUserDto customUserDto) throws AppUserServiceException {
        char[] charPassword = customUserDto.getPassword();
        String encodedPassword = getEncodedPassword(charPassword);
        String username = customUserDto.getUsername();
        appUserService.createAppUser(username, encodedPassword);
    }

    private String getEncodedPassword(char[] charPassword) {
        PasswordEncoder passwordEncoder = securityConfiguration.encoder();
        return passwordEncoder.encode(CharBuffer.wrap(charPassword));
    }
}