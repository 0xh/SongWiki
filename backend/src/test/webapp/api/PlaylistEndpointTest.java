package webapp.api;

import api.PlaylistEndpoint;
import api.config.ApplicationConfig;
import controllers.PlaylistController;
import entities.Playlist;
import entities.Song;
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
import repositories.PlaylistRepository;
import webapp.api.data.DataGenerator;
import webapp.api.data.PlaylistDataGenerator;
import websockets.listeners.SongChangeListener;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PlaylistEndpointTest extends BaseClass {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        File[] dependencies = Maven.configureResolver().loadPomFromFile("pom.xml")
                .importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(ApplicationConfig.class)
                .addClass(PlaylistEndpoint.class)
                .addClass(IPlaylistController.class)
                .addClass(PlaylistController.class)
                .addClass(PlaylistRepository.class)
                .addClass(Playlist.class)
                .addClass(Song.class)
                .addClass(SongChangeListener.class)
                .addClass(DataGenerator.class)
                .addClass(PlaylistDataGenerator.class)
                .addAsLibraries(dependencies)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Test
    @InSequence(1)
    public void getAllPlaylists() {
        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("playlists")
                .request()
                .get();

        // Parse the response to String as it's a JSON object
        String playlistJson = response.readEntity(String.class);

        // Parse an array of songs from the given JSON
        Playlist[] playlists = gson.fromJson(playlistJson, Playlist[].class);

        assertEquals(200, response.getStatus());
        assertEquals(1, playlists.length);
        assertEquals("test", playlists[0].getName());
    }

    @Test
    @InSequence(2)
    public void getPlaylist() {
        Response response = client.target(uri).path("api").path("playlists")
                .path("1")
                .request()
                .get();

        String playlistJson = response.readEntity(String.class);
        Playlist playlist = gson.fromJson(playlistJson, Playlist.class);

        assertEquals(200, response.getStatus());
        assertEquals("test", playlist.getName());
        assertEquals("Test description", playlist.getDescription());
    }

    @Test
    @InSequence(3)
    public void savePlaylist() {
        Playlist playlist = new Playlist();
        playlist.setName("additionalSong");
        playlist.setDescription("Test description");

        Response response = client.target(uri).path("api").path("playlists")
                .request()
                .post(Entity.json(playlist));

        // Expect a 204: No Content as saving the song doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(204, response.getStatus());
    }

    @Test
    @InSequence(4)
    public void updatePlaylist() {
        Playlist playlist = new Playlist();
        playlist.setPlaylistId(2);
        playlist.setName("updatedPlaylist");
        playlist.setDescription("Test description");

        Response response = client.target(uri).path("api").path("playlists")
                .request()
                .put(Entity.json(playlist));

        assertEquals(204, response.getStatus());
    }

    @Test
    @InSequence(5)
    public void deletePlaylist() {
        String playlistId = "2";

        Response response = client.target(uri).path("api").path("playlists")
                .path(playlistId)
                .request()
                .delete();

        assertEquals(204, response.getStatus());
    }

}

