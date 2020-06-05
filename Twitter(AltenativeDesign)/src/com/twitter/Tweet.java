package com.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Tweet {
    private static ArrayList<Tweet> tweets = new ArrayList<>();
    private final Date publishDate;
    private String content;
    private Tweet tweet;

    public static ArrayList<Tweet> searchByKeyword(String keyword){
        ArrayList<Tweet> tweetss = new ArrayList<>();
        for(Tweet tweet: tweets){
            if(tweet.getContent().contains(keyword))
                tweetss.add(tweet);
        }
        Collections.sort(tweetss, new TimeComparator());
        return tweetss;
    }

    public Tweet(String content) {
        this.tweets = tweets;
        this.publishDate = new Date();
        this.content = content;
        tweets.add(this);
    }

    public Tweet(String content, Tweet tweet) {
        this.tweets = tweets;
        this.publishDate = new Date();
        this.content = content;
        this.tweet = tweet;
        tweets.add(this);
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getContent() {
        return content;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public ArrayList<Tweet> showComments(){
        ArrayList<Tweet> tweets = new ArrayList<>();
        for(User u: User.getCreatedUsers()){
            for(UserToTweetRelation utr: u.getUserToTweetRelations()){
                if(utr.getUserToTweetRelationType().equals(UserToTweetRelationType.COMMENT) && utr.getTweet().equals(this)){
                    tweets.add(utr.getTweet());
                }
            }
        }

        Collections.sort(tweets, new TimeComparator());
        return tweets;
    }

    @Override
    public String toString() {
        String sender = null;
        for(User user: User.getCreatedUsers()){
            for(UserToTweetRelation utr: user.getUserToTweetRelations()){
                if(utr.getTweet().equals(this))
                    sender = user.getUsername();
            }
        }
        return "@"+ sender + "     " + getPublishDate() + "\n\n" + getContent()+ "\n";
    }
}
