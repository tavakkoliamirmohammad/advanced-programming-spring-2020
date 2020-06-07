package com.twitter;

public class UserToTweetRelation {
    private Tweet tweet;
    private UserToTweetRelationType userToTweetRelationType;

    public UserToTweetRelation(Tweet tweet, UserToTweetRelationType userToTweetRelationType) {
        this.tweet = tweet;
        this.userToTweetRelationType = userToTweetRelationType;
    }

    public Tweet getTweet(){
        return tweet;
    }

    public UserToTweetRelationType getUserToTweetRelationType() {
        return userToTweetRelationType;
    }
}
