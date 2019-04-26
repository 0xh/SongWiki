package repositories;

import entities.Notification;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Stateless
public class NotificationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Notification> getAll() {
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
        // EntityManager.remove() works only on entities which are managed in the current transaction/context.
        // You need to check if the entity is managed by EntityManager.contains() and if not,
        // then make it managed with EntityManager.merge().
        // As shown in https://stackoverflow.com/a/17027553
        entityManager.remove(entityManager.contains(notification) ? notification : entityManager.merge(notification));
    }
}
