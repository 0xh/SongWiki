package entities;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Account.getAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findOne", query = "select a from Account a where a.username = :username")
})
public class Account {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String email;
    private int age;

    public int getId() { return id; }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
}
