package controllers;

import entities.Song;
import interfaces.ISongController;
import repositories.SongRepository;

import javax.inject.Inject;
import java.util.List;

public class SongController implements ISongController {
    @Inject
    private SongRepository songRepository;

    @Override
    public List<Song> getAll() {
        return songRepository.getAll();
    }

    @Override
    public Song getSpecificSong(int id) {
        return songRepository.find(id);
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }


}
