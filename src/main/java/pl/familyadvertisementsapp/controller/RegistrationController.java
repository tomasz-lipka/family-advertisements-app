package pl.familyadvertisementsapp.controller;

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
import pl.familyadvertisementsapp.exception.CustomUserServiceException;
import pl.familyadvertisementsapp.model.dto.CustomUserDto;
import pl.familyadvertisementsapp.service.customuser.CustomUserService;

import java.nio.CharBuffer;

@Controller
@RequestMapping("registration")
@AllArgsConstructor
public class RegistrationController {

    private CustomUserService customUserService;
    private SecurityConfiguration securityConfiguration;

    @GetMapping()
    public String getRegistrationView(Model model) {
        CustomUserDto customUserDto = new CustomUserDto();
        model.addAttribute("customUserDto", customUserDto);
        return "unlogged/registration";
    }

    @PostMapping()
    public String createCustomUser(@Valid CustomUserDto customUserDto, BindingResult result) {
        if (result.hasErrors()) {
            return "unlogged/registration";
        }
        return createUserWithEncodedPassword(customUserDto);
    }

    private String createUserWithEncodedPassword(CustomUserDto customUserDto) {
        char[] charPassword = customUserDto.getPassword();
        String encodedPassword = getEncodedPassword(charPassword);
        String username = customUserDto.getUsername();
        try {
            customUserService.create(username, encodedPassword);
            customUserDto.clearPassword();
        } catch (CustomUserServiceException e) {
            return "redirect:/registration?exists";
        }
        return "redirect:/authentication?created";
    }

    private String getEncodedPassword(char[] charPassword) {
        PasswordEncoder passwordEncoder = securityConfiguration.encoder();
        return passwordEncoder.encode(CharBuffer.wrap(charPassword));
    }
}