package repositories;

import entities.TwoFactorAuth;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TwoFactorAuthRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TwoFactorAuth find(String username) {
        return entityManager.createNamedQuery("TwoFactorAuth.findOne", TwoFactorAuth.class)
                .setParameter("username", username).getSingleResult();
    }

    public void save(TwoFactorAuth twoFactorAuth) {
        entityManager.persist(twoFactorAuth);
    }

    public void delete(TwoFactorAuth twoFactorAuth) {
        entityManager.remove(entityManager.contains(twoFactorAuth) ?
                twoFactorAuth : entityManager.merge(twoFactorAuth));
    }

}
