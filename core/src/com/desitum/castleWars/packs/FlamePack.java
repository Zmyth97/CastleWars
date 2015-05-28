package com.desitum.castleWars.packs;

import com.desitum.castleWars.world.GameInterface;
import com.desitum.castleWars.world.GameWorld;

/**
 * Created by Zmyth97 on 5/25/2015.
 */
public class FlamePack {
    private GameInterface gi;

    //region Build Cards
    public static final int WALL_OF_FIRE = 401; //+8 Wall
    public static final int CAMPFIRE = 402; //+8 Castle
    public static final int FORGE = 403; //+2 Builders
    public static final int BOILING_OIL = 404; //+15 Wall
    public static final int BONFIRE = 405; //+15 Castle
    //endregion

    //region Build Costs
    public static final int WALL_OF_FIRE_COST = 14;
    public static final int CAMPFIRE_COST = 14;
    public static final int FORGE_COST = 32;
    public static final int BOILING_OIL_COST = 26;
    public static final int BONFIRE_COST = 26;
    //endregion

    //region Attack Cards
    public static final int FIRE = 406; //+6 Attack
    public static final int FIRE_ARROWS = 407; //+10 Attack
    public static final int FLAMING_AXE = 408; //+12 Attack
    public static final int FLAMING_SHOT = 409; //+18 Attack
    public static final int FLAME_LEGION = 410; //+2 Soldier
    //endregion

    //region Attack Costs
    public static final int FIRE_COST = 12;
    public static final int FIRE_ARROWS_COST = 18;
    public static final int FLAMING_AXE_COST = 22;
    public static final int FLAMING_SHOT_COST = 34;
    public static final int FLAME_LEGION_COST = 32;
    //endregion

    //region Magic Cards
    public static final int FIRE_SHAMAN = 411; //+2 Wizards
    public static final int LAVA_FLOW = 412; //+24 Stone
    public static final int COAL = 413; //+24 Gems
    public static final int BLACKSMITH = 414; //+24 weapons
    public static final int FIREBALL = 415; //+16 Attack
    //endregion

    //region Magic Costsfire
    public static final int FIRE_SHAMAN_COST = 32;
    public static final int LAVA_FLOW_COST = 14;
    public static final int COAL_COST = 14;
    public static final int BLACKSMITH_COST = 14;
    public static final int FIREBALL_COST = 30;
    //endregion

    //region GoldenCards
    public static final int PHEONIX = 416; //Kill 1 Enemy Soldier, get 2 Soldier (Rise from dead? :D)
    public static final int METEORS = 417; //+40 Attack
    public static final int INFERNO = 418; //Burn 24 of Each Enemy Resource
    //endRegion

    //region Golden Costs
    public static final int PHEONIX_COST = 64;
    public static final int METEORS_COST = 74;
    public static final int INFERNO_COST = 56;
    //endregion

    public FlamePack(GameInterface gi){
        this.gi = gi;
    }


