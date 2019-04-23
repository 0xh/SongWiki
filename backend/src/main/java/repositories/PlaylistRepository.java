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

    public void save(Playlist playlist) {
        entityManager.persist(playlist);
    }

    public void update(Playlist playlist) {
        entityManager.merge(playlist);
    }

    public void delete(Playlist playlist) {
        entityManager.remove(entityManager.contains(playlist) ? playlist : entityManager.merge(playlist));
    }
    
}
