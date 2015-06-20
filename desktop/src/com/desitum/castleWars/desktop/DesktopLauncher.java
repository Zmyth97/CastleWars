package com.desitum.castleWars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.GooglePlayServicesInterface;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new CastleWars(new GooglePlayServicesInterface() {
            @Override
            public void getLeaderBoard() {

            }

            @Override
            public void submitScore(int score) {

            }

            @Override
            public void unlockAchievement(int achievement) {

            }

            @Override
            public void makePurchase(String sku) {

            }

            @Override
            public void checkForPurchasesMade() {

            }

            @Override
            public void showAchievements(){

            }

            @Override
            public void login() {

            }

            @Override
            public void logout() {

            }

            @Override
            public void shareRegularScore(int score) {

            }

        }), config);
	}
}
