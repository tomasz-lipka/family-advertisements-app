package pl.familyadvertisementsapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Model class of the advertisement entity. Stored in persistence layer.
 *
 * @author Tomasz Lipka
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement implements Comparable<Advertisement> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @Column(length = 500)
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