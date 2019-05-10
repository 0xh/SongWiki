package websockets.context;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

// Singleton instance because it handles context sending of all websockets
public class PushContext {

    private static PushContext instance = null;
    private Map<String, Session> sessions;

    private PushContext() {
        sessions = new HashMap<>();
    }

    // Static method to create instance of this singleton class
    public static PushContext getInstance()
    {
        if (instance == null)
            instance = new PushContext();

        return instance;
    }

    public void add(String username, Session session) {
        if (!sessions.containsValue(session)) sessions.put(username, session);
    }

    public void remove(String username) {
        sessions.remove(username);
    }

    public void send(String username, String message) {
        // Send the message to the sessions corresponding to the user
        Session session = sessions.get(username);
        session.getAsyncRemote().sendText(message);
    }

}
