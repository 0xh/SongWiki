package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "PlayList.getAll", query = "SELECT p FROM PlayList p"),
    @NamedQuery(name = "PlayList.findOne", query = "select p from PlayList p where p.playlistId = :id")
})
public class PlayList {
    @Id
    @GeneratedValue
    private int playlistId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs = new ArrayList<>();

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

    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        songs.add(song);
        song.setPlaylist(this);
    }

    public void removeSong(Song song) {
        songs.remove(song);
        song.setPlaylist(null);
    }

    public PlayList() {}
}