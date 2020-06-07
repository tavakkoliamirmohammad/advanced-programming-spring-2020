package UserView;

import UserManagment.User;

import java.io.IOException;
import java.util.Scanner;

public class UserRegisterPage {

    private static Scanner scanner = new Scanner(System.in);

    private void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("1. login");
        System.out.println("2. sign up");
        System.out.println("3. exit");
    }

    public void run() {
        User user;
        System.out.println("Welcome to my twitter!");
        while (true) {
            showMenu();
            System.out.println("choice an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 3) {
                System.out.println("Good Bye!");
                return;
            }
            if (choice == 1) {
                user = login();
                if(user == null){
                    System.out.println("User not found!");
                    continue;
                }
                UserPage userPage = new UserPage(user);
                userPage.run();
            } else if (choice == 2) {
                signUp();
            }
        }
    }

    private User login() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        return User.login(username, password);
    }

    private void signUp() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        User.signUp(username, password);
    }
}
