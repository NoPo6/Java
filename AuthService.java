package lab3;

import java.util.Scanner;
import Lab3.UserStorage;
import lab3.PasswordUtil;

public class AuthService {
    
    static Scanner sc = new Scanner(System.in);

    public static void registerUser(){
        int count = 0;
        System.out.print("Введите логин: ");
        String login = sc.nextLine();
        while (count < 1 && UserStorage.getUser(login)){
            System.out.print("\nТакой логин уже существует!\n");
            System.out.print("Введите логин: ");
            login = sc.nextLine();
            count += 1;
        }
        if (UserStorage.getUser(login)){
            System.out.println("Превышен лимит попыток, возврат в главное меню!");
            return;
        } 
        System.out.print("Введите пароль: ");
        String password = sc.nextLine();
        System.out.print("Повторите пароль: ");
        String password_check = sc.nextLine();
        count = 0;
        while (count < 1 && !password.equals(password_check)){
            System.out.print("\nПароли не совпадают!\nПовторите пороль: ");
            password_check = sc.nextLine();
            count += 1;
        }
        if (!password.equals(password_check)){
            System.out.println("Превышен лимит попыток, возврат в главное меню!");
            return;
        } 

        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(password, salt);
        UserStorage.addUser(login, salt, hash);
        System.out.println("Пользователь " + login + " успешно создан!");
    }

    public static void loginUser() {
        int count = 0;
        System.out.print("Введите логин: ");
        String login = sc.nextLine();
        while (count < 1 && !UserStorage.getUser(login)){
            System.out.print("\nТакого логина не существует!\n");
            System.out.print("Введите логин: ");
            login = sc.nextLine();
            count += 1;
        }
        if (!UserStorage.getUser(login)){
            System.out.println("Превышен лимит попыток, возврат в главное меню!");
            return;
        } 
        System.out.print("Введите пароль: ");
        String password_check = sc.nextLine();
        count = 0;
        while (count < 1 && !PasswordUtil.verifyPassword(login, password_check)){
            System.out.print("\nНеверный пароль!\nПовторите пороль: ");
            password_check = sc.nextLine();
            count += 1;
        }
        if (!PasswordUtil.verifyPassword(login, password_check)){
            System.out.println("Превышен лимит попыток, возврат в главное меню!");
            return;
        }
        System.out.println("Вход выполнен успешно!");
    }

    public static void changePassword() {
        int count = 0;
        System.out.print("Введите логин: ");
        String login = sc.nextLine();
        while (count < 1 && !UserStorage.getUser(login)){
            System.out.print("\nТакого логина не существует!\n");
            System.out.print("Введите логин: ");
            login = sc.nextLine();
            count += 1;
        }
        if (!UserStorage.getUser(login)){
            System.out.println("Превышен лимит попыток, возврат в главное меню!");
            return;
        }
        System.out.print("Введите старый пароль: ");
        String password = sc.nextLine();
        count = 0;
        while (count < 1 && !PasswordUtil.verifyPassword(login, password)){
            System.out.print("\nНеверный пароль!\nПовторите пороль: ");
            password = sc.nextLine();
            count += 1;
        }
        if (!PasswordUtil.verifyPassword(login, password)){
            System.out.println("Превышен лимит попыток, возврат в главное меню!");
            return;
        }
        System.out.print("Введите новый пароль: ");
        String new_password = sc.nextLine();
        System.out.print("Повторите пароль: ");
        String password_check = sc.nextLine();
        count = 0;
        while (count < 1 && !new_password.equals(password_check)){
            System.out.print("\nПароли не совпадают!\nПовторите пороль: ");
            password_check = sc.nextLine();
            count += 1;
        }
        if (!new_password.equals(password_check)){
            System.out.println("Превышен лимит попыток, возврат в главное меню!");
            return;
        } 
        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(new_password, salt);
        UserStorage.changeUserPassword(login, salt, hash);
        System.out.println("Пороль успешно изменен!");
    }
}
