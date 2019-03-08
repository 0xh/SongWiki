package api;

import controllers.AccountController;
import entities.Account;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("accounts")
public class AccountEndpoint {

    @Inject
    private AccountController accountController;

    @GET
    public List<Account> getAllAccounts() {
        List<Account> accountList = accountController.getAll();

        if (accountList == null) throw new NotFoundException();
        return accountList;
    }

    @GET
    @Path("/{username}")
    public Account getSpecificAccount(@PathParam("username") String username) {
        Account account = accountController.find(username);

        if (account == null) throw new NotFoundException();
        return account;
    }

    @POST
    public void saveAccount(Account account) {
        accountController.save(account);
    }
}
