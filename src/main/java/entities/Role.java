package entities;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    private String roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public Role() {}

    public Role(String roleId) {
        this.roleId = roleId;
    }
}
