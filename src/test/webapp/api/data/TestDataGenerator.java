package webapp.api.data;

import entities.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class TestDataGenerator {

    /**
     * Load the persistence context based on the persistence.xml file
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Seed the database before running the tests
     * The transactional annotation wraps this method in a SQL transaction
     * @param event information from the event called when the application server has spun up
     */
    @Transactional
    private void generateTestData(@Observes @Initialized(ApplicationScoped.class) final Object event) {
        Account account = new Account();
        account.setUsername("test");
        account.setEmail("test@test.nl");
        account.setAge(21);

        em.persist(account);
    }
}
