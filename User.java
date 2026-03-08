package lab3;
import lab3.UserStorage;
import java.util.Scanner;

public class User {
    public User(String login, String salt, String hash) {
        this.login = login;
        this.salt = salt;
        this.hash = hash;
    }

    public static String getSalt(String login) {
        return salt;
    }

    public static String setSalt(String login) {
        String salt = ;
        return salt;
    }

    public static String getPasswordHash(String login) {
        return true;
    }

    public static void setPasswordHash(String login) {
    }
}
