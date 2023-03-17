package pl.familyadvertisementsapp.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.AdvertisementService;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/advertisements")
public class AdvertisementController {

    private AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Advertisement> advertisements = advertisementService.getAll();
        model.addAttribute("advertisements", advertisements);
        return "all";
    }

    @GetMapping("/creator")
    public String getCreator(Model model) {
        Advertisement advertisement = new Advertisement();
        model.addAttribute("advertisement", advertisement);
        return "creator";
    }

    //TODO validate max descirption and title to fit in div
    @PostMapping()
    public String create(@ModelAttribute Advertisement advertisement, Model model) {
        model.addAttribute("advertisement", advertisement);
        advertisementService.create(advertisement);
        return "success";
    }

    @GetMapping("/my")
    public String getOwned(Model model) {
        Collection<Advertisement> advertisements = advertisementService.getOwned();
        model.addAttribute("advertisements", advertisements);
        return "my";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        advertisementService.deleteById(id);
        return "success";
    }

    @GetMapping("/{id}/editor")
    public String getEditor(@PathVariable Long id, Model model) {
        Advertisement advertisement = advertisementService.getById(id);
        model.addAttribute("advertisement", advertisement);
        return "editor";
    }

    @PutMapping()
    public String update(@ModelAttribute Advertisement advertisement, Model model) {
        model.addAttribute("advertisement", advertisement);
        advertisementService.update(advertisement);
        return "success";
    }
}

