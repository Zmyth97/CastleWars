package com.desitum.castleWars.packs;

import com.desitum.castleWars.world.GameInterface;
import com.desitum.castleWars.world.GameWorld;

/**
 * Created by Zmyth97 on 5/26/2015.
 */
public class JapanesePack {
    private GameInterface gi;

    //region Build Cards
    public static final int STOCKADE = 501; //+8 Wall
    public static final int FORTRESS = 502; //+8 Castle
    public static final int MONASTERY = 503; //+2 Builders
    public static final int RAMPART = 504; //+15 Wall
    public static final int CITADEL = 505; //+15 Castle
    //endregion

    //region Build Costs
    public static final int STOCKADE_COST = 14;
    public static final int FORTRESS_COST = 14;
    public static final int MONASTERY_COST = 32;
    public static final int RAMPART_COST = 26;
    public static final int CITADEL_COST = 26;
    //endregion

    //region Attack Cards
    public static final int ASHIGARU = 506; //+6 Attack
    public static final int SHURIKEN = 507; //+10 Attack
    public static final int KATANA = 508; //+12 Attack
    public static final int SAMURAI = 509; //+18 Attack
    public static final int DOJO = 510; //+2 Soldier
    //endregion

    //region Attack Costs
    public static final int ASHIGARU_COST = 12;
    public static final int SHURIKEN_COST = 18;
    public static final int KATANA_COST = 22;
    public static final int SAMURAI_COST = 34;
    public static final int DOJO_COST = 32;
    //endregion

    //region Magic Cards
    public static final int SHRINE = 511; //+2 Wizards
    public static final int QUARRY = 512; //+24 Stone
    public static final int TEMPLE = 513; //+24 Gems
    public static final int RICE_PADDY = 514; //+24 weapons (Supplies)
    public static final int SEPPUKU = 515; //-1 Soldier, + 2 Wizard? (Maybe + 50 or so Supplies?)
    //endregion

    //region Magic Costs
    public static final int SHRINE_COST = 32;
    public static final int QUARRY_COST = 14;
    public static final int TEMPLE_COST = 14;
    public static final int RICE_PADDY_COST = 14;
    public static final int SEPPUKU_COST = 30;
    //endregion

    //region GoldenCards
    public static final int NINJA = 516; //Kill 3 Enemy Wizards
    public static final int DRAGON = 517; //+40 Attack
    public static final int SHOGUN = 518; //Add 24 of Each Resource
    //endRegion

    //region Golden Costs
    public static final int NINJA_COST = 64;
    public static final int DRAGON_COST = 74;
    public static final int SHOGUN_COST = 48;
    //endregion

    //region Japanese CardAmounts
    public static int STOCKADE_AMOUNT = 2;
    public static int FORTRESS_AMOUNT = 2;
    public static int MONASTERY_AMOUNT = 2;
    public static int RAMPART_AMOUNT = 2;
    public static int CITADEL_AMOUNT = 2;

    public static int ASHIGARU_AMOUNT = 2;
    public static int SHURIKEN_AMOUNT = 2;
    public static int KATANA_AMOUNT = 2;
    public static int SAMURAI_AMOUNT = 2;
    public static int DOJO_AMOUNT = 2;

    public static int SHRINE_AMOUNT = 2;
    public static int QUARRY_AMOUNT = 2;
    public static int TEMPLE_AMOUNT = 2;
    public static int RICE_PADDY_AMOUNT = 2;
    public static int SEPPUKU_AMOUNT = 2;

    public static int NINJA_AMOUNT = 2;
    public static int DRAGON_AMOUNT = 2;
    public static int SHOGUN_AMOUNT = 2;
    //endregion

    public JapanesePack(GameInterface gi){
        this.gi = gi;
    }


