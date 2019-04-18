package interfaces;

import entities.Song;

import java.util.List;

public interface ISongController {
    List<Song> getAll();
    Song getSpecificSong(int id);
}
