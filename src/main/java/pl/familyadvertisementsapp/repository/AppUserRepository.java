package pl.familyadvertisementsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.familyadvertisementsapp.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}