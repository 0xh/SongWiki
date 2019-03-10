package webapp.api;

import api.NotificationEndpoint;
import api.config.ApplicationConfig;
import controllers.NotificationController;
import entities.Notification;
import interfaces.INotificationController;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import repositories.NotificationRepository;
import webapp.api.data.DataGenerator;
import webapp.api.data.NotificationDataGenerator;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class NotificationEndpointTest extends BaseClass {

    /**
     * Runs on deployment to fetch the necessary classes and dependencies for the test(s)
     *
     * Testable is set to false in order to run these tests as client instead of within the application server
     * This way the tests aren't included in the testing WAR either
     * Setting this to false means CDI is not supported anymore! (we're not in the application server after all)
     *
     * @return a web archive to be deployed to arquillian
     */
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        File[] dependencies = Maven.configureResolver().loadPomFromFile("pom.xml")
                .importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(ApplicationConfig.class)
                .addClass(NotificationEndpoint.class)
                .addClass(INotificationController.class)
                .addClass(NotificationController.class)
                .addClass(NotificationRepository.class)
                .addClass(Notification.class)
                .addClass(EntityManager.class)
                .addClass(DataGenerator.class)
                .addClass(NotificationDataGenerator.class)
                .addAsLibraries(dependencies)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Test
    @InSequence(1)
    public void getAllNotifications() {
        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("notifications")
                .request()
                .get();

        // Parse the response to String as it's a JSON object
        String notificationsJson = response.readEntity(String.class);

        // Parse an array of accounts from the given JSON
        Notification[] notifications = gson.fromJson(notificationsJson, Notification[].class);

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertEquals(notifications.length, 1);
        assertEquals(notifications[0].getMessage(), "Test message");
    }

    @Test
    @InSequence(2)
    public void getNotification() {
        Response response = client.target(uri).path("api").path("notifications")
                .path("1")
                .request()
                .get();

        String notificationJSON = response.readEntity(String.class);
        Notification parsedNotification = gson.fromJson(notificationJSON, Notification.class);

        assertEquals(response.getStatus(), 200);
        assertEquals(parsedNotification.getMessage(), "Test message");
    }

    @Test
    @InSequence(2)
    public void getNonExistentNotification() {
        Response response = client.target(uri).path("api").path("notifications")
                .path("wrong")
                .request()
                .get();

        assertEquals(response.getStatus(), 404);
    }

    @Test
    @InSequence(3)
    public void saveNotification() {
        Notification notification = new Notification();
        notification.setMessage("Test message 1");

        Response response = client.target(uri).path("api").path("notifications")
                .request()
                .post(Entity.json(notification));

        // Expect a 204: No Content as saving the account doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(response.getStatus(), 204);
    }

    @Test
    @InSequence(3)
    public void saveAlreadyExistingNotification() {
        Notification notification = new Notification();
        notification.setId(2);
        notification.setMessage("Already existing");

        Response response = client.target(uri).path("api").path("notifications")
                .request()
                .post(Entity.json(notification));

        // Expect a 500: Server Error as the notification is already present in the database
        assertEquals(response.getStatus(), 500);
    }

    @Test
    @InSequence(4)
    public void updateNotification() {
        Notification notification = new Notification();
        notification.setId(2);
        notification.setMessage("Test message updated");

        Response response = client.target(uri).path("api").path("notifications")
                .request()
                .put(Entity.json(notification));

        // Expect a 204: No Content as saving the notification doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(response.getStatus(), 204);
    }

    /**
     * TODO: fix 204 response instead of 500 due to merge of unknown item which creates a new item
     */
    @Test
    @InSequence(4)
    public void updateNonExistentNotification() {
        Notification notification = new Notification();
        notification.setId(19000);
        notification.setMessage("Non existent");

        Response response = client.target(uri).path("api").path("notifications")
                .request()
                .put(Entity.json(notification));

        // Expect a 500: Server Error as the specified notification doesn't exist
        assertEquals(response.getStatus(), 500);
    }

    @Test
    @InSequence(5)
    public void deleteNotification() {
        Response response = client.target(uri).path("api").path("notifications")
                .path("2")
                .request()
                .delete();

        // Expect a 204: No Content as saving the notification doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(response.getStatus(), 204);
    }

    @Test
    @InSequence(5)
    public void deleteNonExistentNotification() {
        Response response = client.target(uri).path("api").path("notifications")
                .path("5")
                .request()
                .delete();

        // Expect a 500: Server Error as the specified notification doesn't exist
        assertEquals(response.getStatus(), 500);
    }

}
