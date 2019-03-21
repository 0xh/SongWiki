package api.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Leave this file empty so that it picks up all the files in the archive
 * This is beneficial for testing as Arquillian only bundles the necessary classes in the archive.
 * If a specified class is not in the archive, the test will fail.
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {}
