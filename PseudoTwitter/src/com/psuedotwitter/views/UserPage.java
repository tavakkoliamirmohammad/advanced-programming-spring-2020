package com.psuedotwitter.views;

import com.psuedotwitter.models.PostManagement.Post;
import com.psuedotwitter.models.UserManagement.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserPage {

    private User currentUser;
    private User user;


    public UserPage(User currentUser, User user) {
        this.currentUser = currentUser;
        this.user = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // Create User Page
    public static UserPage createUserPage(User currentUser, User user) {
        return new UserPage(currentUser, user);
    }


    // Follow
    private void follow() {
        currentUser.follow(user.getUsername());
        render();
    }

    // Unfollow
    private void unfollow() {
        currentUser.unfollow(user.getUsername());
        render();
    }

    // Render
    public void render() {

        System.out.println("\033[H\033[2J");
        System.out.flush();

        System.out.println(user.getUsername() + "'s Page: ");
        Post.showUserPosts(user);
        ArrayList<Post> userPosts = Post.userPosts(user);

        if (currentUser.hasFollowed(user)){
            System.out.println("1. Unfollow\t 2. Select a tweet\t3. Back to Home");
        }
        else {
            System.out.println("1. Follow\t 2. Select a tweet\t3. Back to Home");
        }


        System.out.println("Choose your option: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                if (currentUser.hasFollowed(user)) {
                    unfollow();
                }
                else {
                    follow();
                }
                break;
            case 2:
                System.out.println("Enter the tweet number: ");
                int index = scanner.nextInt();
                scanner.nextLine();
                TweetPage tweetPage = TweetPage.createTweetPage(currentUser, userPosts.get(index - 1));
                tweetPage.render();
                break;
            case 3:
                HomePage homePage = HomePage.createHomePage(currentUser);
                homePage.render();
                break;
        }
    }
}
