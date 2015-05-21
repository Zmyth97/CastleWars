package com.desitum.castleWars.data;

import com.badlogic.gdx.Game;
import com.desitum.castleWars.world.GameInterface;
import com.desitum.castleWars.world.GameWorld;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class CardActions {
    private GameInterface gi;


    //region Build Cards
    public static final int REINFORCE = 1; //+6 Castle
    public static final int FORTIFY = 2; //+12 Castle
    public static final int CASTLE = 3; //+20 Castle
    public static final int BARRIER = 4; //+6 Wall
    public static final int WALL = 5; //+12 Wall
    public static final int GREATWALL = 6; //+20 Wall
    public static final int ARCHITECT = 7; //+1 Builder
    //endregion

    //region Attack Cards
    public static final int RECRUITER = 100; //+1 Soldier
    public static final int SPEARMAN = 101; //+2 Attack
    public static final int RAM = 102; //+6 Attack
    public static final int CATAPULT = 103; //+12 Attack
    public static final int TREBUCHET = 104; //+20 Attack
    public static final int ASSASSIN = 105; //Kill 1 Random Person
    //endregion

    //region Magic Cards
    public static final int CREATE_STONES = 200; //+16 Stones
    public static final int CREATE_WEAPONS = 201; //+16 Weapons
    public static final int CREATE_GEMS = 202; //+16 Gems
    public static final int DESTROY_STONES = 203; //-16 Stones
    public static final int DESTROY_WEAPONS = 204; //-16 Weapons
    public static final int DESTROY_GEMS = 205; //-16 Gems
    public static final int MAGE = 206; //+1 Wizard
    //endregion

    //region Golden Cards
    public static final int JERICHO = 300; //Destroy Enemy Wall
    public static final int TROJAN_HORSE = 301; //Destroy 20 Castle (Ignores Wall)
    public static final int BLACK_PLAGUE = 302; //-1 of All People
    public static final int MERLIN = 303; //+32 Attack
    //endRegion


    public CardActions(GameInterface gi){
        this.gi = gi;
    }


    public void doCardAction(int cardID){
        //BUILD CARDS
        if(cardID == REINFORCE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(6);
                gi.getResources().setPlayerStones(-10); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(6);
                gi.getResources().setComputerStones(-10); //Cost
            }
        } else if(cardID == FORTIFY){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(12);
                gi.getResources().setPlayerStones(-22); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(12);
                gi.getResources().setComputerStones(-22); //Cost
            }
        } else if(cardID == CASTLE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(20);
                gi.getResources().setPlayerStones(-36); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(20);
                gi.getResources().setComputerStones(-36); //Cost
            }
        } else if(cardID == BARRIER){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(6);
                gi.getResources().setPlayerStones(-8); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(6);
                gi.getResources().setComputerStones(-8); //Cost
            }
        } else if(cardID == WALL){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(12);
                gi.getResources().setPlayerStones(-20); //Cost
            } else {
                gi.getPlayer1().getCastle().getWall().repair(12);
                gi.getResources().setComputerStones(-20); //Cost
            }
        } else if(cardID == GREATWALL){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(20);
                gi.getResources().setPlayerStones(-30); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(20);
                gi.getResources().setComputerStones(-30); //Cost
            }
        } else if(cardID == ARCHITECT){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerBuilders(1);
                gi.getResources().setPlayerStones(-16); //Cost
            } else {
                gi.getResources().setComputerBuilders(1);
                gi.getResources().setComputerStones(-16); //Cost
            }
        }
        //ATTACK CARDS
        else if(cardID == RECRUITER){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerSoldiers(1);
                gi.getResources().setPlayerWeapons(-16); //Cost
            } else {
                gi.getResources().setComputerSoldiers(1);
                gi.getResources().setComputerWeapons(-16); //Cost
            }
        } else if(cardID == SPEARMAN){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(2);
                gi.getResources().setPlayerWeapons(-4); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(2);
                gi.getResources().setComputerWeapons(-4); //Cost
            }
        } else if(cardID == RAM){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(6);
                gi.getResources().setPlayerWeapons(-12); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(6);
                gi.getResources().setComputerWeapons(-12); //Cost
            }
        } else if(cardID == CATAPULT){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(12);
                gi.getResources().setPlayerWeapons(-24); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(12);
                gi.getResources().setComputerWeapons(-24); //Cost
            }
        } else if(cardID == TREBUCHET){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(20);
                gi.getResources().setPlayerWeapons(-40); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(20);
                gi.getResources().setComputerWeapons(-40); //Cost
            }
        } else if(cardID == ASSASSIN){
            int randomInt = (int)(Math.random() * 2);
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerWeapons(-20); //Cost
                if(randomInt == 0){
                    gi.getResources().setComputerBuilders(-1);
                } else if (randomInt == 1){
                    gi.getResources().setComputerSoldiers(-1);
                } else {
                    gi.getResources().setComputerWizards(-1);
                }
            } else {
                gi.getResources().setComputerWeapons(-20); //Cost
                if(randomInt == 0){
                    gi.getResources().setPlayerBuilders(-1);
                } else if (randomInt == 1){
                    gi.getResources().setPlayerSoldiers(-1);
                } else {
                    gi.getResources().setPlayerWizards(-1);
                }
            }
        }
        //MAGIC CARDS
        else if(cardID == CREATE_STONES){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerStones(16);
                gi.getResources().setPlayerGems(-10); //Cost
            } else {
                gi.getResources().setComputerStones(16);
                gi.getResources().setComputerGems(-10); //Cost
            }
        } else if(cardID == CREATE_WEAPONS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerWeapons(16);
                gi.getResources().setPlayerGems(-10); //Cost
            } else {
                gi.getResources().setComputerWeapons(16);
                gi.getResources().setComputerGems(-10); //Cost
            }
        } else if(cardID == CREATE_GEMS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerGems(16);
                gi.getResources().setPlayerGems(-10); //Cost
            } else {
                gi.getResources().setComputerGems(16);
                gi.getResources().setComputerGems(-10); //Cost
            }
        } else if(cardID == DESTROY_STONES){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setComputerStones(-16);
                gi.getResources().setPlayerGems(-10); //Cost
            } else {
                gi.getResources().setPlayerStones(-16);
                gi.getResources().setComputerGems(-10); //Cost
            }
        } else if(cardID == DESTROY_WEAPONS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setComputerWeapons(-16);
                gi.getResources().setPlayerGems(-10); //Cost
            } else {
                gi.getResources().setPlayerWeapons(-16);
                gi.getResources().setComputerGems(-10); //Cost
            }
        } else if(cardID == DESTROY_GEMS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setComputerGems(-16);
                gi.getResources().setPlayerGems(-10); //Cost
            } else {
                gi.getResources().setPlayerGems(-16);
                gi.getResources().setComputerGems(-10); //Cost
            }
        } else if(cardID == MAGE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerWizards(1);
                gi.getResources().setPlayerGems(-16); //Cost
            } else {
                gi.getResources().setComputerWizards(1);
                gi.getResources().setComputerGems(-16); //Cost
            }
        }

    }

}
