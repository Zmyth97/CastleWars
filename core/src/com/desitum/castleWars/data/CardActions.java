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

    //region Build Costs
    public static final int REINFORCE_COST = 10;
    public static final int FORTIFY_COST = 22;
    public static final int CASTLE_COST = 36;
    public static final int BARRIER_COST = 8;
    public static final int WALL_COST = 20;
    public static final int GREATWALL_COST = 30;
    public static final int ARCHITECT_COST = 16;
    //endregion

    //region Attack Cards
    public static final int RECRUITER = 100; //+1 Soldier
    public static final int SPEARMAN = 101; //+2 Attack
    public static final int RAM = 102; //+6 Attack
    public static final int CATAPULT = 103; //+12 Attack
    public static final int TREBUCHET = 104; //+20 Attack
    public static final int ASSASSIN = 105; //Kill 1 Random Person
    //endregion

    //region Attack Costs
    public static final int RECRUITER_COST = 16;
    public static final int SPEARMAN_COST = 4;
    public static final int RAM_COST = 12;
    public static final int CATAPULT_COST = 24;
    public static final int TREBUCHET_COST = 40;
    public static final int ASSASSIN_COST = 20;
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

    //region Magic Costs
    public static final int CREATE_STONES_COST = 10;
    public static final int CREATE_WEAPONS_COST = 10;
    public static final int CREATE_GEMS_COST = 10;
    public static final int DESTROY_STONES_COST = 10;
    public static final int DESTROY_WEAPONS_COST = 10;
    public static final int DESTROY_GEMS_COST = 10;
    public static final int MAGE_COST = 16;
    //endregion

    //region Golden Cards
    public static final int JERICHO = 300; //Destroy Enemy Wall
    public static final int TROJAN_HORSE = 301; //Destroy 20 Castle (Ignores Wall)
    public static final int BLACK_PLAGUE = 302; //-1 of All People
    public static final int MERLIN = 303; //+32 Attack
    //endRegion

    //region Golden Costs
    public static final int JERICHO_COST = 64;
    public static final int TROJAN_HORSE_COST = 56;
    public static final int BLACK_PLAGUE_COST = 72;
    public static final int MERLIN_COST = 64;
    //endregion

    public CardActions(GameInterface gi){
        this.gi = gi;
    }


    public void doCardAction(int cardID){
        //BUILD CARDS
        if(cardID == REINFORCE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(6);
                gi.getResources().setPlayerStones(-REINFORCE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(6);
                gi.getResources().setComputerStones(-REINFORCE_COST); //Cost
            }
        } else if(cardID == FORTIFY){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(12);
                gi.getResources().setPlayerStones(-FORTIFY_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(12);
                gi.getResources().setComputerStones(-FORTIFY_COST); //Cost
            }
        } else if(cardID == CASTLE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(20);
                gi.getResources().setPlayerStones(-CASTLE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(20);
                gi.getResources().setComputerStones(-CASTLE_COST); //Cost
            }
        } else if(cardID == BARRIER){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(6);
                gi.getResources().setPlayerStones(-BARRIER_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(6);
                gi.getResources().setComputerStones(-BARRIER_COST); //Cost
            }
        } else if(cardID == WALL){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(12);
                gi.getResources().setPlayerStones(-WALL_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().getWall().repair(12);
                gi.getResources().setComputerStones(-WALL_COST); //Cost
            }
        } else if(cardID == GREATWALL){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(20);
                gi.getResources().setPlayerStones(-GREATWALL_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(20);
                gi.getResources().setComputerStones(-GREATWALL_COST); //Cost
            }
        } else if(cardID == ARCHITECT){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerBuilders(1);
                gi.getResources().setPlayerStones(-ARCHITECT_COST); //Cost
            } else {
                gi.getResources().setComputerBuilders(1);
                gi.getResources().setComputerStones(-ARCHITECT_COST); //Cost
            }
        }
        //ATTACK CARDS
        else if(cardID == RECRUITER){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerSoldiers(1);
                gi.getResources().setPlayerWeapons(-RECRUITER_COST); //Cost
            } else {
                gi.getResources().setComputerSoldiers(1);
                gi.getResources().setComputerWeapons(-RECRUITER_COST); //Cost
            }
        } else if(cardID == SPEARMAN){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(2);
                gi.getResources().setPlayerWeapons(-SPEARMAN_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(2);
                gi.getResources().setComputerWeapons(-SPEARMAN_COST); //Cost
            }
        } else if(cardID == RAM){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(6);
                gi.getResources().setPlayerWeapons(-RAM_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(6);
                gi.getResources().setComputerWeapons(-RAM_COST); //Cost
            }
        } else if(cardID == CATAPULT){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(12);
                gi.getResources().setPlayerWeapons(-CATAPULT_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(12);
                gi.getResources().setComputerWeapons(-CATAPULT_COST); //Cost
            }
        } else if(cardID == TREBUCHET){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(20);
                gi.getResources().setPlayerWeapons(-TREBUCHET_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(20);
                gi.getResources().setComputerWeapons(-TREBUCHET_COST); //Cost
            }
        } else if(cardID == ASSASSIN){
            int randomInt = (int)(Math.random() * 2);
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerWeapons(-ASSASSIN_COST); //Cost
                if(randomInt == 0){
                    gi.getResources().setComputerBuilders(-1);
                } else if (randomInt == 1){
                    gi.getResources().setComputerSoldiers(-1);
                } else {
                    gi.getResources().setComputerWizards(-1);
                }
            } else {
                gi.getResources().setComputerWeapons(-ASSASSIN_COST); //Cost
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
                gi.getResources().setPlayerGems(-CREATE_STONES_COST); //Cost
            } else {
                gi.getResources().setComputerStones(16);
                gi.getResources().setComputerGems(-CREATE_STONES_COST); //Cost
            }
        } else if(cardID == CREATE_WEAPONS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerWeapons(16);
                gi.getResources().setPlayerGems(-CREATE_WEAPONS_COST); //Cost
            } else {
                gi.getResources().setComputerWeapons(16);
                gi.getResources().setComputerGems(-CREATE_WEAPONS_COST); //Cost
            }
        } else if(cardID == CREATE_GEMS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerGems(16);
                gi.getResources().setPlayerGems(-CREATE_GEMS_COST); //Cost
            } else {
                gi.getResources().setComputerGems(16);
                gi.getResources().setComputerGems(-CREATE_GEMS_COST); //Cost
            }
        } else if(cardID == DESTROY_STONES){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setComputerStones(-16);
                gi.getResources().setPlayerGems(-DESTROY_STONES_COST); //Cost
            } else {
                gi.getResources().setPlayerStones(-16);
                gi.getResources().setComputerGems(-DESTROY_STONES_COST); //Cost
            }
        } else if(cardID == DESTROY_WEAPONS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setComputerWeapons(-16);
                gi.getResources().setPlayerGems(-DESTROY_WEAPONS_COST); //Cost
            } else {
                gi.getResources().setPlayerWeapons(-16);
                gi.getResources().setComputerGems(-DESTROY_WEAPONS_COST); //Cost
            }
        } else if(cardID == DESTROY_GEMS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setComputerGems(-16);
                gi.getResources().setPlayerGems(-DESTROY_GEMS_COST); //Cost
            } else {
                gi.getResources().setPlayerGems(-16);
                gi.getResources().setComputerGems(-DESTROY_GEMS_COST); //Cost
            }
        } else if(cardID == MAGE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setPlayerWizards(1);
                gi.getResources().setPlayerGems(-MAGE_COST); //Cost
            } else {
                gi.getResources().setComputerWizards(1);
                gi.getResources().setComputerGems(-MAGE_COST); //Cost
            }
        }

        //GOLDEN CARDS
        else if(cardID == JERICHO){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().getWall().doDamage(gi.getPlayer2().getCastle().getWall().getHealth());
                gi.getResources().setPlayerWeapons(-JERICHO_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().getWall().doDamage(gi.getPlayer1().getCastle().getWall().getHealth());
                gi.getResources().setComputerWeapons(-JERICHO_COST); //Cost
            }
        }else if(cardID == TROJAN_HORSE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                float temp = gi.getPlayer2().getCastle().getWall().getHealth();
                gi.getPlayer2().getCastle().doDamage(20 + temp);
                gi.getPlayer2().getCastle().getWall().repair(temp);
                //NOTE FOR KODY: Probably change the three lines above to just 1 and add a Castle.setHealth() along with doDamage to avoid make it more simple
                gi.getResources().setPlayerWeapons(-TROJAN_HORSE_COST); //Cost
            } else {
                float temp = gi.getPlayer1().getCastle().getWall().getHealth();
                gi.getPlayer1().getCastle().doDamage(20 + temp);
                gi.getPlayer1().getCastle().getWall().repair(temp);
                gi.getResources().setComputerWeapons(-TROJAN_HORSE_COST); //Cost
            }
        }else if(cardID == BLACK_PLAGUE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().setComputerBuilders(-1);
                gi.getResources().setComputerSoldiers(-1);
                gi.getResources().setComputerWizards(-1);
                gi.getResources().setPlayerGems(-BLACK_PLAGUE_COST); //Cost
            } else {
                gi.getResources().setPlayerBuilders(-1);
                gi.getResources().setPlayerSoldiers(-1);
                gi.getResources().setPlayerWizards(-1);
                gi.getResources().setComputerGems(-BLACK_PLAGUE_COST); //Cost
            }
        }else if(cardID == MERLIN){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(32);
                gi.getResources().setPlayerGems(-MERLIN_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(32);
                gi.getResources().setComputerGems(-MERLIN_COST); //Cost
            }
        }

    }

}

