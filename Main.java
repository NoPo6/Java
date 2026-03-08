package lab3;

import java.util.Scanner;
import lab3.AuthService;
import java.io.File;
import java.io.IOException;

public class Main {
    public void main(String[] args) {
        File file = new File("users.txt");

        if (!file.exists()) {
            file.createNewFile();
        }
        else {
            UserStorage.loadUsers();
        }
        mainMenu();
    }

    public void mainMenu(String[] args) {
        while (true) {

            System.out.println("Главное меню:\n" 
            + "1. Создать пользователя\n"
            + "2. Вход по логину и поролю\n"
            + "3. Смена пороля\n"
            + "4. Завершить программу\n");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();

            if (input == 4){
                UserStorage.saveUsers();
                break;
            }
            else if (input == 1) AuthService.registerUser();
            else if (input == 2) AuthService.loginUser();
            else AuthService.changePassword();
        }
    }
}
