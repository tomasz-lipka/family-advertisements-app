package pl.familyadvertisementsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

@RestController
@RequestMapping("/")
public class AdvertisementController {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementController(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @GetMapping
    public ResponseEntity getAllAdvertisements() {
        return ResponseEntity.ok(this.advertisementRepository.findAll());
    }
}
