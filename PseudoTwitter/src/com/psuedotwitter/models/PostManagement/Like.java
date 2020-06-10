package com.psuedotwitter.models.PostManagement;

import com.psuedotwitter.models.UserManagement.User;

public class Like extends Interaction {


    public Like(User user) {
        super(user);
    }


    // Like
    public static Like like(User user, Post post) {

        Like like = new Like(user);

        post.addInteraction(like);

        return like;

    }


    // Dislike
    public static void dislike(User user, Post post) {

        for(Interaction interaction : post.getInteractions()) {
            if (interaction instanceof Like) {
                if (interaction.getUser().getUsername().equals(user.getUsername())){
                    post.getInteractions().remove(interaction);
                    return ;
                }
            }
        }

    }
}
