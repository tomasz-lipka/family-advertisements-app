package pl.familyadvertisementsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.familyadvertisementsapp.model.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}