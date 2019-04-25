package entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "Playlist.getAll", query = "SELECT p FROM Playlist p LEFT JOIN FETCH p.songs"),
    @NamedQuery(name = "Playlist.findOne", query = "select p from Playlist p LEFT JOIN FETCH p.songs where p.playlistId = :id")
})
public class Playlist {
    @Id
    @GeneratedValue
    private int playlistId;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    public int getPlaylistId() {
        return playlistId;
    }
    public void setPlaylistId(int id) {
        this.playlistId = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonbTransient
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @JsonbTransient
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public Playlist() {}
}
