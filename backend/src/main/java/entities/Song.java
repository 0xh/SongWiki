package entities;

import websockets.listeners.SongChangeListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@EntityListeners(SongChangeListener.class)
@NamedQueries({
    @NamedQuery(name = "Song.getAll", query = "SELECT s FROM Song s"),
    @NamedQuery(name = "Song.findOne", query = "select s from Song s where s.songId = :id")
})
public class Song {
    @Id
    @GeneratedValue
    private int songId;

    @Size(min = 2, max = 30, message = "Song name must be between 2 and 30 characters")
    private String name;

    private long publishedAt;

    private String resource;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "songs")
    private List<Playlist> playlists = new ArrayList<>();

    public int getSongId() {
        return songId;
    }
    public void setSongId(int id) { this.songId = id; }

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

    public List<Playlist> getPlaylists() {
        return playlists;
    }
    public void setPlaylists(List<Playlist> playlist) { this.playlists = playlist; }

    public Song() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songId == song.songId &&
                publishedAt == song.publishedAt &&
                Objects.equals(name, song.name) &&
                Objects.equals(resource, song.resource) &&
                Objects.equals(playlists, song.playlists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, name, publishedAt, resource, playlists);
    }
}
