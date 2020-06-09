package com.psuedotwitter.models.UserManagement;

import com.psuedotwitter.models.PostManagement.Post;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private String email;

    private ArrayList<UserRelation> userRelations;
    private ArrayList<Post> posts;

    private static ArrayList<User> userDatabase = new ArrayList<>();


    private User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<UserRelation> getUserRelations() {
        return userRelations;
    }

    public void setUserRelations(ArrayList<UserRelation> userRelations) {
        this.userRelations = userRelations;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    // Create User
    public static User createUser(String username, String password, String email) {
        return new User(username, password, email);
    }

    // Login
    public static User login(String username, String password){

        for(User user: userDatabase){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }

        return null;
    }


    // Sign up
    public static User signup(String username, String password, String email){

        for(User user: userDatabase){
            if (user.getUsername().equals(username)){
                return null;
            }
        }

        User newUser = User.createUser(username, password, email);

        userDatabase.add(newUser);

        return newUser;
    }

    // Search User
    public static User searchUser(String username) {

        for(User user: userDatabase){
            if (user.getUsername().equals(username)){
                return user;
            }
        }

        return null;
    }

    // Follow
    public void follow(String username) {

        User user = User.searchUser(username);

        if (user == null){
        }
        else {
            UserRelation userRelation = new UserRelation(user);
            userRelations.add(userRelation);
        }
    }


    // Unfollow
    public void unfollow(String username) {

        for(UserRelation userRelation : userRelations) {
            if (userRelation.getUser().getUsername().equals(username)){
                userRelations.remove(userRelation);
                return ;
            }
        }

    }


    // Post
    public void post(String text) {
        // add post
    }


    // Has Followed
    public boolean hasFollowed(User user) {

        for(UserRelation userRelation : userRelations){
            if (userRelation.getUser().getUsername().equals(user.getUsername())){
                return true;
            }
        }

        return false;
    }

}
