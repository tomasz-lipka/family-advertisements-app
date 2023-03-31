package pl.familyadvertisementsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.familyadvertisementsapp.model.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, String> {
    CustomUser findByUsername(String username);
}