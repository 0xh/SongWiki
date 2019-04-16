package websockets.listeners;

import entities.Notification;
import websockets.NotificationSocket;

import javax.persistence.PostPersist;

public class NotificationChangeListener {
    @PostPersist
    public void onChange(Notification notification) {
        NotificationSocket.sendAll("Notification with message " + notification.getMessage() + " is created!");
    }
}
