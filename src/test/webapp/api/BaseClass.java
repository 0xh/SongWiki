package webapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jboss.arquillian.test.api.ArquillianResource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URI;

abstract class BaseClass {
    /**
     * The base URI of the running server instance
     */
    @ArquillianResource
    URI uri;

    /**
     * The JAX-RS client to test/call the endpoint(s) with
     */
    Client client;

    /**
     * Instantiate Gson object without CDI as that's not available due to testable=false
     */
    Gson gson;

    BaseClass() {
        this.client = ClientBuilder.newClient();
        this.gson = new GsonBuilder().create();
    }

}
