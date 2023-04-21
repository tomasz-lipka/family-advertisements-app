package pl.familyadvertisementsapp.controller.html;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.familyadvertisementsapp.controller.base.AdvertisementBaseController;
import pl.familyadvertisementsapp.exception.AdvertisementServiceException;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.advertisement.AdvertisementService;

import java.util.Collection;
import java.util.List;

//TODO desc "thymeleaf" for all controllers

/**
 * Handles requests regarding the advertisement entities.
 * Returns desired views, adds data to the model and accepts incoming data.
 *
 * @author Tomasz Lipka
 */
@Controller
@RequestMapping("/advertisements")
@AllArgsConstructor
public class AdvertisementHtmlController extends AdvertisementBaseController {

    private final CustomHtmlErrorController customHtmlErrorController;
    private final AdvertisementService advertisementService;

    @Override
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("logged/advertisements");
        List<Advertisement> advertisements = advertisementService.getAll();
        mav.addObject("advertisements", advertisements);
        mav.addObject("selectedMenuPage", MenuPage.ALL.toString());
        return mav;
    }

    @Override
    public ModelAndView getMy() {
        ModelAndView mav = new ModelAndView("logged/advertisements");
        Collection<Advertisement> advertisements = advertisementService.getOwned();
        mav.addObject("advertisements", advertisements);
        mav.addObject("selectedMenuPage", MenuPage.MY.toString());
        return mav;
    }

    @GetMapping("/creator")
    public ModelAndView getCreatorView() {
        ModelAndView mav = new ModelAndView("logged/creator-editor");
        Advertisement advertisement = new Advertisement();
        mav.addObject("advertisement", advertisement);
        mav.addObject("restMethod", "POST");
        mav.addObject("selectedMenuPage", MenuPage.CREATOR.toString());
        return mav;
    }

    @Override
    public ModelAndView create(@Valid Advertisement advertisement, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("logged/creator-editor");
            mav.addObject("restMethod", "POST");
            mav.addObject("selectedMenuPage", MenuPage.CREATOR.toString());
            return mav;
        }
        advertisementService.create(advertisement);
        return new ModelAndView("redirect:/advertisements/all?created");
    }

    @Override
    public ModelAndView delete(@PathVariable Long id) {
        try {
            advertisementService.deleteBy(id);
            return new ModelAndView("redirect:/advertisements/my?deleted");
        } catch (AdvertisementServiceException e) {
            return customHtmlErrorController.getErrorView(e.getMessage());
        }
    }

    @GetMapping("/{id}/editor")
    public ModelAndView getEditorView(@PathVariable Long id) {
        try {
            ModelAndView mav = new ModelAndView("logged/creator-editor");
            Advertisement advertisement = advertisementService.getBy(id);
            mav.addObject("advertisement", advertisement);
            mav.addObject("restMethod", "PUT");
            return mav;
        } catch (AdvertisementServiceException e) {
            return customHtmlErrorController.getErrorView(e.getMessage());
        }
    }

    @PutMapping()
    public ModelAndView update(@Valid Advertisement advertisement, BindingResult result) {
        try {
            if (result.hasErrors()) {
                ModelAndView mav = new ModelAndView("logged/creator-editor");
                mav.addObject("restMethod", "PUT");
                return mav;
            }
            ModelAndView mav = new ModelAndView("redirect:/advertisements/my?updated");
            advertisementService.update(advertisement);
            return mav;
        } catch (AdvertisementServiceException e) {
            return customHtmlErrorController.getErrorView(e.getMessage());
        }
    }
}

