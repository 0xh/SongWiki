package entities;

import websockets.listeners.PlaylistChangeListener;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.*;

@Entity
@EntityListeners(PlaylistChangeListener.class)
@NamedQueries({
    @NamedQuery(name = "Playlist.getAll", query = "SELECT p FROM Playlist p LEFT JOIN FETCH p.songs"),
    @NamedQuery(name = "Playlist.findOne", query = "select p from Playlist p LEFT JOIN FETCH p.songs where p.playlistId = :id"),
    @NamedQuery(name = "Playlist.findByUsername", query = "select p from Playlist p where p.account.username = :username")
})
public class Playlist {
    @Id
    @GeneratedValue
    private int playlistId;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Song> songs = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private Account account;

    @Transient
    private List<Link> links = new ArrayList<>();

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

    public Collection<Song> getSongs() {
        return songs;
    }
    public void setSongs(Collection<Song> songs) {
        this.songs = songs;
    }

    @JsonbTransient
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void addLink(String link, String rel) {
        Link newLink = new Link(link, rel);
        links.add(newLink);
    }

    public Playlist() {}

    @PreRemove
    private void removePlaylistReferences() {
        if (songs == null) return;
        for (Song s : songs) {
            if (s.getPlaylists() != null) s.getPlaylists().remove(this);
        }

        if (account != null)
            account.getPlaylists().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return playlistId == playlist.playlistId &&
                Objects.equals(name, playlist.name) &&
                Objects.equals(description, playlist.description) &&
                Objects.equals(songs, playlist.songs) &&
                account.equals(playlist.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, name, description, songs, account);
    }
}
