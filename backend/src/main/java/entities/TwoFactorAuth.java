package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = "TwoFactorAuth.findOne", query = "select t from TwoFactorAuth t where t.username = :username")
})
public class TwoFactorAuth {

    @Id
    private String username;

    @NotNull
    private String key;

    @Transient
    private int verificationCode;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public int getVerificationCode() {
        return verificationCode;
    }
    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }

}
