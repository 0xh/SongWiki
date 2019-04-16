package entities;

import websockets.listeners.AccountChangeListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@EntityListeners(AccountChangeListener.class)
@NamedQueries({
    @NamedQuery(name = "Account.getAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findOne", query = "select a from Account a where a.username = :username")
})
public class Account {

    @Id
    @Size(min = 2, max = 40, message = "Username must be between 2 and 40 characters")
    private String username;

    @Size(min = 8, message = "Password must have more than 8 characters")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 13, message = "You must be older than 13 in order to use SongWiki")
    @Max(value = 130, message = "Did you find the potion of eternal life? Age can't be higher than 130")
    private int age;

    @Enumerated(EnumType.STRING)
    private Role role = Role.user;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return this.role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
