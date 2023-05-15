package pl.familyadvertisementsapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer class.
 * Allows transferring input Advertisement data into the program.
 * Enforces validation of the input fields.
 *
 * @author Tomasz Lipka
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDto {

    @NotNull
    @Size(min = 5, max = 20, message = "Must have at least 5 and maximum of 20 characters")
    @NotBlank(message = "Title may not be blank")
    private String title;

    @NotNull
    @Size(min = 20, max = 500, message = "Must have at least 20 and a maximum of 500 characters")
    @NotBlank(message = "Description may not be blank")
    private String description;
}