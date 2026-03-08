package lab3;

import lab3.AuthService;
import lab3.UserStorage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("users.txt");

        if (!file.exists()) {
            file.createNewFile();
        } else {
            UserStorage.loadUsers(file);
        }

        mainMenu();
    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Главное меню:\n"
                    + "1. Создать пользователя\n"
                    + "2. Вход по логину и паролю\n"
                    + "3. Смена пароля\n"
                    + "4. Завершить программу\n");

            int input = sc.nextInt();

            if (input == 4) {
                break;
            } else if (input == 1) {
                AuthService.registerUser();
            } else if (input == 2) {
                AuthService.loginUser();
            } else if (input == 3) {
                AuthService.changePassword();
            }
        }
        sc.close();
    }
}