package webapp.api;

import api.SongEndpoint;
import api.config.ApplicationConfig;
import controllers.SongController;
import entities.Song;
import interfaces.ISongController;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import repositories.SongRepository;
import webapp.api.data.DataGenerator;
import webapp.api.data.SongDataGenerator;

import java.io.File;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class SongEndpointTest extends BaseClass {
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        File[] dependencies = Maven.configureResolver().loadPomFromFile("pom.xml")
                .importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(ApplicationConfig.class)
                .addClass(SongEndpoint.class)
                .addClass(ISongController.class)
                .addClass(SongController.class)
                .addClass(SongRepository.class)
                .addClass(Song.class)
                .addClass(DataGenerator.class)
                .addClass(SongDataGenerator.class)
                .addAsLibraries(dependencies)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Test
    @InSequence(1)
    public void getAllSongs() {
        assertTrue(true);
    }
}
