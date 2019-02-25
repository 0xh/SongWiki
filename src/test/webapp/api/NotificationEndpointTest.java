package webapp.api;

import entities.Notification;
import org.junit.Before;
import org.junit.Test;
import webapp.api.mocks.NotificationControllerMock;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class NotificationEndpointTest {
    private NotificationControllerMock notificationControllerMock;

    @Before
    public void setUp() {
        this.notificationControllerMock = new NotificationControllerMock();
    }

    @Test
    public void testGetAllEndpoint() {
        List<Notification> notificationList = notificationControllerMock.getAllNotifications();

        assertNotNull(notificationList);
    }
}
