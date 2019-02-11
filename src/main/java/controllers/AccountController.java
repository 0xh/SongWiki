package controllers;

import entities.Account;
import interfaces.IAccountController;
import repositories.AccountRepository;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class AccountController implements IAccountController {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public List<Account> all() {
        return accountRepository.getAll();
    }

    @Override
    public Account find(String username) {
        return accountRepository.find(username);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void update(Account account) {
        accountRepository.update(account);
    }

    @Override
    public void delete(String username) {
        Account account = accountRepository.find(username);
        accountRepository.delete(account);
    }
}
