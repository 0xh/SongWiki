package api;

import controllers.NotificationController;
import entities.Notification;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Collection;

@Path("notifications")
public class NotificationEndpoint {

    @Inject
    private NotificationController notificationController;

    @GET
    public Collection<Notification> getAllNotifications() {
        return notificationController.getAll();
    }

    @GET
    @Path("/{id}")
    public Notification getSpecificNotification(@PathParam("id") int id) {
        Notification notification = notificationController.find(id);

        if (notification == null) throw new NotFoundException();
        return notification;
    }

    @POST
    public void saveNotification(Notification notification) {
        notificationController.save(notification);
    }

    @PUT
    public void updateNotification(Notification notification) {
        notificationController.update(notification);
    }

    @DELETE
    @Path("/{id}")
    public void deleteNotification(@PathParam("id") int id) {
        notificationController.delete(id);
    }

}
