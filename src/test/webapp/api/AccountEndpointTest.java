package webapp.api;

import api.AccountEndpoint;
import api.config.ApplicationConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.AccountController;
import entities.Account;
import interceptors.LoggingInterceptor;
import interfaces.IAccountController;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import repositories.AccountRepository;
import webapp.api.data.TestDataGenerator;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AccountEndpointTest {

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
     * @return a web archive to be deployed to arquillian
     */
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        File[] dependencies = Maven.configureResolver().loadPomFromFile("pom.xml")
                .importDependencies(ScopeType.TEST).resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(ApplicationConfig.class)
                .addClass(AccountEndpoint.class)
                .addClass(IAccountController.class)
                .addClass(AccountController.class)
                .addClass(AccountRepository.class)
                .addClass(Account.class)
                .addClass(EntityManager.class)
                .addClass(LoggingInterceptor.class)
                .addClass(TestDataGenerator.class)
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
    public void getAllAccounts() {
        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .get();

        // Parse the response to String as it's a JSON object
        String accountsJson = response.readEntity(String.class);

        // Parse an array of accounts from the given JSON
        Account[] accounts = gson.fromJson(accountsJson, Account[].class);

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertEquals(accounts.length, 1);
        assertEquals(accounts[0].getUsername(), "test");
    }

}
