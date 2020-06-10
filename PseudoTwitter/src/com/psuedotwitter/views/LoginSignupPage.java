package com.psuedotwitter.views;

import com.psuedotwitter.models.UserManagement.User;

import java.util.Scanner;

public class LoginSignupPage {

    public LoginSignupPage() {
    }

    // Create LoginSignupPage
    public static LoginSignupPage createLoginSignupPage(){
        return new LoginSignupPage();
    }


    // Login
    private void login() {
        System.out.println("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        User user = User.login(username, password);

        if (user == null) {
            render();
        }
        else {
            HomePage homePage = HomePage.createHomePage(user);
            homePage.render();
        }

    }

    // Signup
    public void signup() {
        System.out.println("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();

        User user = User.signup(username, password, email);

        if (user == null) {
            render();
        }

        else {
            HomePage homePage = HomePage.createHomePage(user);
            homePage.render();
        }
    }

    // Render
    public void render() {
        // Clean Console
        System.out.println("\033[H\033[2J");
        System.out.flush();

        System.out.println("Welcome to Twitter(Kinda)!");
        System.out.println("1. Login\t2. Signup");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                signup();
                break;
            default:
                render();
                break;
        }

    }
}