    public void doCardAction(int cardID) {
        //region BUILD CARDS
        if (cardID == STOCKADE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(8);
                gi.getResources().adjustPlayerStones(-STOCKADE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(8);
                gi.getResources().adjustComputerStones(-STOCKADE_COST); //Cost
            }
        } else if (cardID == FORTRESS) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(8);
                gi.getResources().adjustPlayerStones(-FORTRESS_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(8);
                gi.getResources().adjustComputerStones(-FORTRESS_COST); //Cost
            }
        } else if (cardID == MONASTERY) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerBuilders(2);
                gi.getResources().adjustPlayerStones(-MONASTERY_COST); //Cost
            } else {
                gi.getResources().adjustComputerBuilders(2);
                gi.getResources().adjustComputerStones(-MONASTERY_COST); //Cost
            }
        } else if (cardID == RAMPART) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(15);
                gi.getResources().adjustPlayerStones(-RAMPART_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(15);
                gi.getResources().adjustComputerStones(-RAMPART_COST); //Cost
            }
        } else if (cardID == CITADEL) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(15);
                gi.getResources().adjustPlayerStones(-CITADEL_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(15);
                gi.getResources().adjustComputerStones(-CITADEL_COST); //Cost
            }
        }

        //ATTACK CARDS
        else if (cardID == ASHIGARU) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(6);
                gi.getResources().adjustPlayerWeapons(-ASHIGARU_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(6);
                gi.getResources().adjustComputerWeapons(-ASHIGARU_COST); //Cost
            }
        } else if (cardID == SHURIKEN) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(10);
                gi.getResources().adjustPlayerWeapons(-SHURIKEN_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(10);
                gi.getResources().adjustComputerWeapons(-SHURIKEN_COST); //Cost
            }
        } else if (cardID == KATANA) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(12);
                gi.getResources().adjustPlayerWeapons(-KATANA_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(12);
                gi.getResources().adjustComputerWeapons(-KATANA_COST); //Cost
            }
        } else if (cardID == SAMURAI) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(18);
                gi.getResources().adjustPlayerWeapons(-SAMURAI_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(18);
                gi.getResources().adjustComputerWeapons(-SAMURAI_COST); //Cost
            }
        } else if (cardID == DOJO) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerSoldiers(2);
                gi.getResources().adjustPlayerWeapons(-DOJO_COST); //Cost
            } else {
                gi.getResources().adjustComputerSoldiers(2);
                gi.getResources().adjustComputerWeapons(-DOJO_COST); //Cost
            }
        }

        //MAGIC CARDS
        else if(cardID == SHRINE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWizards(2);
                gi.getResources().adjustPlayerGems(-SHRINE_COST); //Cost
            } else {
                gi.getResources().adjustComputerWizards(2);
                gi.getResources().adjustComputerGems(-SHRINE_COST); //Cost
            }
        } else if(cardID == QUARRY){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerStones(24);
                gi.getResources().adjustPlayerGems(-QUARRY_COST); //Cost
            } else {
                gi.getResources().adjustComputerStones(24);
                gi.getResources().adjustComputerGems(-QUARRY_COST); //Cost
            }
        } else if(cardID == TEMPLE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerGems(24);
                gi.getResources().adjustPlayerGems(-TEMPLE_COST); //Cost
            } else {
                gi.getResources().adjustComputerGems(24);
                gi.getResources().adjustComputerGems(-TEMPLE_COST); //Cost
            }
        } else if(cardID == RICE_PADDY){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(24);
                gi.getResources().adjustPlayerGems(-RICE_PADDY_COST); //Cost
            } else {
                gi.getResources().adjustComputerWeapons(24);
                gi.getResources().adjustComputerGems(-RICE_PADDY_COST); //Cost
            }
        } else if(cardID == SEPPUKU){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerSoldiers(-1);
                gi.getResources().adjustPlayerWizards(2);
                gi.getResources().adjustPlayerGems(-SEPPUKU_COST); //Cost
            } else {
                gi.getResources().adjustComputerSoldiers(-1);
                gi.getResources().adjustComputerWizards(2);
                gi.getResources().adjustComputerGems(-SEPPUKU_COST); //Cost
            }
        }

        //GOLDEN CARDS
        else if(cardID == NINJA){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerWizards(-3);
                gi.getResources().adjustPlayerWeapons(-NINJA_COST); //Cost
            } else {
                gi.getResources().adjustPlayerWizards(-3);
                gi.getResources().adjustComputerWeapons(-NINJA_COST); //Cost
            }
        }else if(cardID == DRAGON){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(40);
                gi.getResources().adjustPlayerGems(-DRAGON_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(40);
                gi.getResources().adjustComputerGems(-DRAGON_COST); //Cost
            }
        }else if(cardID == SHOGUN){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(24);
                gi.getResources().adjustPlayerStones(24);
                gi.getResources().adjustPlayerGems(24);
                gi.getResources().adjustPlayerGems(-SHOGUN_COST); //Cost
            } else {
                gi.getResources().adjustComputerWeapons(24);
                gi.getResources().adjustComputerStones(24);
                gi.getResources().adjustComputerGems(24);
                gi.getResources().adjustComputerGems(-SHOGUN_COST); //Cost
            }
        }

    }
}
