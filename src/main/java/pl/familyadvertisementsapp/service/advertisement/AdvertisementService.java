package pl.familyadvertisementsapp.service.advertisement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.exception.AdvertisementServiceException;
import pl.familyadvertisementsapp.helper.UserSessionHelper;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.model.dto.AdvertisementDto;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * This class provides CRUD methods to manage the Advertisement model entities.
 *
 * @author Tomasz Lipka
 */
@Service
@AllArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserSessionHelper userSessionHelper;
    private AdvertisementVerifier advertisementVerifier;

    public List<Advertisement> getAll() {
        List<Advertisement> advertisements = advertisementRepository.findAll();
        Collections.sort(advertisements);
        return advertisements;
    }

    public Advertisement getBy(Long id) throws AdvertisementServiceException {
        return getIfOwnedAndExistsBy(id);
    }

    public void deleteBy(Long id) throws AdvertisementServiceException {
        Advertisement advertisement = getIfOwnedAndExistsBy(id);
        advertisementRepository.deleteById(advertisement.getId());
    }

    public List<Advertisement> getOwned() {
        List<Advertisement> advertisements = advertisementRepository.findByOwnerUsername(getLoggedInUsername());
        Collections.sort(advertisements);
        return advertisements;
    }

    public void create(AdvertisementDto advertisementDto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(advertisementDto.getTitle());
        advertisement.setDescription(advertisementDto.getDescription());
        advertisement.setOwnerUsername(getLoggedInUsername());
        advertisement.setCreated(new Date());
        advertisementRepository.save(advertisement);
    }

    public void updateBy(Long id, AdvertisementDto advertisementDto) throws AdvertisementServiceException {
        Advertisement advertisement = getIfOwnedAndExistsBy(id);
        advertisement.update(
                advertisementDto.getTitle(),
                advertisementDto.getDescription()
        );
        advertisementRepository.save(advertisement);
    }

    private Advertisement getIfOwnedAndExistsBy(Long id) throws AdvertisementServiceException {
        return advertisementVerifier
                .findBy(id)
                .doesOwnerMatchLoggedInUser();
    }

    private String getLoggedInUsername() {
        return userSessionHelper.getLoggedInUsername();
    }
}
