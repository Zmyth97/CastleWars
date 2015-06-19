package com.desitum.castleWars.world;

/**
 * Created by kody on 4/7/15.
 */
public interface MenuInterface {

    public void playGame();

    public void settings();

    public void multiplayer(boolean host);

    public void buildDeck();

    public void achievements();

    public void store();

    public void buyItem(String sku);
}
