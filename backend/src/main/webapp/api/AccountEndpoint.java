package api;

import api.filters.JWTTokenNeeded;
import controllers.AccountController;
import entities.Account;
import entities.Role;
import utils.PasswordHasher;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("accounts")
public class AccountEndpoint {

    @Inject
    private AccountController accountController;

    @Inject
    private PasswordHasher passwordHasher;

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
        String hashedPassword = passwordHasher.hash(account.getPassword());
        account.setPassword(hashedPassword);
        accountController.save(account);
    }

    @PUT
    @JWTTokenNeeded(roles = { Role.user, Role.admin })
    public void updateAccount(Account account) {
        String hashedPassword = passwordHasher.hash(account.getPassword());
        account.setPassword(hashedPassword);
        accountController.update(account);
    }

    @DELETE
    @Path("/{username}")
    public void deleteAccount(@PathParam("username") String username) {
        accountController.delete(username);
    }
}
