package pl.familyadvertisementsapp.controller.html;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomHtmlErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("infoMessage", "The page you're looking for doesn't exist");
            } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                model.addAttribute("infoMessage", "This request is not supported");
            } else {
                model.addAttribute("errorMessage", "Sorry, something went wrong");
            }
        } else {
            model.addAttribute("errorMessage", "Sorry, something went wrong");
        }
        return "logged/blank";
    }

    ModelAndView getErrorView(String exceptionMessage) {
        ModelAndView mav = new ModelAndView("logged/blank");
        mav.addObject("infoMessage", exceptionMessage);
        return mav;
    }
}
