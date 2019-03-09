package repositories;

import entities.Account;
import interceptors.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Account> getAll() {
        return entityManager.createNamedQuery("Account.getAll", Account.class).getResultList();
    }

    @Interceptors(LoggingInterceptor.class)
    public Account find(String username) {
        return entityManager.createNamedQuery("Account.findOne", Account.class)
                .setParameter("username", username).getSingleResult();
    }

    public void save(Account account) {
        entityManager.persist(account);
    }

    public void update(Account account) {
        entityManager.merge(account);
    }

    public void delete(Account account) {
        // EntityManager.remove() works only on entities which are managed in the current transaction/context.
        // You need to check if the entity is managed by EntityManager.contains() and if not,
        // then make it managed with EntityManager.merge().
        // As shown in https://stackoverflow.com/a/17027553
        entityManager.remove(entityManager.contains(account) ? account : entityManager.merge(account));
    }
}