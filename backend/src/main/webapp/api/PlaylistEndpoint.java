package api;

import controllers.PlaylistController;
import entities.Playlist;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/playlists")
public class PlaylistEndpoint {
    @Inject
    private PlaylistController playlistController;

    @GET
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = playlistController.getAll();

        if (playlists == null) throw new NotFoundException();
        return playlists;
    }

    @GET
    @Path("/{id}")
    public Playlist getSpecificPlaylist(@PathParam("id") int id) {
        return playlistController.getSpecificPlaylist(id);
    }

    @POST
    public void savePlaylist(Playlist playlist) {
        playlistController.save(playlist);
    }

    @PUT
    public void updatePlaylist(Playlist playlist) {
        playlistController.update(playlist);
    }

    @DELETE
    @Path("/{id}")
    public void deletePlaylist(@PathParam("id") int id) {
        playlistController.delete(id);
    }

}
