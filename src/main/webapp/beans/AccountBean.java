package beans;

import controllers.AccountController;
import entities.Account;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * This bean uses ViewScoped because of the use of canEdit.
 * If this bean is annotated with @Model (short for @Named and @RequestScoped) the value isn't remembered after a
 * rerender because of the 'rendered' property in the XHTML
 */
@Named
@ViewScoped
public class AccountBean implements Serializable {

    @Inject
    private AccountController controller;

    private Account account;
    private boolean canEdit = false;

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isCanEdit() {
        return canEdit;
    }
    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public void editAccount() {
        setCanEdit(true);
    }

    public void saveAccount() {
        setCanEdit(false);
        controller.update(account);
    }

    @PostConstruct
    private void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        this.account = controller.find(username);
    }
}
