package pl.familyadvertisementsapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Model class of the advertisement entity. Stored in persistence layer.
 *
 * @author Tomasz Lipka
 */
@Entity
@Getter
@Setter
public class Advertisement implements Comparable<Advertisement> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 5, max = 20, message = "Must have at least 5 and maximum of 20 characters")
    @NotBlank(message = "Title may not be blank")
    private String title;

    @Column(length = 500)
    @NotNull
    @Size(min = 20, max = 500, message = "Must have at least 20 and a maximum of 500 characters")
    @NotBlank(message = "Description may not be blank")
    private String description;

    private Date created;

    private String ownerUsername;

    @Override
    public int compareTo(Advertisement advertisement) {
        long thisMillis = created.getTime();
        long anotherMillis = advertisement.getCreated().getTime();

        return Long.compare(anotherMillis, thisMillis);
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
        this.created = new Date();
    }
}