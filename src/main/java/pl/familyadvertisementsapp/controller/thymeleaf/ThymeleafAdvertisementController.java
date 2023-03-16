package pl.familyadvertisementsapp.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.AdvertisementService;

import java.util.Collection;

@Controller
@RequestMapping("thymeleaf/advertisements")
public class ThymeleafAdvertisementController {

    private AdvertisementService advertisementService;

    public ThymeleafAdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping()
    public String getAll(Model model) {
        Collection<Advertisement> advertisements = advertisementService.getAll();
        model.addAttribute("advertisements", advertisements);
        return "home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Advertisement advertisement = new Advertisement();
        model.addAttribute("advertisement", advertisement);
        return "create";
    }

    //TODO validate max descirption and title to fit in div
    @PostMapping("/create")
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

    //TODO how to make @DeleteMapping
    @GetMapping("/delete/{id}")
    public String deleteAdvertisements(@PathVariable Long id) {
        advertisementService.deleteById(id);
        return "success";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Advertisement advertisement = advertisementService.getById(id);
        model.addAttribute("advertisement", advertisement);
        return "update";
    }

    //TODO make PUT
    @PostMapping("/update")
    public String update(@ModelAttribute Advertisement advertisement, Model model) {
        model.addAttribute("advertisement", advertisement);
        System.out.println();
        advertisementService.update(advertisement);
        return "success";
    }
}

