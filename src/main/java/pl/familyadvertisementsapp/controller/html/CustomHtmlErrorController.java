package pl.familyadvertisementsapp.controller.html;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the error page view. Overrides the default error page.
 *
 * @author Tomasz Lipka
 */
@Controller
public class CustomHtmlErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ModelAndView mav = new ModelAndView("logged/blank");
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                mav.addObject("infoMessage", "The page you're looking for doesn't exist");
            } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                mav.addObject("infoMessage", "This request is not supported");
            } else {
                mav.addObject("errorMessage", "Sorry, something went wrong");
            }
        } else {
            mav.addObject("errorMessage", "Sorry, something went wrong");
        }
        return mav;
    }

    ModelAndView getErrorView(String exceptionMessage) {
        ModelAndView mav = new ModelAndView("logged/blank");
        mav.addObject("infoMessage", exceptionMessage);
        return mav;
    }
}
