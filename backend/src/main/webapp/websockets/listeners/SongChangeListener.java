package websockets.listeners;

import entities.Song;
import websockets.SongSocket;

import javax.persistence.PostPersist;

public class SongChangeListener {

    @PostPersist
    public void onChange(Song song) {
        SongSocket.sendAll("Song with name " + song.getName() + " added!");
    }

}
