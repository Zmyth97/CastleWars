package com.desitum.castleWars;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public interface GooglePlayServicesInterface {

    public void getLeaderBoard();

    public void submitScore(int score);

    public void unlockAchievement(int achievement);

    public void makePurchase(String sku);

    public void checkForPurchasesMade();

    public void showAchievements();

    public void login();

    public void logout();

    public void hostMultiplayer();

    public void joinMultiplayer();

    public void shareRegularScore(int score);
}
