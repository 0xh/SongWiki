package interfaces;

import entities.Notification;

import java.util.Collection;

public interface INotificationController {
    Collection<Notification> getAll();
    Notification find(int id);
    void save(Notification notification);
    void update(Notification notification);
    void delete(int id);
}
