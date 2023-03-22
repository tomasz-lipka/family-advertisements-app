package pl.familyadvertisementsapp.controller.thymeleaf;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    //TODO split method
    public String handleError(HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", "Sorry, something went wrong.");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "Sorry, the page you're looking for doesn't exist.");
            }
            if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                model.addAttribute("errorMessage", "Sorry, this request is not supported.");
            }
        }
        return "logged/error";
    }

     String getErrorView(Model model, String exceptionMessage) {
        model.addAttribute("errorMessage", exceptionMessage);
        return "logged/error";
    }
}
