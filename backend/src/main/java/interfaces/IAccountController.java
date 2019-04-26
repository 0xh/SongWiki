package interfaces;

import entities.Account;

import java.util.Collection;

public interface IAccountController {
    Collection<Account> getAll();
    Account find(String username);
    void save(Account account);
    void update(Account account);
    void delete(String username);
}
