package com.desitum.castleWars.objects;

import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.screens.GameScreen;
import com.desitum.castleWars.world.GameInterface;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class Player {
    private Castle castle;
    private Hand hand;

    public Player(GameInterface gameInterface, float castleX){
        if(GameScreen.SCREEN_WIDTH/2 > castleX) {
            if(Settings.ASSETS_TO_USE ==1) {
                castle = new Castle(Assets.playerCastle, gameInterface, castleX);
            } else if(Settings.ASSETS_TO_USE == 2) {
                castle = new Castle(Assets.playerFlameCastle, gameInterface, castleX);
            } else if(Settings.ASSETS_TO_USE == 3){
                castle = new Castle(Assets.playerJapaneseCastle, gameInterface, castleX);
            }
        } else {
            if(Settings.ASSETS_TO_USE ==1) {
                castle = new Castle(Assets.computerCastle, gameInterface, castleX);
            } else if(Settings.ASSETS_TO_USE == 2) {
                castle = new Castle(Assets.computerFlameCastle, gameInterface, castleX);
            }else if(Settings.ASSETS_TO_USE == 3){
                castle = new Castle(Assets.computerJapaneseCastle, gameInterface, castleX);
            }
        }

        hand = new Hand(gameInterface);
    }

    public Castle getCastle() {
        return castle;
    }

    public Hand getHand() {
        return hand;
    }


    public void update(float delta) {
        hand.update(delta);
        castle.update(delta);
    }
}
