package api;

import controllers.PlaylistController;
import entities.Playlist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.List;

@Path("/playlists")
public class PlaylistEndpoint {
    @Inject
    private PlaylistController playlistController;

    @Context
    private UriInfo uriInfo;

    @GET
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = playlistController.getAll();

        if (playlists == null) throw new NotFoundException();
        return playlists;
    }

    @GET
    @Path("/{id}")
    public Playlist getSpecificPlaylist(@PathParam("id") int id) {
        Playlist playlist = playlistController.getSpecificPlaylist(id);
        if (playlist == null) throw new NotFoundException();

        String selfUri = uriInfo.getBaseUriBuilder()
                .path(PlaylistEndpoint.class)
                .path(String.valueOf(playlist.getPlaylistId()))
                .build()
                .toString();

        String songsUri = uriInfo.getBaseUriBuilder()
                .path(SongEndpoint.class)
                .path("playlist")
                .path(String.valueOf(playlist.getPlaylistId()))
                .build()
                .toString();

        playlist.addLink(selfUri, "self");
        playlist.addLink(songsUri, "songs");

        return playlist;
    }

    @GET
    @Path("/account/{username}")
    public Collection<Playlist> getPlaylistsByUsername(@PathParam("username") String username) {
        return playlistController.getPlaylistsByUsername(username);
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
