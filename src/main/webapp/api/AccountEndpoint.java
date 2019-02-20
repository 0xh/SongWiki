package api;

import controllers.AccountController;
import entities.Account;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("accounts")
public class AccountEndpoint {

    @Inject
    private AccountController accountController;

    @GET
    public List<Account> getAllAccounts() {
        return accountController.all();
    }

    @GET
    @Path("/{username}")
    public Account getSpecificAccount(@PathParam("username") String username) {
        return accountController.find(username);
    }
}
