package pl.familyadvertisementsapp.controller.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.AdvertisementService;

@RestController()
@RequestMapping("advertisements")
public class AdvertisementController {

    private AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/create")
    public String createAdvertisement(Model model) {
        Advertisement advertisement = new Advertisement();
        model.addAttribute("advertisement", advertisement);
        return "create";
    }

    //TODO validate max descirption and title to fit in div
    @PostMapping("/create")
    public void createAdvertisement(@ModelAttribute Advertisement advertisement, Model model) {
        model.addAttribute("advertisement", advertisement);
        advertisementService.createAdvertisement(advertisement);
    }
}
