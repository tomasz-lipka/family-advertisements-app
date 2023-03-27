package pl.familyadvertisementsapp.service.advertisement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.exception.AdvertisementServiceException;
import pl.familyadvertisementsapp.helper.UserSessionHelper;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * This class provides CRUD methods to manipulate the Advertisement model entities.
 *
 * @author Tomasz Lipka
 */
@Service
@AllArgsConstructor
public class AdvertisementService {

    //TODO make all lombok's final
    private AdvertisementRepository advertisementRepository;
    private UserSessionHelper userSessionHelper;
    private AdvertisementVerifier advertisementVerifier;

    public List<Advertisement> getAll() {
        List<Advertisement> advertisements = advertisementRepository.findAll();
        Collections.sort(advertisements);
        return advertisements;
    }

    public Advertisement getById(Long id) throws AdvertisementServiceException {
        return getVerified(id);
    }

    public void deleteById(Long id) throws AdvertisementServiceException {
        Advertisement advertisement = getVerified(id);
        advertisementRepository.deleteById(advertisement.getId());
    }

    public List<Advertisement> getOwned() {
        List<Advertisement> advertisements = advertisementRepository.findByOwnerUsername(getLoggedInUsername());
        Collections.sort(advertisements);
        return advertisements;
    }

    public void create(Advertisement advertisement) {
        advertisement.setOwnerUsername(getLoggedInUsername());
        advertisement.setCreated(new Date());
        advertisementRepository.save(advertisement);
    }

    public void update(Advertisement oldAdvertisement) throws AdvertisementServiceException {
        Advertisement newAdvertisement = getVerified(oldAdvertisement.getId());
        newAdvertisement.setTitle(oldAdvertisement.getTitle());
        newAdvertisement.setDescription(oldAdvertisement.getDescription());
        newAdvertisement.setCreated(new Date());
        advertisementRepository.save(newAdvertisement);
    }

    private Advertisement getVerified(Long id) throws AdvertisementServiceException {
        return advertisementVerifier
                .findBy(id)
                .doesOwnerMatchLoggedInUser();
    }

    private String getLoggedInUsername() {
        return userSessionHelper.getLoggedInUsername();
    }
}
