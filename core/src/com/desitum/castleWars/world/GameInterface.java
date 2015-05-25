package com.desitum.castleWars.world;

import com.desitum.castleWars.data.Resources;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Player;

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
    public void removeWidgetFromWorld(PopupWidget widget);
    public void addWidgetToWorld(PopupWidget widget);
}
