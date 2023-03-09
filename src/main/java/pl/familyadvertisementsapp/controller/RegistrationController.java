package pl.familyadvertisementsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.model.UserDto;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    //TODO is it safe for passwords?
    @PostMapping("/register")
    public void registerUserAccount(@ModelAttribute UserDto userDto, Model model) {
        model.addAttribute("user", userDto);
        System.out.println(userDto.getLogin());
    }
}
