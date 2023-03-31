package pl.familyadvertisementsapp.service.advertisement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.familyadvertisementsapp.exception.AdvertisementVerifierException;
import pl.familyadvertisementsapp.helper.UserSessionHelper;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Unit tests of the advertisement verifier.
 *
 * @author Tomasz Lipka
 */
@ExtendWith(MockitoExtension.class)
class AdvertisementVerifierTest {

    @Mock
    AdvertisementRepository advertisementRepository;

    @Mock
    UserSessionHelper userSessionHelper;

    @Mock
    Advertisement advertisement;

    @InjectMocks
    AdvertisementVerifier advertisementVerifier;

    @Test
    void shouldThrowExceptionBecauseIdDoesNotExist() {
        Long id = 1234L;
        when(advertisementRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(
                AdvertisementVerifierException.class,
                () -> advertisementVerifier.findBy(id)
        );
    }

    @Test
    void shouldThrowExceptionBecauseUsernamesNotMatch() {
        advertisementVerifier.setAdvertisement(advertisement);
        when(userSessionHelper.matchesLoggedInUsername(null)).thenReturn(false);
        assertThrows(
                AdvertisementVerifierException.class,
                () -> advertisementVerifier.doesOwnerMatchLoggedInUser()
        );
    }
}