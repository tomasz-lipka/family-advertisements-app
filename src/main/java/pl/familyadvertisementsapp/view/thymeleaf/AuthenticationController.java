package pl.familyadvertisementsapp.view.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/authentication")
    public String getLoginView() {
        return "unlogged/login";
    }
}