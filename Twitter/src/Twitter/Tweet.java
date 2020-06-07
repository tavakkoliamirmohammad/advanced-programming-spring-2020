package Twitter;

import UserManagment.User;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    private String text;
    private List<Tweet> tweets = new ArrayList<>();
    private List<TweetUserRelation> tweetUserRelations = new ArrayList<>();
    private TweetType tweetType;

    public Tweet(String text) {
        if (text.length() > 140) {
            throw new IllegalArgumentException();
        }
        this.text = text;
    }


    public TweetType getTweetType() {
        return tweetType;
    }

    public void setTweetType(TweetType tweetType) {
        this.tweetType = tweetType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<TweetUserRelation> getTweetUserRelations() {
        return tweetUserRelations;
    }

    public void setTweetUserRelations(List<TweetUserRelation> tweetUserRelations) {
        this.tweetUserRelations = tweetUserRelations;
    }

    public static List<Tweet> searchByKeyword(String keyword){
        List<User> users = User.getUsers();
        List<Tweet> foundTweets = new ArrayList<>();
        for(User user : users){
            for(Tweet tweet: user.getTweets()){
                if(tweet.getText().contains(keyword)){
                    foundTweets.add(tweet);
                }
            }
        }
        return foundTweets;
    }

    public List<Tweet> getComments(){
        List<Tweet> comments = new ArrayList<>();
        for(Tweet tweet: tweets){
            if(tweet.getTweetType() == TweetType.Comment){
                comments.add(tweet);
            }
        }
        return comments;
    }

    public List<Tweet> getReTweet(){
        List<Tweet> reTweet = new ArrayList<>();
        for(Tweet tweet: tweets){
            if(tweet.getTweetType() == TweetType.ReTweet){
                reTweet.add(tweet);
            }
        }
        return reTweet;
    }
}
