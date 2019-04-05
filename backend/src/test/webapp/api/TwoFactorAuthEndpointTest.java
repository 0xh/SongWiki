package webapp.api;

import api.TwoFactorAuthEndpoint;
import api.config.ApplicationConfig;
import controllers.TwoFactorAuthController;
import entities.TwoFactorAuth;
import interfaces.ITwoFactorAuthController;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import repositories.TwoFactorAuthRepository;
import utils.GoogleAuthenticatorFactory;

import javax.ws.rs.core.Response;
import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TwoFactorAuthEndpointTest extends BaseClass {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        File[] dependencies = Maven.configureResolver().loadPomFromFile("pom.xml")
                .importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(ApplicationConfig.class)
                .addClass(TwoFactorAuthEndpoint.class)
                .addClass(TwoFactorAuth.class)
                .addClass(ITwoFactorAuthController.class)
                .addClass(TwoFactorAuthController.class)
                .addClass(TwoFactorAuthRepository.class)
                .addClass(GoogleAuthenticatorFactory.class)
                .addAsLibraries(dependencies)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Test
    @InSequence(1)
    public void registerWithQrCode() {
        String username = "admin";

        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("2fa")
                .path(username)
                .request()
                .get();

        // Parse the response to String as it's a URL
        String otpAuthUrl = response.readEntity(String.class);

        // Expect a successful call to the endpoint
        assertEquals(200, response.getStatus());
        assertThat(otpAuthUrl, containsString("https://chart.googleapis.com/chart"));
    }

    @Test
    @InSequence(2)
    public void registerWithKey() {
        String username = "test";

        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("2fa").path("key")
                .path(username)
                .request()
                .get();

        // Parse the response to String as it's a URL
        String key = response.readEntity(String.class);

        // Expect a successful call to the endpoint
        assertEquals(200, response.getStatus());
        assertNotEquals(key, null);
    }

    @Test
    @InSequence(3)
    public void remove2FA() {
        String username = "admin";

        Response response = client.target(uri).path("api").path("2fa")
                .path(username)
                .request()
                .delete();

        // Expect a successful call to the endpoint with no return value
        assertEquals(204, response.getStatus());
    }
}
