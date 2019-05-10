package websockets.listeners;

import entities.Account;
import entities.Playlist;
import websockets.context.PushContext;

import javax.persistence.PostPersist;

public class PlaylistChangeListener {

    private PushContext pushContext;

    public PlaylistChangeListener() {
        pushContext = PushContext.getInstance();
    }

    @PostPersist
    public void onChange(Playlist playlist) {
        Account account = playlist.getAccount();
        pushContext.send(account.getUsername(), "Playlist with name " + playlist.getName() + " added!");
    }

}
