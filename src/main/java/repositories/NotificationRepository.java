package repositories;

import entities.Notification;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class NotificationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Notification> getAll() {
        return entityManager.createNamedQuery("Notification.getAll", Notification.class).getResultList();
    }

    public Notification find(int id) {
        return entityManager.createNamedQuery("Notification.findOne", Notification.class)
                .setParameter("id", id).getSingleResult();
    }

    public void save(Notification notification) {
        entityManager.persist(notification);
    }

    public void update(Notification notification) {
        entityManager.merge(notification);
    }

    public void delete(Notification notification) {
        entityManager.remove(notification);
    }
}
