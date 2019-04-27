package entities;

import websockets.listeners.SongChangeListener;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@EntityListeners(SongChangeListener.class)
@NamedQueries({
    @NamedQuery(name = "Song.getAll", query = "SELECT s FROM Song s"),
    @NamedQuery(name = "Song.findOne", query = "select s from Song s where s.songId = :id"),
    @NamedQuery(name = "Song.findSongsByPlaylistId",
        query = "select s from Song s inner join s.playlists playlist where playlist.playlistId = :id")
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
    private Collection<Playlist> playlists = new HashSet<>();

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

    @JsonbTransient
    public Collection<Playlist> getPlaylists() {
        return playlists;
    }
    public void setPlaylists(Collection<Playlist> playlist) { this.playlists = playlist; }

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
