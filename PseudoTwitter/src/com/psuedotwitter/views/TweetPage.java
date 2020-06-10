package com.psuedotwitter.views;

import com.psuedotwitter.models.PostManagement.Comment;
import com.psuedotwitter.models.PostManagement.Interaction;
import com.psuedotwitter.models.PostManagement.Like;
import com.psuedotwitter.models.PostManagement.Post;
import com.psuedotwitter.models.UserManagement.User;

import java.util.Scanner;

public class TweetPage {

    private User currentUser;
    private Post post;

    public TweetPage(User currentUser, Post post) {
        this.currentUser = currentUser;
        this.post = post;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    // Create Tweet Page
    public static TweetPage createTweetPage(User user, Post post){
        return new TweetPage(user, post);
    }

    // Like
    private void like() {
        Like.like(currentUser, post);
        render();
    }

    // Dislike
    private void dislike() {
        Like.dislike(currentUser, post);
        render();
    }

    // Comment
    private void comment() {

        System.out.println("Enter your comment: ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        Comment.comment(currentUser, post, text);
        render();
    }


    // Render
    public void render() {

        System.out.println("\033[H\033[2J");
        System.out.flush();

        post.showPost();

        System.out.println("Comments: ");
        for(Interaction interaction: post.getInteractions()){
            if (interaction instanceof Comment){
                System.out.print("\t");
                ((Comment)interaction).showComment();
                System.out.println();
            }
        }

        boolean hasLiked = false;
        for(Interaction interaction : post.getInteractions()){
            if (interaction instanceof Like){
                if (interaction.getUser().getUsername().equals(currentUser.getUsername())){
                    hasLiked = true;
                    break;
                }
            }
        }

        if (!hasLiked) {
            System.out.println("1. Like\t2. Comment\t3. Back to Home");
        }
        else{
            System.out.println("1. Dislike\t2. Comment\t3. Back to Home");
        }

        System.out.println("Choose your option: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                if (!hasLiked) {
                    like();
                }
                else {
                    dislike();
                }
                break;
            case 2:
                comment();
                break;
            case 3:
                HomePage homePage = HomePage.createHomePage(currentUser);
                homePage.render();
                break;
            default:
                render();
                break;
        }

    }
}
