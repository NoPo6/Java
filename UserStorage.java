package lab3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import lab3.User;

public class UserStorage {
    
    static HashMap<String, User> users = new HashMap<>();

    public static void loadUsers(File file){
        try (Scanner fileScanner = new Scanner(new FileReader(file))){
            while (fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            String[] credentials = line.split("\\|");
            if (credentials.length == 3){
                String login = credentials[0];
                String salt = credentials[1];
                String hash = credentials[2];
                User user = new User(login, salt, hash);
                users.put(login, user);
            }
        }
        fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл пользователей не найден");
        }
    }

    public static boolean getUser(String login) {
        return users.containsKey(login);
    }

    public static void saveUsers(String login, String salt, String hash) {
        try (FileWriter writer = new FileWriter("users.txt", true)){
            writer.write(login + "|" + salt + "|" + hash);
            writer.newLine();
            writer.close();
            System.out.println("Пользователь создан успешно!");
        } catch (IOException e) {
        System.out.println("Ошибка при сохранении пользователей");
        e.printStackTrace();
        }
    }

    public static void addUser(String login, String salt, String hash) {
        User user = new User(login, salt, hash);
        users.put(login, user);
        saveUsers(login, salt, hash);
    }

    public static void saveUsersToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
        for (User user : users.values()) {
            writer.write(user.getLogin() + "|" + user.getSalt() + "|" + user.getHash());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static void changeUserPassword(String login, String salt, String hash) {
        User user = users.get(login);
        user.setSalt(salt);
        user.setHash(hash);
    }
}
