package pl.familyadvertisementsapp.service.advertisement;

import org.springframework.stereotype.Service;
import pl.familyadvertisementsapp.exception.AdvertisementVerifierException;
import pl.familyadvertisementsapp.helper.UserSessionHelper;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

import java.util.Optional;

/**
 * This class checks if an Advertisement with the given id exists in the repository and if the currently logged-in user is the owner of this Advertisement.
 * If those conditions aren't met, then an exception is thrown.
 *
 * @author Tomasz Lipka
 */
@Service
public class AdvertisementVerifier {

    private final AdvertisementRepository advertisementRepository;
    private final UserSessionHelper userSessionHelper;
    private Advertisement advertisement;

    public AdvertisementVerifier(AdvertisementRepository advertisementRepository, UserSessionHelper userSessionHelper) {
        this.advertisementRepository = advertisementRepository;
        this.userSessionHelper = userSessionHelper;
    }

    AdvertisementVerifier findBy(Long advertisementId) throws AdvertisementVerifierException {
        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(advertisementId);
        if (advertisementOptional.isEmpty()) {
            throw new AdvertisementVerifierException("Advertisement with the given id doesn't exist");
        } else {
            this.advertisement = advertisementOptional.get();
            return this;
        }
    }

    Advertisement doesOwnerMatchLoggedInUser() throws AdvertisementVerifierException {
        String owner = advertisement.getOwnerUsername();
        if (userSessionHelper.matchesLoggedInUsername(owner)) {
            return advertisement;
        } else {
            throw new AdvertisementVerifierException("Logged in user isn't owner of this advertisement");
        }
    }
}
