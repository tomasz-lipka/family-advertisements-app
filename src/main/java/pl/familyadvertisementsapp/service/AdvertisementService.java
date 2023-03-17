package pl.familyadvertisementsapp.service;

import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.helper.UserSessionHelper;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

import java.util.*;

@Service
public class AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    private UserSessionHelper userSessionHelper;

    public AdvertisementService(UserSessionHelper userSessionHelper, AdvertisementRepository advertisementRepository) {
        this.userSessionHelper = userSessionHelper;
        this.advertisementRepository = advertisementRepository;
    }

    public List<Advertisement> getAll() {
        List<Advertisement> advertisements = advertisementRepository.findAll();
        Collections.sort(advertisements);
        return advertisements;
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

    public List<Advertisement> getOwned() {
        String owner = userSessionHelper.getCurrentPrincipalName();
        List<Advertisement> advertisements = advertisementRepository.findByAppUserUsername(owner);
        Collections.sort(advertisements);
        return advertisements;
    }

    public Advertisement update(Advertisement advertisement) {
        Advertisement update = getById(advertisement.getId());
        update.setTitle(advertisement.getTitle());
        update.setDescription(advertisement.getDescription());
        update.setCreated(new Date());
        return advertisementRepository.save(update);
    }
}
