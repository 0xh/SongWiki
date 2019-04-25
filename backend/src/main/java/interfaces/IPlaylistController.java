package interfaces;

import entities.Playlist;

import java.util.List;

public interface IPlaylistController {
    List<Playlist> getAll();
    Playlist getSpecificPlaylist(int id);
    List<Playlist> getPlaylistsByUsername(String username);
    void save(Playlist playlist);
    void update(Playlist playlist);
    void delete(int id);
}
