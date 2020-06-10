package com.psuedotwitter.views;

import com.psuedotwitter.models.PostManagement.Post;
import com.psuedotwitter.models.UserManagement.User;

import java.util.ArrayList;
import java.util.Scanner;

public class HomePage {

    private User currentUser;

    public HomePage(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    // Create Home Page
    public static HomePage createHomePage(User user){
        return new HomePage(user);
    }

    // Tweet
    private void tweet(){
        System.out.println("\033[H\033[2J");
        System.out.flush();

        System.out.println("Enter your tweet: ");
        Scanner scanner = new Scanner(System.in);
        String tweet = scanner.nextLine();

        currentUser.post(tweet);
    }

    // Search For User
    private void searchForUser() {
        System.out.println("\033[H\033[2J");
        System.out.flush();

        System.out.println("Enter the username of the user you want to search for: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        User user = User.searchUser(username);

        if ( user == null ){
            render();
        }

        else {
            UserPage userPage = UserPage.createUserPage(currentUser, user);
            userPage.render();
        }

    }


    // Logout
    private void logout() {

        LoginSignupPage loginSignupPage = LoginSignupPage.createLoginSignupPage();
        loginSignupPage.render();

    }



    // Render
    public void render() {

        System.out.println("\033[H\033[2J");
        System.out.flush();

        System.out.println("Home page for " + currentUser.getUsername());
        System.out.println();
        System.out.println();
        Post.showPostForUser(currentUser);
        ArrayList<Post> posts = Post.postsForUser(currentUser);
        System.out.println();
        System.out.println("1. Tweet\t2. Select Tweet\t3.Search for a user\t4. Logout\t5. Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                tweet();
                render();
                break;
            case 2:
                System.out.println("Choose the tweet number: ");
                int index = scanner.nextInt();
                scanner.nextLine();
                Post post = posts.get(index - 1);
                TweetPage tweetPage = TweetPage.createTweetPage(currentUser, post);
                tweetPage.render();
                break;
            case 3:
                searchForUser();
                break;
            case 4:
                logout();
                break;
            case 5:
                return;
            default:
                render();
                break;
        }


    }


}
