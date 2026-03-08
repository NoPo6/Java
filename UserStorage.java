package lab3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UserStorage {
    
    HashMap<String, User> users = new HashMap<>();

    public void loadUsers() {
        Scanner fileScanner = new Scanner(new FileReader(file));

        while (fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            String[] credentials = line.split(';');
            if (credentials.length == 3){
                String login = parts[0];
                String salt = parts[1];
                String hash = parts[2];
                User user = new User(login, salt, hash);
                users.put(login, user);
            }
        }
        fileScanner.close();
    }

    public User getUser(String login) {
        return users.get(login);
    }

    public static void saveUsers(String[] args) {
        System.out.println("Пользователи успешно сохранены в файл!");
    }

    public static void addUser(user) {
        
    }

    public static void updateUser(user) {
        
    }

    public static void checkUser(login) {
        
    }
}
