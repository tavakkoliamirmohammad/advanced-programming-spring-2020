package UserManagment;

import Twitter.TweetUser;

import java.util.ArrayList;
import java.util.List;

public class User extends TweetUser {

    private static List<User> users = new ArrayList<>();
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        User.users = users;
    }

    public void follow(User user) {
        List<UserRelationUser> userRelationUsers = UserRelationUser.getDatabase();

        for (UserRelationUser userRelationUser : userRelationUsers) {
            if (userRelationUser.getFollower() == this && userRelationUser.getFollowing() == user) {
                userRelationUsers.remove(userRelationUser);
                return;
            }
        }

        UserRelationUser userRelationUser = new UserRelationUser();
        userRelationUser.setFollower(this);
        userRelationUser.setFollowing(user);
    }

    public List<User> getFollowers() {
        List<User> followers = new ArrayList<>();
        List<UserRelationUser> userRelationUsers = UserRelationUser.getDatabase();

        for (UserRelationUser userRelationUser : userRelationUsers) {
            if (userRelationUser.getFollowing() == this) {
                followers.add(userRelationUser.getFollower());
            }
        }
        return followers;
    }

    public List<User> getFollowing() {
        List<User> following = new ArrayList<>();
        List<UserRelationUser> userRelationUsers = UserRelationUser.getDatabase();

        for (UserRelationUser userRelationUser : userRelationUsers) {
            if (userRelationUser.getFollower() == this) {
                following.add(userRelationUser.getFollowing());
            }
        }
        return following;
    }


    public static User signUp(String username, String password){
        for (User user : users){
            if(user.getUsername().equals(username)){
                return null;
            }
        }
        User user = new User(username, password);
        users.add(user);
        return user;
    }

    public static User login(String username, String password){
        for(User user: users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public static User searchByUsername(String username){
        for(User user: users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }



}
