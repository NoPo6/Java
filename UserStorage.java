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
    
    // хэш таблица с пользователями
    static HashMap<String, User> users = new HashMap<>();

    // загрузка пользователей из файла при запуске программы
    public static void loadUsers(File file) {
        try (Scanner fileScanner = new Scanner(new FileReader(file))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] credentials = line.split("\\|");
                if (credentials.length == 3) {
                    String login = credentials[0];
                    String salt = credentials[1];
                    String hash = credentials[2];
                    User user = new User(login, salt, hash);
                    users.put(login, user);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл пользователей не найден");
        }
    }

    // получение пользователя по логину
    public static boolean getUser(String login) {
        return users.containsKey(login);
    }

    // добавление нового пользователя в конец файла
    public static void saveUsers(String login, String salt, String hash) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(login + "|" + salt + "|" + hash);
            writer.newLine();
            System.out.println("Пользователь " + login + " успешно создан!");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении пользователей");
            e.printStackTrace();
        }
    }

    // добавление нового пользователя
    public static void addUser(String login, String salt, String hash) {
        User user = new User(login, salt, hash);
        users.put(login, user);
        saveUsers(login, salt, hash);
    }

    // перезапись старых пользователей в файл (при смене пароля)
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
    
    // изменение соли и хэша при смене пароля
    public static void changeUserPassword(String login, String salt, String hash) {
        User user = users.get(login);
        if (user != null) {
            user.setSalt(salt);
            user.setHash(hash);
        }
    }
}