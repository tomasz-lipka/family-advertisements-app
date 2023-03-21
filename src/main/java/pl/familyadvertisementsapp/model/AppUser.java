package pl.familyadvertisementsapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUser {

    @Id
    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 10)
    @NotBlank
    private String username;

    @NotNull
    @Size(min = 3)
    @NotBlank
    private String password;
}