package controllers;

import entities.Song;
import interfaces.ISongController;
import repositories.SongRepository;

import javax.inject.Inject;
import java.util.Collection;

public class SongController implements ISongController {
    @Inject
    private SongRepository songRepository;

    @Override
    public Collection<Song> getAll() {
        return songRepository.getAll();
    }

    @Override
    public Song getSpecificSong(int id) {
        return songRepository.find(id);
    }

    @Override
    public Collection<Song> getSongsByPlaylistId(int id) {
        return songRepository.findSongsByPlaylistId(id);
    }

    @Override
    public void save(Song song) {
        song.setPublishedAt(System.currentTimeMillis());
        songRepository.save(song);
    }

    @Override
    public void update(Song song) {
        song.setPublishedAt(System.currentTimeMillis());
        songRepository.update(song);
    }

    @Override
    public void delete(int id) {
        Song song = songRepository.find(id);
        songRepository.delete(song);
    }


}
