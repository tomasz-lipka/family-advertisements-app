package pl.familyadvertisementsapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomUser {

    @Id
    @Column(unique = true)
    private String username;

    private String password;
}