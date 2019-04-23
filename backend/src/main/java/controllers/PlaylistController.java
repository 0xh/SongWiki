package controllers;

import entities.Playlist;
import interfaces.IPlaylistController;
import repositories.PlaylistRepository;

import javax.inject.Inject;
import java.util.List;

public class PlaylistController implements IPlaylistController {

    @Inject
    private PlaylistRepository playlistRepository;

    @Override
    public List<Playlist> getAll() {
        return playlistRepository.getAll();
    }

    @Override
    public Playlist getSpecificPlaylist(int id) {
        return playlistRepository.find(id);
    }

    @Override
    public void save(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public void update(Playlist playlist) {
        playlistRepository.update(playlist);
    }

    @Override
    public void delete(int id) {
        Playlist playlist = playlistRepository.find(id);
        playlistRepository.delete(playlist);
    }

}
