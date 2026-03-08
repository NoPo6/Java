package lab3;
import lab3.UserStorage;
import java.util.Scanner;

public class User {
    private String login;
    private String salt;
    private String hash;

    public User(String login, String salt, String hash) {
        this.login = login;
        this.salt = salt;
        this.hash = hash;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSalt() {
        return this.salt;
    }

    public String getHash() {
        return this.hash;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
     
    public void setHash(String hash) {
        this.hash = hash;
    }
}
