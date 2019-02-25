package webapp.api;

import entities.Account;
import org.junit.Test;
import webapp.api.mocks.AccountControllerMock;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class AccountEndpointTest {

    @Test
    public void testGetAllEndpoint() {
        AccountControllerMock accountControllerMock = new AccountControllerMock();
        List<Account> accountList = accountControllerMock.getAllAccounts();

        assertNotNull(accountList);
    }

}
