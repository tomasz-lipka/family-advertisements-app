package pl.familyadvertisementsapp.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Advertisement implements Comparable<Advertisement> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    //TODO validate that in UI
    @Column(length = 9999)
    private String description;

    private Date created;

    private String appUserUsername;

    public Advertisement() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAppUserUsername() {
        return appUserUsername;
    }

    public void setAppUserUsername(String appUserUsername) {
        this.appUserUsername = appUserUsername;
    }

    @Override
    public int compareTo(Advertisement advertisement) {
        long thisMilis = created.getTime();
        long anotherMilis = advertisement.getCreated().getTime();

        return Long.compare(anotherMilis, thisMilis);
    }

    //TODO delete toString
    @Override
    public String toString() {
        return "Advertisement{" +
                "title='" + title + '\'' +
                ", created=" + created +
                '}';
    }
}