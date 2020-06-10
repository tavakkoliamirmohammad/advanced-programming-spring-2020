package com.psuedotwitter.models.PostManagement;

import com.psuedotwitter.models.UserManagement.User;
import com.psuedotwitter.models.UserManagement.UserRelation;

import java.util.ArrayList;
import java.util.Comparator;

public class Post {

    private String text;

    private ArrayList<Interaction> interactions;

    public Post(String text) {
        this.text = text;
        this.interactions = new ArrayList<>();
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(ArrayList<Interaction> interactions) {
        this.interactions = interactions;
    }


    // Show Post
    public void showPost() {

        // TODO: show username as well
        System.out.println(text);
        System.out.println(this.countLikes() + " Likes " + this.countComments() + " Comments");
    }


    // Add Interaction
    public void addInteraction(Interaction interaction) {

        interactions.add(interaction);

    }


    // Show Post for User
    public static void showPostForUser(User user) {

        ArrayList<Post> userPosts = postsForUser(user);

        if (userPosts.isEmpty()){
            System.out.println("No Posts yet!");
        }

        else {
            int count = 1;
            for(Post post: userPosts) {
                System.out.print(count + ". ");
                post.showPost();
                System.out.println();
                ++count;
            }

        }


    }


    // Show User Posts
    public static void showUserPosts(User user) {

        ArrayList<Post> userPosts = userPosts(user);


        if (userPosts.isEmpty()){
            System.out.println("No Posts yet!");
        }

        else {
            int count = 1;
            for(Post post: userPosts){
                System.out.print(count + ". ");
                post.showPost();
                System.out.println();
                ++count;
            }
        }

    }


    // Count Likes
    public int countLikes() {

        int count = 0;
        for(Interaction interaction : interactions) {
            if (interaction instanceof Like) {
                ++count;
            }
        }

        return count;

    }


    // Count Comments
    public int countComments() {

        int count = 0;
        for(Interaction interaction: interactions){
            if (interaction instanceof Comment) {
                ++count;
            }
        }

        return count;
    }


    // Posts for User
    public static ArrayList<Post> postsForUser(User user) {

        ArrayList<Post> userPosts = new ArrayList<>(user.getPosts());

        for(UserRelation userRelation : user.getUserRelations()) {
            userPosts.addAll(userRelation.getUser().getPosts());
        }


        userPosts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post post1, Post post2) {
                return Integer.compare(post1.countLikes(), post2.countLikes());
            }
        });

        return userPosts;
    }

    // User Posts
    public static ArrayList<Post> userPosts(User user) {

        ArrayList<Post> userPosts = new ArrayList<>(user.getPosts());

        userPosts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post post1, Post post2) {
                return Integer.compare(post1.countLikes(), post2.countLikes());
            }
        });

        return userPosts;

    }


}
