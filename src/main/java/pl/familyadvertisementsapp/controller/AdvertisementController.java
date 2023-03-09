package pl.familyadvertisementsapp.controller;

import org.springframework.stereotype.Controller;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

@Controller
//@RequestMapping("/")
public class AdvertisementController {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementController(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }
//    @GetMapping
//    public String test() {
//        return "page";
//    }

//    @GetMapping
//    public String getAllAdvertisements() {
////        return ResponseEntity.ok(this.advertisementRepository.findAll().);
//        return this.advertisementRepository.findAll().toString();
//    }
}
