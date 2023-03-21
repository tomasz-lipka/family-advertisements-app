package pl.familyadvertisementsapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Advertisement implements Comparable<Advertisement> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 5, max = 20)
    @NotBlank(message = "asdasdads")
    private String title;

    @Column(length = 9999)
    private String description;

    private Date created;

    private String appUserUsername;

    @Override
    public int compareTo(Advertisement advertisement) {
        long thisMilis = created.getTime();
        long anotherMilis = advertisement.getCreated().getTime();

        return Long.compare(anotherMilis, thisMilis);
    }
}