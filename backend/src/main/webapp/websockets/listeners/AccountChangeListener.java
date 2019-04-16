package websockets.listeners;

import entities.Account;
import websockets.AccountSocket;

import javax.persistence.PostPersist;

public class AccountChangeListener {
    @PostPersist
    public void onChange(Account account) {
        AccountSocket.sendAll("Account with username " + account.getUsername() + " added!");
    }
}
