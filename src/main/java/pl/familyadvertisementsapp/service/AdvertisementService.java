package pl.familyadvertisementsapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

import java.util.Collection;

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    public void createAdvertisement(Advertisement advertisement) {
        advertisementRepository.save(advertisement);
    }

    public Collection<Advertisement> getAll(){
        return advertisementRepository.findAll();
    }
}
