package lab3;

import java.util.Scanner;
import lab3.UserStorage;
import lab3.PasswordUtil;

public class AuthService {

    static Scanner sc = new Scanner(System.in);

    // метод ввода данных
    private static String input(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    // проверка, что оба введенных пароля совпадают
    private static String getConfirmedPassword(String message) {
        int count = 0;

        String password = input(message);
        String check = input("Повторите пароль: ");

        while (count < 1 && !password.equals(check)) {
            System.out.println("Пароли не совпадают!");
            check = input("Попробуйте ввести пароль еще раз: ");
            count++;
        }

        if (!password.equals(check)) {
            System.out.println("Превышен лимит попыток!");
            return null;
        }

        return password;
    }

    // одтверждение, что логин существует
    private static String getExistingLogin() {
        int count = 0;
        String login = input("Введите логин: ");

        while (count < 1 && !UserStorage.getUser(login)) {
            System.out.println("Такого логина не существует!");
            login = input("Введите логин: ");
            count++;
        }

        if (!UserStorage.getUser(login)) {
            System.out.println("Превышен лимит попыток!");
            return null;
        }

        return login;
    }

    // подтверждение, что нового логина еще нет среди данных
    private static String getNewLogin() {
        int count = 0;
        String login = input("Введите логин: ");

        while (count < 1 && UserStorage.getUser(login)) {
            System.out.println("Такой логин уже существует!");
            login = input("Введите логин: ");
            count++;
        }

        if (UserStorage.getUser(login)) {
            System.out.println("Превышен лимит попыток!");
            return null;
        }

        return login;
    }

    // подтверждение, что введенный пароль совпадает с существующим
    private static boolean verifyPassword(String login) {
        int count = 0;

        String password = input("Введите пароль: ");

        while (count < 1 && !PasswordUtil.verifyPassword(login, password)) {
            System.out.println("Неверный пароль!");
            password = input("Попробуйте ввести пароль еще раз: ");
            count++;
        }

        if (!PasswordUtil.verifyPassword(login, password)) {
            System.out.println("Превышен лимит попыток!");
            return false;
        }

        return true;
    }

    // регистрация нового пользователя
    public static void registerUser() {

        String login = getNewLogin();
        if (login == null) return;

        String password = getConfirmedPassword("Введите пароль: ");
        if (password == null) return;

        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(password, salt);

        UserStorage.addUser(login, salt, hash);

        System.out.println("Пользователь " + login + " успешно создан!");
    }

    // вход для существующего пользователя
    public static void loginUser() {

        String login = getExistingLogin();
        if (login == null) return;

        if (!verifyPassword(login)) return;

        System.out.println("Вход выполнен успешно!");
    }

    // смена пороля
    public static void changePassword() {

        String login = getExistingLogin();
        if (login == null) return;

        if (!verifyPassword(login)) return;

        String newPassword = getConfirmedPassword("Введите новый пароль: ");
        if (newPassword == null) return;

        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(newPassword, salt);

        UserStorage.changeUserPassword(login, salt, hash);

        System.out.println("Пароль успешно изменен!");
    }
}