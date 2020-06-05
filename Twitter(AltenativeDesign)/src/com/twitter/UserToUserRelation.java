package com.twitter;

public class UserToUserRelation {
    private User user;
    private UserToUserRelationType userToUserRelationType;

    public UserToUserRelation(User user, UserToUserRelationType userToUserRelationType) {
        this.user = user;
        this.userToUserRelationType = userToUserRelationType;
    }

    public User getUser() {
        return user;
    }

    public UserToUserRelationType getUserToUserRelationType() {
        return userToUserRelationType;
    }
}
