package webapp.api.mocks;

import entities.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationControllerMock {
    public List<Notification> getAllNotifications() {
        return new ArrayList<>();
    }
}
