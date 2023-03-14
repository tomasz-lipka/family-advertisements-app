package pl.familyadvertisementsapp.controller.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.AdvertisementService;

import java.util.Collection;

@Controller
public class MyAdvertisementsController {

    private AdvertisementService advertisementService;

    @Autowired
    public MyAdvertisementsController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/my-advertisements")
    public String getOwnedAdvertisements(Model model) {
        Collection<Advertisement> advertisements = advertisementService.getOwned();
        model.addAttribute("advertisements", advertisements);
        return "my-advertisements";
    }

    @GetMapping("/my-advertisements/delete")
    public String deleteAdvertisements(@RequestParam Long advertisementId) {
        advertisementService.deleteById(advertisementId);
        return "my-advertisements";
    }
}
