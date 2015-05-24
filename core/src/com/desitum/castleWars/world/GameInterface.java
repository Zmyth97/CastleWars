package com.desitum.castleWars.world;

import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Player;
import com.desitum.castleWars.data.Resources;

/**
 * Created by kody on 4/7/15.
 * can be used by kody and people in []
 */
public interface GameInterface {
    public void onClickCard(Card card);
    public Resources getResources();
    public int getPlayerTurn();
    public Player getPlayer1();
    public Player getPlayer2();
    public void addCardToWorld(Card card);
    public void removeCardFromWorld(Card card);
}
