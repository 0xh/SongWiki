package interfaces;

import entities.Song;

import java.util.List;

public interface ISongController {
    List<Song> getAll();
    Song getSpecificSong(int id);
    void save(Song song);
    void update(Song song);
    void delete(int id);
}
