package pl.familyadvertisementsapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Model class of the CustomUser entity. Stored in persistence layer.
 *
 * @author Tomasz Lipka
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser {

    @Id
    @Column(unique = true)
    private String username;

    private String password;
}