    public void doCardAction(int cardID) {
        //region BUILD CARDS
        if (cardID == WALL_OF_FIRE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(8);
                gi.getResources().adjustPlayerStones(-WALL_OF_FIRE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(8);
                gi.getResources().adjustComputerStones(-WALL_OF_FIRE_COST); //Cost
            }
        } else if (cardID == CAMPFIRE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(8);
                gi.getResources().adjustPlayerStones(-CAMPFIRE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(8);
                gi.getResources().adjustComputerStones(-CAMPFIRE_COST); //Cost
            }
        } else if (cardID == FORGE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerBuilders(2);
                gi.getResources().adjustPlayerStones(-FORGE_COST); //Cost
            } else {
                gi.getResources().adjustComputerBuilders(2);
                gi.getResources().adjustComputerStones(-FORGE_COST); //Cost
            }
        } else if (cardID == BOILING_OIL) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(15);
                gi.getResources().adjustPlayerStones(-BOILING_OIL_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(15);
                gi.getResources().adjustComputerStones(-BOILING_OIL_COST); //Cost
            }
        } else if (cardID == BONFIRE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(15);
                gi.getResources().adjustPlayerStones(-BONFIRE_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().repair(15);
                gi.getResources().adjustComputerStones(-BONFIRE_COST); //Cost
            }
        }

        //ATTACK CARDS
        else if (cardID == FIRE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(6);
                gi.getResources().adjustPlayerWeapons(-FIRE_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(6);
                gi.getResources().adjustComputerWeapons(-FIRE_COST); //Cost
            }
        } else if (cardID == FIRE_ARROWS) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(10);
                gi.getResources().adjustPlayerWeapons(-FIRE_ARROWS_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(10);
                gi.getResources().adjustComputerWeapons(-FIRE_ARROWS_COST); //Cost
            }
        } else if (cardID == FLAMING_AXE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(12);
                gi.getResources().adjustPlayerWeapons(-FLAMING_AXE_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(12);
                gi.getResources().adjustComputerWeapons(-FLAMING_AXE_COST); //Cost
            }
        } else if (cardID == FLAMING_SHOT) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(18);
                gi.getResources().adjustPlayerWeapons(-FLAMING_SHOT_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(18);
                gi.getResources().adjustComputerWeapons(-FLAMING_SHOT_COST); //Cost
            }
        } else if (cardID == FLAME_LEGION) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerSoldiers(2);
                gi.getResources().adjustPlayerWeapons(-FLAME_LEGION_COST); //Cost
            } else {
                gi.getResources().adjustComputerSoldiers(2);
                gi.getResources().adjustComputerWeapons(-FLAME_LEGION_COST); //Cost
            }
        }

        //MAGIC CARDS
        else if(cardID == FIRE_SHAMAN){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWizards(2);
                gi.getResources().adjustPlayerGems(-FIRE_SHAMAN_COST); //Cost
            } else {
                gi.getResources().adjustComputerWizards(2);
                gi.getResources().adjustComputerGems(-FIRE_SHAMAN_COST); //Cost
            }
        } else if(cardID == LAVA_FLOW){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerStones(24);
                gi.getResources().adjustPlayerGems(-LAVA_FLOW_COST); //Cost
            } else {
                gi.getResources().adjustComputerStones(24);
                gi.getResources().adjustComputerGems(-LAVA_FLOW_COST); //Cost
            }
        } else if(cardID == COAL){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerGems(24);
                gi.getResources().adjustPlayerGems(-COAL_COST); //Cost
            } else {
                gi.getResources().adjustComputerGems(24);
                gi.getResources().adjustComputerGems(-COAL_COST); //Cost
            }
        } else if(cardID == BLACKSMITH){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(24);
                gi.getResources().adjustPlayerGems(-BLACKSMITH_COST); //Cost
            } else {
                gi.getResources().adjustComputerWeapons(24);
                gi.getResources().adjustComputerGems(-BLACKSMITH_COST); //Cost
            }
        } else if(cardID == FIREBALL){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(16);
                gi.getResources().adjustPlayerGems(-FIREBALL_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(16);
                gi.getResources().adjustComputerGems(-FIREBALL_COST); //Cost
            }
        }

        //GOLDEN CARDS
        else if(cardID == PHEONIX){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerSoldiers(-1);
                gi.getResources().adjustPlayerSoldiers(2);
                gi.getResources().adjustPlayerWeapons(-PHEONIX_COST); //Cost
            } else {
                gi.getResources().adjustPlayerSoldiers(-1);
                gi.getResources().adjustComputerSoldiers(2);
                gi.getResources().adjustComputerWeapons(-PHEONIX_COST); //Cost
            }
        }else if(cardID == METEORS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(40);
                gi.getResources().adjustPlayerGems(-METEORS_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(40);
                gi.getResources().adjustComputerGems(-METEORS_COST); //Cost
            }
        }else if(cardID == INFERNO){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerWeapons(-24);
                gi.getResources().adjustComputerStones(-24);
                gi.getResources().adjustComputerGems(-24);
                gi.getResources().adjustPlayerGems(-INFERNO_COST); //Cost
            } else {
                gi.getResources().adjustPlayerWeapons(-24);
                gi.getResources().adjustPlayerStones(-24);
                gi.getResources().adjustPlayerGems(-24);
                gi.getResources().adjustComputerGems(-INFERNO_COST); //Cost
            }
        }

    }
}
