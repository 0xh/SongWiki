package webapp.api;

import controllers.AccountController;
import entities.Account;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import repositories.AccountRepository;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AccountEndpointTest {

    @ArquillianResource
    private URI uri;

    private Client client;

    @Deployment
    public static WebArchive createDeployment() {
        File[] dependencies = Maven.configureResolver().loadPomFromFile("pom.xml")
                .importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(AccountController.class)
                .addClass(AccountRepository.class)
                .addClass(Account.class)
                .addAsLibraries(dependencies)
                .addAsManifestResource(EmptyAsset.INSTANCE, "META-INF/beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Before
    public void setUp() {
        this.client = ClientBuilder.newClient();
    }

    @Test
    public void testGetAllEndpoint() {
        Response response = client.target(uri)
                .path("rest")
                .path("accounts")
                .request()
                .get();

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());


//        List<Account> accountList = accountController.all();
//
//        assertNotNull(accountList);
//        assertEquals(accountList.size(), 1);
//        assertEquals(accountList.get(0).getUsername(), "test");
//        assertEquals(accountList.get(0).getEmail(), "test@test.nl");
//        assertEquals(accountList.get(0).getAge(), 21);
    }

}
