package com.desitum.castleWars.world;

import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Resources;

/**
 * Created by kody on 4/7/15.
 * can be used by kody and people in []
 */
public interface GameInterface {
    public void onClickCard(Card card, int cardID);
    public Resources getResources();
    public int getPlayerTurn();
}
