package com.twitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        User u1 = new User("mh", "akbari", "mh2033", "blabblab", "mh.akbari@yahoo.com", new Date());
        User u2 = new User("pooya", "fekri", "codersdontcry", "blabblab", "pooya.af@yahoo.com", new Date());
        u1.postNewTweet("Keep calm and use IntelliJ");
        u1.postNewTweet("Screw eclipse!");
        u2.followUser(u1);
        u1.followUser(u2);
        u2.postNewTweet("Smoke weed everyday \\m/");
        u1.commentOnTweet(u2.getUserToTweetRelations().get(0).getTweet(), "Cool man!");

        Scanner scanner = new Scanner(System.in);
        boolean exitFlag = false;
        while (true) {
            clearScreen();
            System.out.printf("1.Sign-up\n2.Sign-in\n3.Search\n4.Exit\n");
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input){
                case 1:
                    System.out.printf("Enter your firstName: ");
                    String firstName = scanner.nextLine();
                    System.out.printf("Enter your lastName: ");
                    String lastName = scanner.nextLine();
                    System.out.printf("Enter your birthDate: (yyyy-mm-dd): ");
                    Date birthDate = new SimpleDateFormat("yyyy-mm-dd").parse(scanner.nextLine());
                    System.out.printf("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.printf("Enter Username: ");
                    String username = scanner.nextLine();
                    System.out.printf("Enter password: ");
                    String password = scanner.nextLine();
                    try {
                        new User(firstName, lastName, username, password, email, birthDate);
                        System.out.printf("User has been created\n");
                    }
                    catch (RuntimeException e) {
                        System.out.printf("Error in user creation try again \n");
                    }
                    break;
                case 2:
                    System.out.printf("Enter username or email: ");
                    String usernameOrEmail = scanner.nextLine();
                    System.out.printf("Enter password: ");
                    password = scanner.nextLine();
                    User u;
                    if((u = User.login(usernameOrEmail, password)) != null) {
                        System.out.println("1.Show timeline\n");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice){
                            case 1:
                                for(Tweet tweet: u.showTimeline()){
                                    System.out.println(tweet);
                                    System.out.printf("\n1.Like   2.Comment   3.Show comments   4.Show next tweet");
                                    int uchoice = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (uchoice){
                                        case 1:
                                            u.likeTweet(tweet);
                                            System.out.printf("\nYou liked the tweet! press enter to see next tweet");
                                            scanner.nextLine();
                                            break;
                                        case 2:
                                            System.out.printf("\nType your comment then press enter: ");
                                            String comment = scanner.nextLine();
                                            u.commentOnTweet(tweet, comment);
                                            System.out.printf("\nYour comment has been published successfully! press enter to see next tweet");
                                            scanner.nextLine();
                                            break;
                                        case 3:
                                            System.out.printf("\nComments: \n");
                                            for(Tweet tweet1: tweet.showComments()){
                                                System.out.println(tweet1);
                                                System.out.printf("\nPress enter to see next comment");
                                                scanner.nextLine();
                                            }
                                            break;
                                        case 4:
                                            break;
                                    }
                                }
                        }
                    }
                    else
                        System.out.printf("Wrong Credentials! try again\n");
                    break;
                case 3:
                    System.out.printf("\n\n1.Search by keyword\n2.Search by user\n");
                    int inp = scanner.nextInt();
                    scanner.nextLine();
                    switch (inp){
                        case 1:
                            System.out.printf("Enter a keyword: ");
                            String keyword = scanner.nextLine();
                            System.out.printf("Tweets containing this keyword: \n");
                            for(Tweet tweet: Tweet.searchByKeyword(keyword)) {
                                System.out.println(tweet);
                                System.out.printf("\nPress Enter to Show next tweet");
                                scanner.nextLine();
                            }
                            break;
                        case 2:
                            System.out.printf("Enter username: ");
                            String uname = scanner.nextLine();
                            for(Tweet tweet: User.showTweetsByUser(uname)){
                                System.out.println(tweet);
                                System.out.printf("\nPress Enter to Show next tweet");
                                scanner.nextLine();
                            }
                            break;
                    }
                    break;
                case 4:
                    exitFlag = true;
                    break;
            }
            if(exitFlag)
                break;
        }
    }
    public static void clearScreen() {
    	for(int i= 0; i < 100; i++)
    		System. out. print("\n");
    }
}