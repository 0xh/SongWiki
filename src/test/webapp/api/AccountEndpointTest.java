package webapp.api;

import api.AccountEndpoint;
import api.config.ApplicationConfig;
import controllers.AccountController;
import entities.Account;
import interceptors.LoggingInterceptor;
import interfaces.IAccountController;
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
import webapp.api.data.AccountDataGenerator;
import webapp.api.data.DataGenerator;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.File;

import static org.junit.Assert.assertEquals;

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
                .addClass(EntityManager.class)
                .addClass(LoggingInterceptor.class)
                .addClass(DataGenerator.class)
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

        assertEquals(response.getStatus(), 200);
        assertEquals(accounts.length, 1);
        assertEquals(accounts[0].getUsername(), "test");
    }

    @Test
    @InSequence(2)
    public void getSpecificExistingAccount() {
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
        assertEquals(response.getStatus(), 200);
        assertEquals(fetchedAccount.getUsername(), "test");
        assertEquals(fetchedAccount.getEmail(), "test@test.nl");
        assertEquals(fetchedAccount.getAge(), 21);
    }

    @Test
    @InSequence(3)
    public void failOnNonExistentAccount() {
        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("accounts")
                .path("wrong")
                .request()
                .get();

        // Expect a 404: Not Found status code
        assertEquals(response.getStatus(), 404);
    }

    @Test
    @InSequence(4)
    public void saveAccount() {
        Account account = new Account();
        account.setUsername("new");
        account.setEmail("new@test.com");
        account.setAge(44);

        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .post(Entity.json(account));

        // Expect a 204: No Content as saving the account doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(response.getStatus(), 204);
    }

    @Test
    @InSequence(5)
    public void failOnSavingExistingAccount() {
        Account account = new Account();
        account.setUsername("new");
        account.setEmail("new@test.com");
        account.setAge(44);

        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .post(Entity.json(account));

        // Expect a 500: Server Error as the account is already present in the database
        assertEquals(response.getStatus(), 500);
    }

    @Test
    @InSequence(6)
    public void updateAccount() {
        Account account = new Account();
        account.setUsername("new");
        account.setEmail("new2@test.nl");
        account.setAge(55);

        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .put(Entity.json(account));

        // Expect a 204: No Content as saving the account doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(response.getStatus(), 204);
    }

    @Test
    @InSequence(7)
    public void updateNonExistentAccount() {
        Account account = new Account();
        account.setUsername("wrong");
        account.setEmail("wrong@test.nl");
        account.setAge(55);

        Response response = client.target(uri).path("api").path("accounts")
                .request()
                .put(Entity.json(account));

        // Expect a 500: Server Error as the specified account doesn't exist
        // It is successful though, which is why it falls in the 200 range
        assertEquals(response.getStatus(), 204);
    }

    @Test
    @InSequence(8)
    public void deleteAccount() {
        Response response = client.target(uri).path("api").path("accounts")
                .path("new")
                .request()
                .delete();

        // Expect a 204: No Content as saving the account doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(response.getStatus(), 204);
    }

    @Test
    @InSequence(9)
    public void deleteNonExistentAccount() {
        Response response = client.target(uri).path("api").path("accounts")
                .path("wrong")
                .request()
                .delete();

        // Expect a 204: No Content as the specified account doesn't exist
        // so is not present in the database by default
        assertEquals(response.getStatus(), 204);
    }
}
