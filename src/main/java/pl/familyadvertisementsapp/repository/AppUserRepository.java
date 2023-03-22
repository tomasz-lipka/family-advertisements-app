package pl.familyadvertisementsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.familyadvertisementsapp.model.CustomUser;

public interface AppUserRepository extends JpaRepository<CustomUser, String> {
    CustomUser findByUsername(String username);
}