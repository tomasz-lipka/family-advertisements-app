package pl.familyadvertisementsapp.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("selector")
public class TechnologySelectorHtmlController {

    @GetMapping()
    public ModelAndView getRegistrationView() {
        return new ModelAndView("logged/tech-selector");
    }

}
