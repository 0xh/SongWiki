package entities;

import websockets.listeners.SongChangeListener;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

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

    public Playlist getPlaylist() {
        return playlist;
    }
    public void setPlaylist(Playlist playlist) { this.playlist = playlist; }

    public Song() {}
}
