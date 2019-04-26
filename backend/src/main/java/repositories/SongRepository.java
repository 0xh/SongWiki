package repositories;

import entities.Song;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Stateless
public class SongRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Song> getAll() {
        return entityManager.createNamedQuery("Song.getAll", Song.class).getResultList();
    }

    public Song find(int id) {
        return entityManager.createNamedQuery("Song.findOne", Song.class)
                .setParameter("id", id).getSingleResult();
    }

    public Collection<Song> findSongsByPlaylistId(int id) {
        return entityManager.createNamedQuery("Song.findSongsByPlaylistId", Song.class)
                .setParameter("id", id).getResultList();
    }

    public void save(Song song) {
        entityManager.persist(song);
    }

    public void update(Song song) {
        entityManager.merge(song);
    }

    public void delete(Song song) {
        entityManager.remove(entityManager.contains(song) ? song : entityManager.merge(song));
    }
}
