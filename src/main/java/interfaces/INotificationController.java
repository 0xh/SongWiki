package interfaces;

import entities.Notification;

import java.util.List;

public interface INotificationController {
    List<Notification> getAll();
    Notification find(int id);
    void save(Notification notification);
    void update(Notification notification);
    void delete(Notification notification);
}
