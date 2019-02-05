package main.java.controllers;

import main.java.repositories.AccountRepository;
import main.java.entities.Account;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("accounts")
public class AccountController {

    @Inject
    private AccountRepository accountRepository;

    @GET
    @Produces("application/json")
    public List<Account> all() { return accountRepository.getAll(); }

    @GET
    @Path("/{username}")
    @Produces("application/json")
    public Account find(@PathParam("username") String username) { return accountRepository.find(username); }

    @POST
    @Consumes("application/json")
    public void save(Account account) {
        accountRepository.save(account);
    }

    @PUT
    @Consumes("application/json")
    public void update(Account account) {
        accountRepository.update(account);
    }

    @DELETE
    @Path("/{username}")
    @Consumes("application/json")
    public void delete(@PathParam("username") String username) {
        Account account = accountRepository.find(username);
        accountRepository.delete(account);
    }
}
