package api;

import controllers.SongController;
import entities.Song;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import java.util.List;

@Path("/songs")
public class SongEndpoint {

    @Inject
    private SongController songController;

    @GET
    public List<Song> getAllAccounts() {
        List<Song> songList = songController.getAll();

        if (songList == null) throw new NotFoundException();
        return songList;
    }

}
