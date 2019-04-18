package repositories;

import entities.Song;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SongRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Song> getAll() {
        return entityManager.createNamedQuery("Song.getAll", Song.class).getResultList();
    }

    public Song find(int id) {
        return entityManager.createNamedQuery("Song.findOne", Song.class)
                .setParameter("id", id).getSingleResult();
    }

    public void save(Song song) {
        entityManager.persist(song);
    }
}
