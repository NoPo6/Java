package lab3;

import lab3.AuthService;
import lab3.UserStorage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // инициализация файла
        File file = new File("users.txt");

        // проверка существования файла, если не существует, то создаем
        if (!file.exists()) {
            file.createNewFile();
        } else {
            UserStorage.loadUsers(file);
        }

        //запуск главного меню
        mainMenu();
    }

    // главное меню
    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);

        // бесконеычный цикл главного меню
        while (true) {
            // опции главного меню
            System.out.println("Главное меню:\n"
                    + "1. Создать пользователя\n"
                    + "2. Вход по логину и паролю\n"
                    + "3. Смена пароля\n"
                    + "4. Завершить программу\n");

            int input = sc.nextInt();

            // реакция на выбор в главном меню
            if (input == 4) {
                break;                                  // завершение программы
            } else if (input == 1) {
                AuthService.registerUser();             // регистрация нового пользователя
            } else if (input == 2) {
                AuthService.loginUser();                // вход по логину и поролю
            } else if (input == 3) {
                AuthService.changePassword();           // смена пороля
                UserStorage.saveUsersToFile();          // перезапись файла
            }
        }
        sc.close();
    }
}