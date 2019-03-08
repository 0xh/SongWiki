package webapp.api;

import api.NotificationEndpoint;
import api.config.ApplicationConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.NotificationController;
import entities.Notification;
import interfaces.INotificationController;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import repositories.NotificationRepository;
import webapp.api.data.DataGenerator;
import webapp.api.data.NotificationDataGenerator;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class NotificationEndpointTest {

    /**
     * The base URI of the running server instance
     */
    @ArquillianResource
    private URI uri;

    /**
     * The JAX-RS client to test/call the endpoint(s) with
     */
    private Client client;

    /**
     * Instantiate Gson object without CDI as that's not available due to testable=false
     */
    private Gson gson;

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

    @Before
    public void setUp() {
        this.client = ClientBuilder.newClient();
        this.gson = new GsonBuilder().create();
    }

    @Test
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
}
