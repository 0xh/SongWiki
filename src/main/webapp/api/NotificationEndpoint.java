package api;

import controllers.NotificationController;
import entities.Notification;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("notifications")
public class NotificationEndpoint {

    @Inject
    private NotificationController notificationController;

    @GET
    public List<Notification> getAllNotifications() {
        return notificationController.getAll();
    }

    @GET
    @Path("/{id}")
    public Notification getSpecificNotification(@PathParam("id") int id) {
        return notificationController.find(id);
    }
}
