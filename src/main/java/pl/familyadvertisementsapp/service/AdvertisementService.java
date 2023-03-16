package pl.familyadvertisementsapp.service;

import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.helper.UserSessionHelper;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

import java.util.Collection;
import java.util.Date;

@Service
public class AdvertisementService {

    //TODO check if all fields are injected by constructor

    private AdvertisementRepository advertisementRepository;

    private UserSessionHelper userSessionHelper;

    public AdvertisementService(UserSessionHelper userSessionHelper, AdvertisementRepository advertisementRepository) {
        this.userSessionHelper = userSessionHelper;
        this.advertisementRepository = advertisementRepository;
    }

    public Collection<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Advertisement getById(Long id) {
        return advertisementRepository.findById(id).get();
    }

    public Advertisement create(Advertisement advertisement) {
        String owner = userSessionHelper.getCurrentPrincipalName();
        advertisement.setCreated(new Date());
        advertisement.setAppUserUsername(owner);
        advertisementRepository.save(advertisement);
        return advertisement;
    }

    public void deleteById(Long id) {
        advertisementRepository.deleteById(id);
    }

    public Collection<Advertisement> getOwned() {
        String owner = userSessionHelper.getCurrentPrincipalName();
        return advertisementRepository.findByAppUserUsername(owner);
    }

    public Advertisement update(Advertisement advertisement) {
        Advertisement update = getById(advertisement.getId());
        update.setTitle(advertisement.getTitle());
        update.setDescription(advertisement.getDescription());
        update.setCreated(new Date());
        return advertisementRepository.save(update);
    }
}
