package UserView;

import Twitter.Tweet;
import Twitter.TweetType;
import UserManagment.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserPage {
    private static Scanner scanner = new Scanner(System.in);
    private User user;

    public UserPage(User user) {
        this.user = user;
    }

    private void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignored) {

        }
        System.out.println("1. Make tweet.");
        System.out.println("2. Show my tweets");
        System.out.println("3. Show all tweets");
        System.out.println("4. Search by keyword");
        System.out.println("5. log out");
    }

    private Tweet makeTweet() {
        String text = scanner.nextLine();
        Tweet tweet = new Tweet(text);
        tweet.setTweetType(TweetType.OrdinaryTweet);
        user.makeTweet(tweet);
        return tweet;
    }

    public void run() {
        while (true) {
            showMenu();
            System.out.println("choice an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 5) {
                return;
            }
            if (choice == 1) {
                makeTweet();
            } else if (choice == 2) {
                showMyTweets();
            } else if (choice == 3) {
                showAllTweets();
            } else if (choice == 4) {
                searchByKeyword();
            }
        }
    }

    private void showMyTweets() {
        List<Tweet> tweets = user.getTweets();
        showTweets(tweets);
    }

    private void showAllTweets() {
        List<User> users = user.getFollowers();
        List<Tweet> tweets = new ArrayList<>();
        for (User user : users) {
            tweets.addAll(user.getTweets());
        }
        showTweets(tweets);
    }

    private void searchByKeyword() {
        System.out.println("Enter the keyword: ");
        String keyword = scanner.nextLine();
        List<Tweet> tweets = Tweet.searchByKeyword(keyword);
        showTweets(tweets);
    }

    private void showTweets(List<Tweet> tweets) {
        if(tweets.isEmpty()){
            System.out.println("Empty!");
            return;
        }
        int i = 0;
        for (Tweet tweet : tweets) {
            System.out.println((i + 1) + ". " + tweet.getText());
            ++i;
        }
        System.out.println("Choice a tweet by number (-1 to exit): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == -1) {
            return;
        }
        --choice;
        if(choice >= tweets.size()){
            System.out.println("Invalid option");
            return;
        }
        Tweet tweet = tweets.get(choice);
        TweetPage tweetPage = new TweetPage(tweet, user);
        tweetPage.run();
    }
}
