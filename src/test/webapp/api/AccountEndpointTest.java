package webapp.api;

import entities.Account;
import org.junit.Before;
import org.junit.Test;
import webapp.api.mocks.AccountControllerMock;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class AccountEndpointTest {

    private AccountControllerMock accountControllerMock;

    @Before
    public void setUp() {
        this.accountControllerMock = new AccountControllerMock();
    }

    @Test
    public void testGetAllEndpoint() {
        List<Account> accountList = accountControllerMock.getAllAccounts();

        assertNotNull(accountList);
    }

}
