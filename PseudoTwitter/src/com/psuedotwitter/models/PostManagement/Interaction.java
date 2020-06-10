package com.psuedotwitter.models.PostManagement;

import com.psuedotwitter.models.UserManagement.User;

abstract public class Interaction {

    private User user;

    public Interaction(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
