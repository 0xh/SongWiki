import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Setup JAX-RS (Java API for RESTFul webservices) for the specified application path
 */
@ApplicationPath("rest")
public class ApplicationConfig extends Application {}