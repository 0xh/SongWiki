package webapp.api;

import api.PlaylistEndpoint;
import api.SongEndpoint;
import api.config.ApplicationConfig;
import com.google.gson.reflect.TypeToken;
import controllers.AccountController;
import controllers.PlaylistController;
import controllers.SongController;
import entities.*;
import interceptors.LoggingInterceptor;
import interfaces.IAccountController;
import interfaces.IPlaylistController;
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
import repositories.AccountRepository;
import repositories.PlaylistRepository;
import repositories.SongRepository;
import webapp.api.data.DataGenerator;
import webapp.api.data.PlaylistDataGenerator;
import websockets.AccountSocket;
import websockets.PlaylistSocket;
import websockets.SongSocket;
import websockets.context.PushContext;
import websockets.listeners.AccountChangeListener;
import websockets.listeners.PlaylistChangeListener;
import websockets.listeners.SongChangeListener;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

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
                .addClass(Account.class)
                .addClass(Role.class)
                .addClass(Link.class)
                .addClass(AccountChangeListener.class)
                .addClass(AccountSocket.class)
                .addClass(AccountController.class)
                .addClass(AccountRepository.class)
                .addClass(IAccountController.class)
                .addClass(AccountChangeListener.class)
                .addClass(LoggingInterceptor.class)
                .addClass(Song.class)
                .addClass(SongChangeListener.class)
                .addClass(SongSocket.class)
                .addClass(SongEndpoint.class)
                .addClass(SongController.class)
                .addClass(SongRepository.class)
                .addClass(ISongController.class)
                .addClass(PlaylistChangeListener.class)
                .addClass(PlaylistSocket.class)
                .addClass(PushContext.class)
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
    @InSequence(2)
    public void getPlaylistByUsername() {
        String username = "test";

        Response response = client.target(uri).path("api").path("playlists")
                .path("account").path(username)
                .request()
                .get();

        String playlistsJson = response.readEntity(String.class);
        Type listType = new TypeToken<List<Playlist>>() {}.getType();
        List<Playlist> playlists = gson.fromJson(playlistsJson, listType);

        assertEquals(200, response.getStatus());
        assertEquals(0, playlists.size());
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

