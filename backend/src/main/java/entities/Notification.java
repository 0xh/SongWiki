package entities;

import websockets.listeners.NotificationChangeListener;

import javax.persistence.*;

@Entity
@EntityListeners(NotificationChangeListener.class)
@NamedQueries({
        @NamedQuery(name = "Notification.getAll", query = "SELECT n FROM Notification n"),
        @NamedQuery(name = "Notification.findOne", query = "select n from Notification n where n.id = :id")
})
public class Notification {

    @Id
    @GeneratedValue
    private int id;

    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
