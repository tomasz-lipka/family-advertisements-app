package pl.familyadvertisementsapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.helper.UserSessionHelper;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.model.AppUser;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

import java.util.Collection;
import java.util.Date;

@Service
public class AdvertisementService {

    //TODO inject all by constructor
    private AdvertisementRepository advertisementRepository;

    private UserSessionHelper userSessionHelper;

    @Autowired
    public AdvertisementService(UserSessionHelper userSessionHelper, AdvertisementRepository advertisementRepository) {
        this.userSessionHelper = userSessionHelper;
        this.advertisementRepository = advertisementRepository;
    }

    public void createAdvertisement(Advertisement advertisement) {
        advertisement.setCreated(new Date());
        String owner = userSessionHelper.getCurrentPrincipalName();
        advertisement.setAppUserUsername(owner);
        advertisementRepository.save(advertisement);
    }

    public Collection<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Collection<Advertisement> getOwned() {
        String owner = userSessionHelper.getCurrentPrincipalName();
        return advertisementRepository.findByAppUserUsername(owner);
    }

    public void deleteById(Long advertisementId) {
        advertisementRepository.deleteById(advertisementId);
    }
}
