package com.desitum.castleWars.data;

import com.desitum.castleWars.world.GameInterface;
import com.desitum.castleWars.world.GameWorld;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class CardActions {
    private GameInterface gi;


    //region Build Cards
    public static final int REINFORCE = 1; //+6 Castle
    public static final int FORTIFY = 2; //+12 Castle
    public static final int CASTLE = 3; //+20 Castle
    public static final int BARRIER = 4; //+4 Wall
    public static final int WALL = 5; //+10 Wall
    public static final int GREATWALL = 6; //+18 Wall
    public static final int ARCHITECT = 7; //+1 Builder
    public static final int RESERVE = 8; //Castle +8, Wall -4
    public static final int SABOTAGE = 9; //Enemy Castle -4, Castle +8
    public static final int STRONGHOLD = 10; //Castle + 10, Wall + 10
    //endregion

    //region Build Costs
    public static final int REINFORCE_COST = 10;
    public static final int FORTIFY_COST = 22;
    public static final int CASTLE_COST = 36;
    public static final int BARRIER_COST = 8;
    public static final int WALL_COST = 20;
    public static final int GREATWALL_COST = 30;
    public static final int ARCHITECT_COST = 16;
    public static final int RESERVE_COST = 8;
    public static final int SABOTAGE_COST = 20;
    public static final int STRONGHOLD_COST = 36;
    //endregion

    //region Attack Cards
    public static final int RECRUITER = 100; //+1 Soldier
    public static final int SPEARMAN = 101; //+2 Attack
    public static final int RAM = 102; //+6 Attack
    public static final int CATAPULT = 103; //+12 Attack
    public static final int LEGION = 109; //+16 Attack
    public static final int TREBUCHET = 104; //+20 Attack
    public static final int ASSASSIN = 105; //Kill 1 Random Person
    public static final int BURGLAR = 106; //Steal 8 of each resource
    public static final int THIEF = 107; //Steal 24 of each resource
    public static final int RAID = 108; //Destroy 24 of a resource
    //endregion

    //region Attack Costs
    public static final int RECRUITER_COST = 16;
    public static final int SPEARMAN_COST = 4;
    public static final int RAM_COST = 12;
    public static final int CATAPULT_COST = 24;
    public static final int LEGION_COST = 30;
    public static final int TREBUCHET_COST = 40;
    public static final int ASSASSIN_COST = 20;
    public static final int BURGLAR_COST = 18;
    public static final int THIEF_COST = 64;
    public static final int RAID_COST = 14;
    //endregion

    //region Magic Cards
    public static final int CREATE_STONES = 200; //+16 Stones
    public static final int CREATE_WEAPONS = 201; //+16 Weapons
    public static final int CREATE_GEMS = 202; //+16 Gems
    public static final int DESTROY_STONES = 203; //-16 Stones
    public static final int DESTROY_WEAPONS = 204; //-16 Weapons
    public static final int DESTROY_GEMS = 205; //-16 Gems
    public static final int MAGE = 206; //+1 Wizard
    public static final int HAT_TRICK = 207; //+4 all res
    public static final int LIGHTNING_STRIKE = 208; //+16 Attack
    public static final int BLAST = 209; //+8 Attack
    //endregion

    //region Magic Costs
    public static final int CREATE_STONES_COST = 10;
    public static final int CREATE_WEAPONS_COST = 10;
    public static final int CREATE_GEMS_COST = 10;
    public static final int DESTROY_STONES_COST = 10;
    public static final int DESTROY_WEAPONS_COST = 10;
    public static final int DESTROY_GEMS_COST = 10;
    public static final int MAGE_COST = 16;
    public static final int HAT_TRICK_COST = 4;
    public static final int LIGHTNING_STRICK_COST = 32;
    public static final int BLAST_COST = 16;
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
                gi.getResources().adjustPlayerStones(-REINFORCE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(6);
                gi.getResources().adjustComputerStones(-REINFORCE_COST); //Cost
            }
        } else if(cardID == FORTIFY){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(12);
                gi.getResources().adjustPlayerStones(-FORTIFY_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(12);
                gi.getResources().adjustComputerStones(-FORTIFY_COST); //Cost
            }
        } else if(cardID == CASTLE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(20);
                gi.getResources().adjustPlayerStones(-CASTLE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(20);
                gi.getResources().adjustComputerStones(-CASTLE_COST); //Cost
            }
        } else if(cardID == BARRIER){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(4);
                gi.getResources().adjustPlayerStones(-BARRIER_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(4);
                gi.getResources().adjustComputerStones(-BARRIER_COST); //Cost
            }
        } else if(cardID == WALL){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(10);
                gi.getResources().adjustPlayerStones(-WALL_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().getWall().repair(10);
                gi.getResources().adjustComputerStones(-WALL_COST); //Cost
            }
        } else if(cardID == GREATWALL){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(18);
                gi.getResources().adjustPlayerStones(-GREATWALL_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(18);
                gi.getResources().adjustComputerStones(-GREATWALL_COST); //Cost
            }
        } else if(cardID == ARCHITECT){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerBuilders(1);
                gi.getResources().adjustPlayerStones(-ARCHITECT_COST); //Cost
            } else {
                gi.getResources().adjustComputerBuilders(1);
                gi.getResources().adjustComputerStones(-ARCHITECT_COST); //Cost
            }
        }else if(cardID == RESERVE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(8);
                gi.getPlayer1().getCastle().getWall().doDamage(4);
                gi.getResources().adjustPlayerStones(-RESERVE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(8);
                gi.getPlayer2().getCastle().getWall().doDamage(4);
                gi.getResources().adjustComputerStones(-RESERVE_COST); //Cost
            }
        }else if(cardID == SABOTAGE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(8);
                gi.getPlayer2().getCastle().doDamage(4);
                gi.getResources().adjustPlayerStones(-SABOTAGE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(8);
                gi.getPlayer1().getCastle().doDamage(4);
                gi.getResources().adjustComputerStones(-SABOTAGE_COST); //Cost
            }
        }else if(cardID == STRONGHOLD){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(10);
                gi.getPlayer1().getCastle().getWall().repair(10);
                gi.getResources().adjustPlayerStones(-STRONGHOLD_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(10);
                gi.getPlayer2().getCastle().getWall().repair(10);
                gi.getResources().adjustComputerStones(-STRONGHOLD_COST); //Cost
            }
        }
        //ATTACK CARDS
        else if(cardID == RECRUITER){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerSoldiers(1);
                gi.getResources().adjustPlayerWeapons(-RECRUITER_COST); //Cost
            } else {
                gi.getResources().adjustComputerSoldiers(1);
                gi.getResources().adjustComputerWeapons(-RECRUITER_COST); //Cost
            }
        } else if(cardID == SPEARMAN){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(2);
                gi.getResources().adjustPlayerWeapons(-SPEARMAN_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(2);
                gi.getResources().adjustComputerWeapons(-SPEARMAN_COST); //Cost
            }
        } else if(cardID == RAM){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(6);
                gi.getResources().adjustPlayerWeapons(-RAM_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(6);
                gi.getResources().adjustComputerWeapons(-RAM_COST); //Cost
            }
        } else if(cardID == CATAPULT){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(12);
                gi.getResources().adjustPlayerWeapons(-CATAPULT_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(12);
                gi.getResources().adjustComputerWeapons(-CATAPULT_COST); //Cost
            }
        } else if(cardID == LEGION) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(16);
                gi.getResources().adjustPlayerWeapons(-LEGION_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(16);
                gi.getResources().adjustComputerWeapons(-LEGION_COST); //Cost
            }
        }else if(cardID == TREBUCHET){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(20);
                gi.getResources().adjustPlayerWeapons(-TREBUCHET_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(20);
                gi.getResources().adjustComputerWeapons(-TREBUCHET_COST); //Cost
            }
        } else if(cardID == ASSASSIN){
            int randomInt = (int)(Math.random() * 2);
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(-ASSASSIN_COST); //Cost
                if(randomInt == 0){
                    gi.getResources().adjustComputerBuilders(-1);
                } else if (randomInt == 1){
                    gi.getResources().adjustComputerSoldiers(-1);
                } else {
                    gi.getResources().adjustComputerWizards(-1);
                }
            } else {
                gi.getResources().adjustComputerWeapons(-ASSASSIN_COST); //Cost
                if(randomInt == 0){
                    gi.getResources().adjustPlayerBuilders(-1);
                } else if (randomInt == 1){
                    gi.getResources().adjustPlayerSoldiers(-1);
                } else {
                    gi.getResources().adjustPlayerWizards(-1);
                }
            }
        }else if(cardID == BURGLAR){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(-BURGLAR_COST); //Cost
                gi.getResources().adjustPlayerStones(gi.getResources().adjustComputerStones(-8));
                gi.getResources().adjustPlayerWeapons(gi.getResources().adjustComputerWeapons(-8));
                gi.getResources().adjustPlayerGems(gi.getResources().adjustComputerGems(-8));
            } else {
                gi.getResources().adjustComputerWeapons(-BURGLAR_COST); //Cost
                gi.getResources().adjustComputerStones(gi.getResources().adjustPlayerStones(-8));
                gi.getResources().adjustComputerWeapons(gi.getResources().adjustPlayerWeapons(-8));
                gi.getResources().adjustComputerGems(gi.getResources().adjustPlayerGems(-8));

            }
        }else if(cardID == THIEF){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(-THIEF_COST); //Cost
                gi.getResources().adjustPlayerStones(gi.getResources().adjustComputerStones(-24));
                gi.getResources().adjustPlayerWeapons(gi.getResources().adjustComputerWeapons(-24));
                gi.getResources().adjustPlayerGems(gi.getResources().adjustComputerGems(-24));
            } else {
                gi.getResources().adjustComputerWeapons(-THIEF_COST); //Cost
                gi.getResources().adjustComputerStones(gi.getResources().adjustPlayerStones(-24));
                gi.getResources().adjustComputerWeapons(gi.getResources().adjustPlayerWeapons(-24));
                gi.getResources().adjustComputerGems(gi.getResources().adjustPlayerGems(-24));
            }
        } else if(cardID == RAID){
            int randomInt = (int)(Math.random() * 2);
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(-RAID_COST); //Cost
                if(randomInt == 0){
                    gi.getResources().adjustComputerStones(-24);
                } else if (randomInt == 1){
                    gi.getResources().adjustComputerWeapons(-24);
                } else {
                    gi.getResources().adjustComputerGems(-24);
                }
            } else {
                gi.getResources().adjustComputerWeapons(-RAID_COST); //Cost
                if(randomInt == 0){
                    gi.getResources().adjustPlayerStones(-24);
                } else if (randomInt == 1){
                    gi.getResources().adjustPlayerWeapons(-24);
                } else {
                    gi.getResources().adjustPlayerGems(-24);
                }
            }
        }
        //MAGIC CARDS
        else if(cardID == CREATE_STONES){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerStones(16);
                gi.getResources().adjustPlayerGems(-CREATE_STONES_COST); //Cost
            } else {
                gi.getResources().adjustComputerStones(16);
                gi.getResources().adjustComputerGems(-CREATE_STONES_COST); //Cost
            }
        } else if(cardID == CREATE_WEAPONS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(16);
                gi.getResources().adjustPlayerGems(-CREATE_WEAPONS_COST); //Cost
            } else {
                gi.getResources().adjustComputerWeapons(16);
                gi.getResources().adjustComputerGems(-CREATE_WEAPONS_COST); //Cost
            }
        } else if(cardID == CREATE_GEMS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerGems(16);
                gi.getResources().adjustPlayerGems(-CREATE_GEMS_COST); //Cost
            } else {
                gi.getResources().adjustComputerGems(16);
                gi.getResources().adjustComputerGems(-CREATE_GEMS_COST); //Cost
            }
        } else if(cardID == DESTROY_STONES){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerStones(-16);
                gi.getResources().adjustPlayerGems(-DESTROY_STONES_COST); //Cost
            } else {
                gi.getResources().adjustPlayerStones(-16);
                gi.getResources().adjustComputerGems(-DESTROY_STONES_COST); //Cost
            }
        } else if(cardID == DESTROY_WEAPONS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerWeapons(-16);
                gi.getResources().adjustPlayerGems(-DESTROY_WEAPONS_COST); //Cost
            } else {
                gi.getResources().adjustPlayerWeapons(-16);
                gi.getResources().adjustComputerGems(-DESTROY_WEAPONS_COST); //Cost
            }
        } else if(cardID == DESTROY_GEMS){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerGems(-16);
                gi.getResources().adjustPlayerGems(-DESTROY_GEMS_COST); //Cost
            } else {
                gi.getResources().adjustPlayerGems(-16);
                gi.getResources().adjustComputerGems(-DESTROY_GEMS_COST); //Cost
            }
        } else if(cardID == MAGE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWizards(1);
                gi.getResources().adjustPlayerGems(-MAGE_COST); //Cost
            } else {
                gi.getResources().adjustComputerWizards(1);
                gi.getResources().adjustComputerGems(-MAGE_COST); //Cost
            }
        } else if(cardID == HAT_TRICK){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerStones(gi.getResources().adjustPlayerStones(4));
                gi.getResources().adjustComputerWeapons(gi.getResources().adjustPlayerWeapons(4));
                gi.getResources().adjustComputerGems(gi.getResources().adjustPlayerGems(4));
                gi.getResources().adjustPlayerGems(-HAT_TRICK_COST); //Cost
            } else {
                gi.getResources().adjustPlayerStones(gi.getResources().adjustComputerStones(4));
                gi.getResources().adjustPlayerWeapons(gi.getResources().adjustComputerWeapons(4));
                gi.getResources().adjustPlayerGems(gi.getResources().adjustComputerGems(4));
                gi.getResources().adjustComputerGems(-HAT_TRICK_COST); //Cost
            }
        } else if(cardID == LIGHTNING_STRIKE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(16);
                gi.getResources().adjustPlayerGems(-LIGHTNING_STRICK_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(16);
                gi.getResources().adjustComputerGems(-LIGHTNING_STRICK_COST); //Cost
            }
        } else if(cardID == BLAST){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(8);
                gi.getResources().adjustPlayerGems(-BLAST_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(8);
                gi.getResources().adjustComputerGems(-BLAST_COST); //Cost
            }
        }

        //GOLDEN CARDS
        else if(cardID == JERICHO){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().getWall().doDamage(gi.getPlayer2().getCastle().getWall().getHealth());
                gi.getResources().adjustPlayerWeapons(-JERICHO_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().getWall().doDamage(gi.getPlayer1().getCastle().getWall().getHealth());
                gi.getResources().adjustComputerWeapons(-JERICHO_COST); //Cost
            }
        }else if(cardID == TROJAN_HORSE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                float temp = gi.getPlayer2().getCastle().getWall().getHealth();
                gi.getPlayer2().getCastle().doDamage(20 + temp);
                gi.getPlayer2().getCastle().getWall().repair(temp);
                //NOTE FOR KODY: Probably change the three lines above to just 1 and add a Castle.setHealth() along with doDamage to avoid make it more simple
                gi.getResources().adjustPlayerWeapons(-TROJAN_HORSE_COST); //Cost
            } else {
                float temp = gi.getPlayer1().getCastle().getWall().getHealth();
                gi.getPlayer1().getCastle().doDamage(20 + temp);
                gi.getPlayer1().getCastle().getWall().repair(temp);
                gi.getResources().adjustComputerWeapons(-TROJAN_HORSE_COST); //Cost
            }
        }else if(cardID == BLACK_PLAGUE){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerBuilders(-1);
                gi.getResources().adjustComputerSoldiers(-1);
                gi.getResources().adjustComputerWizards(-1);
                gi.getResources().adjustPlayerGems(-BLACK_PLAGUE_COST); //Cost
            } else {
                gi.getResources().adjustPlayerBuilders(-1);
                gi.getResources().adjustPlayerSoldiers(-1);
                gi.getResources().adjustPlayerWizards(-1);
                gi.getResources().adjustComputerGems(-BLACK_PLAGUE_COST); //Cost
            }
        }else if(cardID == MERLIN){
            if(gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(32);
                gi.getResources().adjustPlayerGems(-MERLIN_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(32);
                gi.getResources().adjustComputerGems(-MERLIN_COST); //Cost
            }
        }

    }

}

