package pl.familyadvertisementsapp.controller.html;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.familyadvertisementsapp.controller.base.RegistrationAbstractController;
import pl.familyadvertisementsapp.exception.CustomUserServiceException;
import pl.familyadvertisementsapp.model.dto.CustomUserDto;
import pl.familyadvertisementsapp.service.customuser.CustomUserService;

@Controller
@RequestMapping("registration")
@AllArgsConstructor
public class RegistrationHtmlController extends RegistrationAbstractController {

    private CustomUserService customUserService;

    @GetMapping()
    public ModelAndView getRegistrationView() {
        CustomUserDto customUserDto = new CustomUserDto();
        ModelAndView mav = new ModelAndView("unlogged/registration");
        mav.addObject("customUserDto", customUserDto);
        return mav;
    }

    @Override
    public ModelAndView createCustomUser(@Valid CustomUserDto customUserDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("unlogged/registration");
        }
        try {
            customUserService.createUserWithEncodedPassword(customUserDto);
            return new ModelAndView("redirect:/authentication?created");
        } catch (CustomUserServiceException e) {
            return new ModelAndView("redirect:/registration?exists");
        }
    }
}