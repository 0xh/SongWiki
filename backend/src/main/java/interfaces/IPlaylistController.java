package interfaces;

import entities.Playlist;

import java.util.Collection;
import java.util.List;

public interface IPlaylistController {
    List<Playlist> getAll();
    Playlist getSpecificPlaylist(int id);
    Collection<Playlist> getPlaylistsByUsername(String username);
    void save(Playlist playlist);
    void update(Playlist playlist);
    void delete(int id);
}
