package interfaces;

import entities.Playlist;

import java.util.List;

public interface IPlaylistController {
    List<Playlist> getAll();
    Playlist getSpecificPlaylist(int id);
    void save(Playlist playlist);
    void update(Playlist playlist);
    void delete(int id);
}
