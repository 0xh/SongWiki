package webapp.api.data;

import entities.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

public class AccountDataGenerator extends DataGenerator {

    /**
     * Seed the database before running the account tests
     * The transactional annotation wraps this method in a SQL transaction
     * @param event information from the event called when the application server has spun up
     */
    @Override
    @Transactional
    public void generateTestData(@Observes @Initialized(ApplicationScoped.class) final Object event) {
        Account account = new Account();
        account.setUsername("test");
        account.setEmail("test@test.nl");
        account.setAge(21);

        em.persist(account);
    }

}
