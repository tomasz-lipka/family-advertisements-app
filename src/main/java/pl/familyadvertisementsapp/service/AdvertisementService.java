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

    @Autowired
    //TODO inject all by constructor
    private AdvertisementRepository advertisementRepository;

    private UserSessionHelper userSessionHelper;

    @Autowired
    public AdvertisementService(UserSessionHelper userSessionHelper) {
        this.userSessionHelper = userSessionHelper;
    }

    public void createAdvertisement(Advertisement advertisement) {
        AppUser currentLoggedAppUser = userSessionHelper.getCurrentLoggedAppUser();
        advertisement.setCreated(new Date());
        advertisement.setAppUserId(currentLoggedAppUser.getId());
        advertisementRepository.save(advertisement);
    }

    public Collection<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }
}
