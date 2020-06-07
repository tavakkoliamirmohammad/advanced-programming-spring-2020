package com.twitter;

import java.util.*;

public class User {
    //Static properties
    private static ArrayList<User> createdUsers = new ArrayList<>();
    private final Date creationDate;
    //Dynamic properties
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Date birthDate;
    private Date lastModificationDate;
    private ArrayList<UserToUserRelation> userToUserRelations;
    private ArrayList<UserToTweetRelation> userToTweetRelations;

    public User(String firstName, String lastName, String username, String password, String email, Date birthDate) {
        for(User u: createdUsers){
            if(u.getUsername().equals(username))
                throw new RuntimeException("Username is taken by another user");
            if(u.getEmail().equals(email))
                throw new RuntimeException("Email is already used by another user");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.creationDate = new Date();
        this.userToUserRelations = new ArrayList<UserToUserRelation>();
        this.userToTweetRelations = new ArrayList<UserToTweetRelation>();
        createdUsers.add(this);
    }

    public static ArrayList<User> getCreatedUsers() {
        return createdUsers;
    }

    public static User login(String emailOrPassword, String password){
        for(User u: createdUsers){
            if(u.getPassword().equals(password) && (u.getEmail().equals(emailOrPassword) || (u.getUsername().equals(emailOrPassword)))){
                return u;
            }
        }
        return null;
    }

    public static ArrayList<Tweet> showTweetsByUser(String username){
        ArrayList<Tweet> tweets = new ArrayList<>();
        User u;
        if((u = searchForUser(username)) != null){
            for(UserToTweetRelation utr: u.getUserToTweetRelations()){
                if(utr.getUserToTweetRelationType().equals(UserToTweetRelationType.POST)){
                    tweets.add(utr.getTweet());
                }
            }
        }
        Collections.sort(tweets, new TimeComparator());
        return tweets;
    }

    private static User searchForUser(String usernameOrEmail){
        for(User u: createdUsers){
            if(u.getEmail().equals(usernameOrEmail) || u.getUsername().equals(usernameOrEmail)){
                return u;
            }
        }
        return null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.lastModificationDate = new Date();
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastModificationDate = new Date();
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.lastModificationDate = new Date();
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.lastModificationDate = new Date();
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.lastModificationDate = new Date();
        this.birthDate = birthDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    private String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.lastModificationDate = new Date();
        this.password = password;
    }

    public ArrayList<UserToTweetRelation> getUserToTweetRelations() {
        return userToTweetRelations;
    }

    public ArrayList<UserToUserRelation> getUserToUserRelations() {
        return userToUserRelations;
    }

    //User function
    public ArrayList<User> showFollowers(){
        ArrayList<User> followers = new ArrayList<User>();
        for(User u: createdUsers){
            for(UserToUserRelation uur: u.getUserToUserRelations()){
                if(uur.getUser().equals(this) && uur.getUserToUserRelationType().equals(UserToUserRelationType.FOLLOW)){
                    followers.add(u);
                }
            }
        }
        return followers;
    }

    public void postNewTweet(String content) throws RuntimeException {
        if(content.isEmpty())
            throw new RuntimeException("Tweet can't be empty");
        Tweet tweet = new Tweet(content);
        UserToTweetRelation uut = new UserToTweetRelation(tweet, UserToTweetRelationType.POST);
        userToTweetRelations.add(uut);
    }

    public void likeTweet(Tweet tweet) throws NullPointerException{
        if(tweet == null)
            throw new NullPointerException("Tweet doesn't exist");
        UserToTweetRelation uut = new UserToTweetRelation(tweet, UserToTweetRelationType.LIKE);
        userToTweetRelations.add(uut);
    }

    public void commentOnTweet(Tweet tweet, String comment) throws NullPointerException, RuntimeException{
        if(tweet == null)
            throw new NullPointerException("Tweet doesn't exits");
        if(comment.isEmpty())
            throw new RuntimeException("Comment can't be empty");
        Tweet uTweet = new Tweet(comment, tweet);
        UserToTweetRelation utr = new UserToTweetRelation(uTweet, UserToTweetRelationType.COMMENT);
        userToTweetRelations.add(utr);
    }

    public void followUser(User user) throws NullPointerException{
        if(user == null)
            throw new NullPointerException("User doesn't Exist");
        UserToUserRelation uur = new UserToUserRelation(user, UserToUserRelationType.FOLLOW);
        userToUserRelations.add(uur);
    }

    public ArrayList<Tweet> showTimeline(){
        ArrayList<Tweet> tweets = new ArrayList<>();
        for(UserToUserRelation uur: userToUserRelations){
            if(uur.getUserToUserRelationType().equals(UserToUserRelationType.FOLLOW)){
                for(UserToTweetRelation utr: uur.getUser().getUserToTweetRelations()){
                    if(utr.getUserToTweetRelationType().equals(UserToTweetRelationType.POST))
                        tweets.add(utr.getTweet());
                }
            }
        }
        Collections.sort(tweets, new TimeComparator());
        return tweets;
    }
}