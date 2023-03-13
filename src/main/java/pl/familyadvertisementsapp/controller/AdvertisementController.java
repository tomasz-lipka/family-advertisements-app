package pl.familyadvertisementsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.AdvertisementService;

import java.security.Principal;

@Controller
public class AdvertisementController {

    @Autowired
    AdvertisementService advertisementService;

    @GetMapping("/create-advertisement")
    public String createAdvertisement(Model model) {
        Advertisement advertisement = new Advertisement();
        model.addAttribute("advertisement", advertisement);
        return "create-advertisement";
    }

    //TODO validate max descirption and title to fit in div
    @PostMapping("/create-advertisement")
    public void createAdvertisement(@ModelAttribute Advertisement advertisement, Model model) {
        model.addAttribute("advertisement", advertisement);
        advertisementService.createAdvertisement(advertisement);
    }
}
