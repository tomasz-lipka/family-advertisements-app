package pl.familyadvertisementsapp.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.familyadvertisementsapp.controller.serverside.CustomErrorController;
import pl.familyadvertisementsapp.exception.AdvertisementServiceException;
import pl.familyadvertisementsapp.exception.RestAdvertisementControllerException;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.service.advertisement.AdvertisementService;

import java.util.List;

@RestController
@RequestMapping("rest/advertisements")
@AllArgsConstructor
public class RestAdvertisementController {

    private final AdvertisementService advertisementService;
    private final CustomErrorController customErrorController;

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
    //TODO @valid
    public ResponseEntity<HttpStatus> create(@RequestBody Advertisement advertisement) {
        advertisementService.create(advertisement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    //TODO @valid
    public ResponseEntity<HttpStatus> update(@RequestBody Advertisement advertisement) {
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
}
