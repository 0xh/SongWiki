package websockets;

import websockets.context.PushContext;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/playlist/{username}")
public class PlaylistSocket {

    private PushContext pushContext;

    public PlaylistSocket() {
        pushContext = PushContext.getInstance();
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") final String username) {
        pushContext.add(username, session);
    }

    @OnClose
    public void onClose(@PathParam("username") final String username) {
        pushContext.remove(username);
    }

}
