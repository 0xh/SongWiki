package main.java.repositories;

import main.java.entities.Account;

import javax.ejb.Stateless;
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
        entityManager.remove(account);
    }
}