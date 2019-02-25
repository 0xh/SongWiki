package webapp.api.mocks;

import entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountControllerMock {

    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        Account account = new Account();

        account.setUsername("test");
        account.setEmail("test@testmail.com");
        account.setAge(21);

        accountList.add(account);
        return accountList;
    }
}
