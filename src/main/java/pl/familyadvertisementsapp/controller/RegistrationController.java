package pl.familyadvertisementsapp.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.configuration.UserConfiguration;
import pl.familyadvertisementsapp.model.UserDto;

@Controller
public class RegistrationController {

    @Autowired
    UserConfiguration userConfiguration;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    //TODO is ModelAttribute safe for passwords?
    @PostMapping("/register")
    public void registerUserAccount(@ModelAttribute UserDto userDto, Model model) {
        model.addAttribute("user", userDto);
        System.out.println(userDto.getLogin());
        System.out.println(userDto.getPassword());
        System.out.println(userDto.getMatchingPassword());

    }
}
