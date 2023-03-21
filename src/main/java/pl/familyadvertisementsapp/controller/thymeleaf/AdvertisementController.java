package pl.familyadvertisementsapp.controller.thymeleaf;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.exception.AdvertisementServiceException;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.AdvertisementService;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/advertisements")
@AllArgsConstructor
public class AdvertisementController {

    private AdvertisementService advertisementService;
    private AppErrorController appErrorController;

    @GetMapping("/all")
    public String getAllView(Model model) {
        List<Advertisement> advertisements = advertisementService.getAll();
        model.addAttribute("advertisements", advertisements);
        return "advertisements/all";
    }

    @GetMapping("/my")
    public String getMyView(Model model) {
        Collection<Advertisement> advertisements = advertisementService.getOwned();
        model.addAttribute("advertisements", advertisements);
        return "advertisements/my";
    }

    @GetMapping("/creator")
    public String getCreatorView(Model model) {
        Advertisement advertisement = new Advertisement();
        model.addAttribute("advertisement", advertisement);
        return "advertisements/creator";
    }

    @GetMapping("/{id}/editor")
    public String getEditorView(@PathVariable Long id, Model model) {
        try {
            Advertisement advertisement = advertisementService.getById(id);
            model.addAttribute("advertisement", advertisement);
            return "advertisements/editor";
        } catch (AdvertisementServiceException e) {
            return appErrorController.getErrorView(model, e.getMessage());
        }
    }

    @PostMapping()
    public String create(@ModelAttribute @Valid Advertisement advertisement, Model model, BindingResult result) {
       result.addError(new FieldError("advertisement", "title","asd324"));
        if (result.hasErrors()) {
            System.out.println("22222");
            return "redirect:/advertisements/creator";
        }
        model.addAttribute("advertisement", advertisement);
        advertisementService.create(advertisement);
        return "redirect:/advertisements/my?created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            advertisementService.deleteById(id);
            return "redirect:/advertisements/my?deleted";
        } catch (AdvertisementServiceException e) {
            return appErrorController.getErrorView(model, e.getMessage());
        }
    }

    @PutMapping()
    public String update(@ModelAttribute Advertisement advertisement, Model model) {
        try {
            model.addAttribute("advertisement", advertisement);
            advertisementService.update(advertisement);
            return "redirect:/advertisements/my?updated";
        } catch (AdvertisementServiceException e) {
            return appErrorController.getErrorView(model, e.getMessage());
        }
    }
}

