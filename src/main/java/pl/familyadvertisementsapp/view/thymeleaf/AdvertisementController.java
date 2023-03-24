package pl.familyadvertisementsapp.view.thymeleaf;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.exception.AdvertisementServiceException;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.AdvertisementService;
import pl.familyadvertisementsapp.view.SelectedPage;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/advertisements")
@AllArgsConstructor
public class AdvertisementController {

    private AdvertisementService advertisementService;
    private CustomErrorController customErrorController;

    @GetMapping("/all")
    public String getAllView(Model model) {
        List<Advertisement> advertisements = advertisementService.getAll();
        model.addAttribute("advertisements", advertisements);
        model.addAttribute("selectedPage", "ALL");
        return "logged/advertisements/advertisements";
    }

    @GetMapping("/my")
    public String getMyView(Model model) {
        Collection<Advertisement> advertisements = advertisementService.getOwned();
        model.addAttribute("advertisements", advertisements);
        model.addAttribute("selectedPage", "MY");
        return "logged/advertisements/advertisements";
    }

    @GetMapping("/creator")
    public String getCreatorView(Model model) {
        Advertisement advertisement = new Advertisement();
        model.addAttribute("advertisement", advertisement);
        return "logged/advertisements/creator";
    }

    @PostMapping()
    public String create(@Valid Advertisement advertisement, BindingResult result) {
        if (result.hasErrors()) {
            return "logged/advertisements/creator";
        }
        advertisementService.create(advertisement);
        return "redirect:/advertisements/my?created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            advertisementService.deleteById(id);
            return "redirect:/advertisements/my?deleted";
        } catch (AdvertisementServiceException e) {
            return customErrorController.getErrorView(model, e.getMessage());
        }
    }

    @GetMapping("/{id}/editor")
    public String getEditorView(@PathVariable Long id, Model model) {
        try {
            Advertisement advertisement = advertisementService.getById(id);
            model.addAttribute("advertisement", advertisement);
            return "logged/advertisements/editor";
        } catch (AdvertisementServiceException e) {
            return customErrorController.getErrorView(model, e.getMessage());
        }
    }

    @PutMapping()
    public String update(@Valid Advertisement advertisement, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return "logged/advertisements/editor";
            }
            advertisementService.update(advertisement);
            return "redirect:/advertisements/my?updated";
        } catch (AdvertisementServiceException e) {
            return customErrorController.getErrorView(model, e.getMessage());
        }
    }
}

