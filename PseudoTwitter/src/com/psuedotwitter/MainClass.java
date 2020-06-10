package com.psuedotwitter;

import com.psuedotwitter.views.LoginSignupPage;

public class MainClass {

    public static void main(String[] args) {

        LoginSignupPage loginSignupPage = LoginSignupPage.createLoginSignupPage();
        loginSignupPage.render();

    }
}
