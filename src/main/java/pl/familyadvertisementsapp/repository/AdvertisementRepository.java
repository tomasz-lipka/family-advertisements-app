package pl.familyadvertisementsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.familyadvertisementsapp.model.Advertisement;

import java.util.Collection;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Collection<Advertisement> findByAppUserUsername(String username);
}