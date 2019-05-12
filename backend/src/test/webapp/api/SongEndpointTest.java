package webapp.api;

import api.SongEndpoint;
import api.config.ApplicationConfig;
import com.google.gson.reflect.TypeToken;
import controllers.SongController;
import entities.*;
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
                .addClass(SongChangeListener.class)
                .addClass(SongSocket.class)
                .addClass(Playlist.class)
                .addClass(PlaylistChangeListener.class)
                .addClass(PlaylistSocket.class)
                .addClass(PushContext.class)
                .addClass(Account.class)
                .addClass(Link.class)
                .addClass(Role.class)
                .addClass(AccountChangeListener.class)
                .addClass(AccountSocket.class)
                .addClass(DataGenerator.class)
                .addClass(SongDataGenerator.class)
                .addAsLibraries(dependencies)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Test
    @InSequence(1)
    public void getAllSongs() {
        // Use the JAX-RS 2.0 Client API to test the endpoint
        Response response = client.target(uri).path("api").path("songs")
                .request()
                .get();

        // Parse the response to String as it's a JSON object
        String songJson = response.readEntity(String.class);

        // Parse an array of songs from the given JSON
        Song[] songs = gson.fromJson(songJson, Song[].class);

        assertEquals(200, response.getStatus());
        assertEquals(1, songs.length);
        assertEquals("test", songs[0].getName());
    }

    @Test
    @InSequence(2)
    public void getSong() {
        Response response = client.target(uri).path("api").path("songs")
                .path("1")
                .request()
                .get();

        String songJson = response.readEntity(String.class);
        Song song = gson.fromJson(songJson, Song.class);

        assertEquals(200, response.getStatus());
        assertEquals("test", song.getName());
        assertEquals("https://www.youtube.com/watch?v=TKmGU77INaM", song.getResource());
    }

    @Test
    @InSequence(2)
    public void getSongByPlaylistId() {
        String id = "1";

        Response response = client.target(uri).path("api").path("songs")
                .path("playlist").path(id)
                .request()
                .get();

        String songsJson = response.readEntity(String.class);
        Type listType = new TypeToken<List<Song>>() {}.getType();
        List<Song> songs = gson.fromJson(songsJson, listType);

        assertEquals(200, response.getStatus());
        assertEquals(0, songs.size());
    }

    @Test
    @InSequence(3)
    public void saveSong() {
        Song song = new Song();
        song.setName("additionalSong");
        song.setResource("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        song.setPublishedAt(System.currentTimeMillis());

        Response response = client.target(uri).path("api").path("songs")
                .request()
                .post(Entity.json(song));

        // Expect a 204: No Content as saving the song doesn't return anything
        // It is successful though, which is why it falls in the 200 range
        assertEquals(204, response.getStatus());
    }

    @Test
    @InSequence(4)
    public void updateSong() {
        Song song = new Song();
        song.setSongId(2);
        song.setName("updatedSong");
        song.setPublishedAt(System.currentTimeMillis());
        song.setResource("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        Response response = client.target(uri).path("api").path("songs")
                .request()
                .put(Entity.json(song));

        assertEquals(204, response.getStatus());
    }

    @Test
    @InSequence(5)
    public void deleteSong() {
        String songId = "2";

        Response response = client.target(uri).path("api").path("songs")
                .path(songId)
                .request()
                .delete();

        assertEquals(204, response.getStatus());
    }
}
