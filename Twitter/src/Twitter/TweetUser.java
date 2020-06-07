package Twitter;

import UserManagment.UserRelationUser;

import java.util.ArrayList;
import java.util.List;

public abstract class TweetUser {

    private List<Tweet> tweets = new ArrayList<>();

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void makeTweet(Tweet tweet) throws IllegalArgumentException {
        if (tweet.getTweetType() != TweetType.OrdinaryTweet) {
            throw new IllegalArgumentException();
        }
        tweets.add(tweet);
    }

    public void makeTweet(Tweet tweet, Tweet relatedTweet) {
        if (tweet.getTweetType() == TweetType.OrdinaryTweet) {
            throw new IllegalArgumentException();
        }

        relatedTweet.getTweets().add(tweet);
        tweets.add(tweet);
    }

    public void likeTweet(Tweet tweet) {
        List<TweetUserRelation> tweetUserRelations = tweet.getTweetUserRelations();
        for (TweetUserRelation tweetUserRelation : tweetUserRelations) {
            if(tweetUserRelation.getTweetUser() == this){
                boolean liked = tweetUserRelation.isLiked();
                tweetUserRelation.setLiked(!liked);
                return;
            }
        }
        TweetUserRelation tweetUserRelation = new TweetUserRelation();
        tweetUserRelation.setLiked(true);
        tweetUserRelation.setFavorite(false);
        tweetUserRelation.setTweetUser(this);
        tweet.getTweetUserRelations().add(tweetUserRelation);
    }
    public void favoriteTweet(Tweet tweet){
        List<TweetUserRelation> tweetUserRelations = tweet.getTweetUserRelations();
        for (TweetUserRelation tweetUserRelation : tweetUserRelations) {
            if(tweetUserRelation.getTweetUser() == this){
                boolean favorite = tweetUserRelation.isFavorite();
                tweetUserRelation.setFavorite(!favorite);
                return;
            }
        }
        TweetUserRelation tweetUserRelation = new TweetUserRelation();
        tweetUserRelation.setLiked(false);
        tweetUserRelation.setFavorite(true);
        tweetUserRelation.setTweetUser(this);
        tweet.getTweetUserRelations().add(tweetUserRelation);
    }

}
