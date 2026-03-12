package lab3;
import lab3.UserStorage;
import java.util.Scanner;

public class User {
    // приватные поля пользователя
    private String login;
    private String salt;
    private String hash;

    // конструктор пользователя 
    public User(String login, String salt, String hash) {
        this.login = login;
        this.salt = salt;
        this.hash = hash;
    }

    // геттер для логина
    public String getLogin() {
        return this.login;
    }

    // геттер для соли
    public String getSalt() {
        return this.salt;
    }

    // геттер для хэша
    public String getHash() {
        return this.hash;
    }

    // сетер для соли
    public void setSalt(String salt) {
        this.salt = salt;
    }
     
    // сетер для хэша
    public void setHash(String hash) {
        this.hash = hash;
    }
}
