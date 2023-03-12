package pl.familyadvertisementsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.AdvertisementService;

import java.util.Collection;

@Controller
public class HomePageController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/")
    public String test(Model model) {
        Collection<Advertisement> advertisements = advertisementService.getAll();
        model.addAttribute("advertisements", advertisements);
        return "home";
    }
}

