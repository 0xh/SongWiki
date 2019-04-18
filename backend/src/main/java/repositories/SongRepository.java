package repositories;

import entities.Song;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SongRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Song> getAll() {
        return entityManager.createNamedQuery("Song.getAll", Song.class).getResultList();
    }
}
