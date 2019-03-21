package webapp.api.data;

import entities.Notification;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

public class NotificationDataGenerator extends DataGenerator {

    /**
     * Seed the database before running the notification tests
     * The transactional annotation wraps this method in a SQL transaction
     * @param event information from the event called when the application server has spun up
     */
    @Transactional
    @Override
    public void generateTestData(@Observes @Initialized(ApplicationScoped.class) final Object event) {
        Notification notification = new Notification();
        notification.setMessage("Test message");

        em.persist(notification);
    }
}
