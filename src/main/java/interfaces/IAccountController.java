package interfaces;

import entities.Account;

import java.util.List;

public interface IAccountController {
    List<Account> all();
    Account find(String username);
    void save(Account account);
    void update(Account account);
    void delete(String username);
}
