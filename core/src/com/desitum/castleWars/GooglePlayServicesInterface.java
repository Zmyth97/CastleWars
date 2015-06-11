package com.desitum.castleWars;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public interface GooglePlayServicesInterface {

    public void getLeaderBoard();
    public void submitScore(int score);
    public void unlockAchievement(int achievement);
    public void login();
    public void logout();
    public void shareRegularScore(int score);
}
