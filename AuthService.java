package lab3;

import java.util.Scanner;
import Lab3.UserStorage;

public class AuthService {
    
    Scanner sc = new Scanner(System.in);

    public void registerUser(String[] args) {
        System.out.print("Введите логин: ");
        String login = sc.nextLine();
        System.out.print("Введите пороль: ");
        String password = sc.nextLine();
        System.out.print("Повторите пороль: ");
        String password_check = sc.nextLine();
        int count = 0;
        while (count < 4 && password != password_check){
            System.out.print("Пороли не совпадают!\nПовторите пороль: ");
            password_check = sc.nextLine();
            count += 1;
        }
        if (count == 4){
            System.out.println("Превышен лимит попыток, возврат в главное меню!");
            Main.mainMenu();
        } 

        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(password, salt);
        UserStorage.addUser(login, salt, hash);
        System.out.println("Пользователь " + login + " успешно создан!");
    }

    public static void loginUser(String[] args) {
        
        System.out.println("Вход выполнен успешно!");
    }

    public static void changePassword(String[] args) {
        
        System.out.println("Пороль успешно изменен!");
    }

    public static boolean checkLoginExists(String[] args) {
        
        retrun is_exist;
    }

    public static boolean verifyOldPassword(String[] args) {
        
        retrun is_exist;
    }
}
