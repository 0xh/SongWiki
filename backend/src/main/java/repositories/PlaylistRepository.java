package repositories;

import entities.Playlist;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PlaylistRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<Playlist> getAll() {
        return entityManager.createNamedQuery("Playlist.getAll", Playlist.class).getResultList();
    }

    public Playlist find(int id) {
        return entityManager.createNamedQuery("Playlist.findOne", Playlist.class)
                .setParameter("id", id).getSingleResult();
    }

    public List<Playlist> findByUsername(String username) {
        return entityManager.createNamedQuery("Playlist.findByUsername", Playlist.class)
                .setParameter("username", username).getResultList();
    }

    public void save(Playlist playlist) {
        entityManager.persist(entityManager.contains(playlist) ? playlist : entityManager.merge(playlist));
    }

    public void update(Playlist playlist) {
        entityManager.merge(playlist);
    }

    public void delete(Playlist playlist) {
        entityManager.remove(entityManager.contains(playlist) ? playlist : entityManager.merge(playlist));
    }
    
}
