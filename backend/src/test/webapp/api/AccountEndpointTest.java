package webapp.api;

import api.AccountEndpoint;
import api.PlaylistEndpoint;
import api.config.ApplicationConfig;
import controllers.AccountController;
import controllers.PlaylistController;
import entities.*;
import interceptors.LoggingInterceptor;
import interfaces.IAccountController;
import interfaces.IPlaylistController;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import repositories.AccountRepository;
import repositories.PlaylistRepository;
import utils.PasswordHasher;
import webapp.api.data.AccountDataGenerator;
import webapp.api.data.DataGenerator;
import websockets.AccountSocket;
import websockets.PlaylistSocket;
import websockets.SongSocket;
import websockets.context.PushContext;
import websockets.listeners.AccountChangeListener;
import websockets.listeners.PlaylistChangeListener;
import websockets.listeners.SongChangeListener;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.File;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class AccountEndpointTest extends BaseClass {

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
                .importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(ApplicationConfig.class)
                .addClass(AccountEndpoint.class)
                .addClass(IAccountController.class)
                .addClass(AccountController.class)
                .addClass(AccountRepository.class)
                .addClass(Account.class)
                .addClass(AccountChangeListener.class)
                .addClass(AccountSocket.class)
                .addClass(Link.class)
                .addClass(Role.class)
                .addClass(Playlist.class)
                .addClass(PlaylistChangeListener.class)
                .addClass(PlaylistSocket.class)
                .addClass(PlaylistEndpoint.class)
                .addClass(PlaylistController.class)
                .addClass(PlaylistRepository.class)
                .addClass(IPlaylistController.class)
                .addClass(PushContext.class)
                .addClass(Song.class)
                .addClass(SongChangeListener.class)
                .addClass(SongSocket.class)
                .addClass(EntityManager.class)
                .addClass(LoggingInterceptor.class)
                .addClass(DataGenerator.class)
                .addClass(PasswordHasher.class)
                .addClass(AccountDataGenerator.class)
                .addAsLibraries(dependencies)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Test
    @InSequence(1)
    public void getAllAccounts() {
        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .get();

        // Parse the response to String as it's a JSON object
        String accountsJson = response.readEntity(String.class);

        // Parse an array of accounts from the given JSON
        Account[] accounts = gson.fromJson(accountsJson, Account[].class);

        assertEquals(200, response.getStatus());
        assertEquals(1, accounts.length);
        assertEquals("test", accounts[0].getUsername());
    }

    @Test
    @InSequence(2)
    public void getAccount() {
        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("accounts")
                .path("test")
                .request()
                .get();

        // Parse the response to String as it's a JSON object
        String accountJson = response.readEntity(String.class);

        // Parse the account from JSON
        Account fetchedAccount = gson.fromJson(accountJson, Account.class);

        // Expect a successful database call with the predefined entries
        assertEquals(200, response.getStatus());
        assertEquals("test", fetchedAccount.getUsername());
        assertEquals("test@test.nl", fetchedAccount.getEmail());
        assertEquals(21, fetchedAccount.getAge());
    }

    @Test
    @InSequence(2)
    public void getNonExistentAccount() {
        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("accounts")
                .path("wrong")
                .request()
                .get();

        // Expect a 404: Not Found status code
        assertEquals(404, response.getStatus());
    }

    @Test
    @InSequence(3)
    public void saveAccount() {
        Account account = new Account();
        account.setUsername("new");
        account.setPassword("testPassword");
        account.setEmail("new@test.com");
        account.setAge(44);

        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .post(Entity.json(account));

        // Expect a 204: No Content as saving the account doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(204, response.getStatus());
    }

    @Test
    @InSequence(3)
    public void saveAlreadyExistingAccount() {
        Account account = new Account();
        account.setUsername("new");
        account.setPassword("testPassword");
        account.setEmail("new@test.com");
        account.setAge(44);

        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .post(Entity.json(account));

        // Expect a 500: Server Error as the account is already present in the database
        assertEquals(500, response.getStatus());
    }

    @Test
    @InSequence(3)
    public void setDefaultRole() {
        String userName = "roleTest";

        Account account = new Account();
        account.setUsername(userName);
        account.setPassword("testPassword");
        account.setEmail("role@test.com");
        account.setAge(44);

        client.target(uri).path("api").path("accounts")
                .request()
                .put(Entity.json(account));

        Response response = client.target(uri).path("api").path("accounts")
                .path(userName)
                .request()
                .get();

        // Parse the response to String as it's a JSON object
        String accountJson = response.readEntity(String.class);

        // Parse the account from JSON
        Account fetchedAccount = gson.fromJson(accountJson, Account.class);

        assertEquals(Role.user, fetchedAccount.getRole());
    }

    @Test
    @InSequence(3)
    public void setDifferentRole() {
        String userName = "roleTest2";

        Account account = new Account();
        account.setUsername(userName);
        account.setPassword("testPassword");
        account.setEmail("role2@test.com");
        account.setAge(44);
        account.setRole(Role.admin);

        client.target(uri).path("api").path("accounts")
                .request()
                .put(Entity.json(account));

        Response response = client.target(uri).path("api").path("accounts")
                .path(userName)
                .request()
                .get();

        // Parse the response to String as it's a JSON object
        String accountJson = response.readEntity(String.class);

        // Parse the account from JSON
        Account fetchedAccount = gson.fromJson(accountJson, Account.class);

        assertEquals(Role.admin, fetchedAccount.getRole());
    }

    @Test
    @InSequence(4)
    public void updateAccount() {
        Account account = new Account();
        account.setUsername("new");
        account.setPassword("testPassword");
        account.setEmail("new2@test.nl");
        account.setAge(55);

        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .put(Entity.json(account));

        // Expect a 204: No Content as saving the account doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(204, response.getStatus());
    }

    /**
     * TODO: fix 204 response instead of 500 due to merge of unknown item which creates a new item
     */
    @Test
    @InSequence(4)
    public void updateNonExistentAccount() {
        Account account = new Account();
        account.setUsername("wrong");
        account.setPassword("testPassword");
        account.setEmail("wrong@test.nl");
        account.setAge(55);

        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .put(Entity.json(account));

        // Expect a 500: Server Error as the specified account doesn't exist
        // It is successful though, which is why it falls in the 200 range
        assertEquals(500, response.getStatus());
    }

    @Test
    @InSequence(5)
    public void deleteAccount() {
        Response response = client.target(uri).path("api").path("accounts")
                .path("new")
                .request()
                .delete();

        // Expect a 204: No Content as saving the account doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(204, response.getStatus());
    }

    @Test
    @InSequence(5)
    public void deleteNonExistentAccount() {
        Response response = client.target(uri).path("api").path("accounts")
                .path("nonexistent")
                .request()
                .delete();

        // Expect a 500: Server Error as the specified account doesn't exist
        assertEquals(500, response.getStatus());
    }
}
