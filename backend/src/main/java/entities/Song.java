package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
        @NamedQuery(name = "Song.getAll", query = "SELECT s FROM Song s"),
        @NamedQuery(name = "Song.findOne", query = "select s from Song s where s.id = :id")
})
public class Song {
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 2, max = 30, message = "Song name must be between 2 and 30 characters")
    private String name;

    private long publishedAt;

    private String resource;

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(long unixTime) {
        this.publishedAt = unixTime;
    }

    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
}
