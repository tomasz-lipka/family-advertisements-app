package pl.familyadvertisementsapp.controller.serverside;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.exception.AdvertisementServiceException;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.advertisement.AdvertisementService;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/advertisements")
@AllArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final CustomErrorController customErrorController;

    @GetMapping("/all")
    public String getAllView(Model model) {
        List<Advertisement> advertisements = advertisementService.getAll();
        model.addAttribute("advertisements", advertisements);
        model.addAttribute("selectedMenuPage", MenuPage.ALL.toString());
        return "logged/advertisements";
    }

    @GetMapping("/my")
    public String getMyView(Model model) {
        Collection<Advertisement> advertisements = advertisementService.getOwned();
        model.addAttribute("advertisements", advertisements);
        model.addAttribute("selectedMenuPage", MenuPage.MY.toString());
        return "logged/advertisements";
    }

    @GetMapping("/creator")
    public String getCreatorView(Model model) {
        Advertisement advertisement = new Advertisement();
        model.addAttribute("advertisement", advertisement);
        model.addAttribute("restMethod", "POST");
        model.addAttribute("selectedMenuPage", MenuPage.CREATOR.toString());
        return "logged/creator-editor";
    }

    @PostMapping()
    public String create(@Valid Advertisement advertisement, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("restMethod", "POST");
            model.addAttribute("selectedMenuPage", MenuPage.CREATOR.toString());
            return "logged/creator-editor";
        }
        advertisementService.create(advertisement);
        return "redirect:/advertisements/all?created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            advertisementService.deleteBy(id);
            return "redirect:/advertisements/my?deleted";
        } catch (AdvertisementServiceException e) {
            return customErrorController.getErrorView(model, e.getMessage());
        }
//        String[] asd = {"asd"};
//        return asd[2];
    }

    @GetMapping("/{id}/editor")
    public String getEditorView(@PathVariable Long id, Model model) {
        try {
            Advertisement advertisement = advertisementService.getBy(id);
            model.addAttribute("advertisement", advertisement);
            model.addAttribute("restMethod", "PUT");
            return "logged/creator-editor";
        } catch (AdvertisementServiceException e) {
            return customErrorController.getErrorView(model, e.getMessage());
        }
    }

    @PutMapping()
    public String update(@Valid Advertisement advertisement, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("restMethod", "PUT");
                return "logged/creator-editor";
            }
            advertisementService.update(advertisement);
            return "redirect:/advertisements/my?updated";
        } catch (AdvertisementServiceException e) {
            return customErrorController.getErrorView(model, e.getMessage());
        }
    }
}

