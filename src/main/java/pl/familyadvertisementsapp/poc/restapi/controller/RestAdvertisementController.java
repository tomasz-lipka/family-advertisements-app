package pl.familyadvertisementsapp.poc.restapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.exception.AdvertisementServiceException;
import pl.familyadvertisementsapp.poc.restapi.exception.RestAdvertisementControllerException;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.advertisement.AdvertisementService;

import java.util.List;

//TODO restcontroloer: This class is made only for proof of concept reasons. The logic here is a duplicate of...
@RestController
@RequestMapping("rest/advertisements")
@AllArgsConstructor
public class RestAdvertisementController {

    private final AdvertisementService advertisementService;

    @GetMapping()
    public List<Advertisement> getAdvertisements() {
        return advertisementService.getAll();
    }

    @GetMapping("/{id}")
    public Advertisement getBy(@PathVariable Long id) {
        try {
            return advertisementService.getBy(id);
        } catch (AdvertisementServiceException e) {
            throw new RestAdvertisementControllerException(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Advertisement advertisement, BindingResult result) {
        throwBindingResultException(result);
        advertisementService.create(advertisement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid Advertisement advertisement, BindingResult result) {
        throwBindingResultException(result);
        try {
            advertisementService.update(advertisement);
        } catch (AdvertisementServiceException e) {
            throw new RestAdvertisementControllerException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBy(@PathVariable Long id) {
        try {
            advertisementService.deleteBy(id);
        } catch (AdvertisementServiceException e) {
            throw new RestAdvertisementControllerException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static void throwBindingResultException(BindingResult result) {
        if (result.hasErrors()) {
            throw new RestAdvertisementControllerException(result.toString());
        }
    }
}
