package UserView;

import Twitter.Tweet;
import Twitter.TweetType;
import Twitter.TweetUserRelation;
import UserManagment.User;

import java.io.IOException;
import java.util.Scanner;

public class TweetPage {
    private Tweet tweet;
    private User user;
    private static Scanner scanner = new Scanner(System.in);

    public TweetPage(Tweet tweet, User user) {
        this.tweet = tweet;
        this.user = user;
    }

    private void showMenu() {
        System.out.println("1. Like tweet");
        System.out.println("2. Comment tweet");
        System.out.println("3. Favorite tweet");
        System.out.println("4. Re tweet");
        System.out.println("5. Exit");
    }

    public void run() {
        while (true) {
            showTweet();
            showMenu();
            System.out.println("choice an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 5) {
                return;
            }
            if (choice == 1) {
                user.likeTweet(tweet);
            } else if (choice == 2) {
                commentTweet();
            } else if (choice == 3) {
                favoriteTweet();
            } else if (choice == 4) {
                reTweet();
            }
        }
    }

    private void showTweet() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println(tweet.getText());
        boolean liked = false;
        boolean favorite = false;
        for (TweetUserRelation tweetUserRelation : tweet.getTweetUserRelations()) {
            if (tweetUserRelation.getTweetUser() == user) {
                liked = tweetUserRelation.isLiked();
                favorite = tweetUserRelation.isFavorite();
                break;
            }
        }
        System.out.println("Liked: " + liked);
        System.out.println("Favorite: " + favorite);
        showComments();

    }

    private void commentTweet() {
        String text = scanner.nextLine();
        Tweet commentTweet = new Tweet(text);
        commentTweet.setTweetType(TweetType.Comment);
        user.makeTweet(commentTweet, tweet);
    }

    private void reTweet() {
        Tweet myTweet = new Tweet(tweet.getText());
        myTweet.setTweetType(TweetType.ReTweet);
        user.makeTweet(myTweet, tweet);
    }

    private void favoriteTweet() {
        user.favoriteTweet(tweet);
    }

    private void showComments() {
        for (Tweet t : tweet.getComments()) {
            System.out.println(t.getText());
        }
    }
}
