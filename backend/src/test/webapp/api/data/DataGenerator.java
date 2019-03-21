package webapp.api.data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DataGenerator {

    /**
     * Load the persistence context based on the persistence.xml file
     */
    @PersistenceContext
    public EntityManager em;

    /**
     * Seed the database before running the tests
     * @param event information from the event called when the application server has spun up
     */
    abstract public void generateTestData(@Observes @Initialized(ApplicationScoped.class) final Object event);
}
