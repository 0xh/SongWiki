package interfaces;

import entities.Song;

import java.util.Collection;

public interface ISongController {
    Collection<Song> getAll();
    Song getSpecificSong(int id);
    void save(Song song);
    void update(Song song);
    void delete(int id);
}
