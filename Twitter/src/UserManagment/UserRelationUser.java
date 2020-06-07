package UserManagment;

import Twitter.Tweet;

import java.util.ArrayList;
import java.util.List;

public class UserRelationUser {
    private static List<UserRelationUser> database = new ArrayList<>();
    private User follower;
    private User following;

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public static List<UserRelationUser> getDatabase() {
        return database;
    }

    public static void setDatabase(List<UserRelationUser> database) {
        UserRelationUser.database = database;
    }

}
