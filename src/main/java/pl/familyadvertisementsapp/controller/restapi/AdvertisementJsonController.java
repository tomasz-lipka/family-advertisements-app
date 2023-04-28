package pl.familyadvertisementsapp.controller.restapi;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.familyadvertisementsapp.controller.base.AdvertisementAbstractController;
import pl.familyadvertisementsapp.exception.AdvertisementServiceException;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.model.dto.AdvertisementDto;
import pl.familyadvertisementsapp.service.advertisement.AdvertisementService;

import java.util.List;

/**
 * Rest controller for the Advertisement entity.
 *
 * @author Tomasz Lipka
 */
@RestController
@RequestMapping("restapi/advertisements")
@AllArgsConstructor
public class AdvertisementJsonController extends AdvertisementAbstractController {

    private final AdvertisementService advertisementService;

    @Override
    public List<Advertisement> getAll() {
        return advertisementService.getAll();
    }

    @Override
    public List<Advertisement> getMy() {
        return advertisementService.getOwned();
    }

    @Override
    public ResponseEntity<String> create(@RequestBody @Valid AdvertisementDto advertisementDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.toString());
        }
        advertisementService.create(advertisementDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            advertisementService.deleteBy(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (AdvertisementServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    protected Object update(@PathVariable Long id, @RequestBody @Valid AdvertisementDto advertisementDto, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.toString());
            }
            advertisementService.updateBy(id, advertisementDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (AdvertisementServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
