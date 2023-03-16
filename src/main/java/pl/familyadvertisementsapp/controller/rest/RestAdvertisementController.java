package pl.familyadvertisementsapp.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.AdvertisementService;

import java.util.Collection;

@RestController
@RequestMapping("rest")
public class RestAdvertisementController {

    private AdvertisementService advertisementService;

    public RestAdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/advertisements")
    public Collection<Advertisement> getAll() {
        return advertisementService.getAll();
    }

    @GetMapping("/advertisements/{id}")
    public Advertisement getById(@PathVariable Long id) {
        return advertisementService.getById(id);
    }

    @PostMapping("/advertisements")
    public Advertisement create(@RequestBody Advertisement advertisement) {
        return advertisementService.create(advertisement);
    }

    @DeleteMapping("/advertisements/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        advertisementService.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/advertisements")
    public Advertisement update(@RequestBody Advertisement advertisement) {
        return advertisementService.update(advertisement);
    }
}
