package controllers;

import entities.Notification;
import interfaces.INotificationController;
import repositories.NotificationRepository;

import javax.inject.Inject;
import java.util.List;

public class NotificationController implements INotificationController {

    @Inject
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAll() {
        return notificationRepository.getAll();
    }

    @Override
    public Notification find(int id) {
        return notificationRepository.find(id);
    }

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void update(Notification notification) {
        notificationRepository.update(notification);
    }

    @Override
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }
}
