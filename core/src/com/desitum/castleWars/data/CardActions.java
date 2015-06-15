package com.desitum.castleWars.data;

import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.packs.FlamePack;
import com.desitum.castleWars.packs.JapanesePack;
import com.desitum.castleWars.world.GameInterface;
import com.desitum.castleWars.world.GameWorld;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class CardActions {
    //region Build Cards
    public static final int BARRIER = 1; //+4 Wall
    public static final int WALL = 2; //+10 Wall
    public static final int GREATWALL = 3; //+18 Wall
    public static final int REINFORCE = 4; //+6 Castle
    public static final int FORTIFY = 5; //+12 Castle
    public static final int CASTLE = 6;//+20 Castle
    public static final int ARCHITECT = 7; //+1 Builder
    public static final int RESERVE = 8; //Castle +8, Wall -4
    public static final int SABOTAGE = 9; //Enemy Castle -4, Castle +8
    public static final int STRONGHOLD = 10; //Castle + 10, Wall + 10
    //region Build Costs
    public static final int REINFORCE_COST = 10;
    //endregion
    public static final int FORTIFY_COST = 20;
    public static final int CASTLE_COST = 36;
    public static final int BARRIER_COST = 8;
    public static final int WALL_COST = 20;
    public static final int GREATWALL_COST = 30;
    public static final int ARCHITECT_COST = 16;
    public static final int RESERVE_COST = 8;
    public static final int SABOTAGE_COST = 20;
    public static final int STRONGHOLD_COST = 36;
    //region Attack Cards
    public static final int RECRUITER = 100; //+1 Soldier
    //endregion
    public static final int SPEARMAN = 101; //+2 Attack
    public static final int RAM = 102; //+6 Attack
    public static final int CATAPULT = 103; //+12 Attack
    public static final int LEGION = 109; //+16 Attack
    public static final int TREBUCHET = 104; //+20 Attack
    public static final int ASSASSIN = 105; //Kill 1 Random Person
    public static final int BURGLAR = 106; //Steal 8 of each resource
    public static final int THIEF = 107; //Steal 24 of each resource
    public static final int RAID = 108; //Destroy 24 of a resource
    //region Attack Costs
    public static final int RECRUITER_COST = 16;
    //endregion
    public static final int SPEARMAN_COST = 4;
    public static final int RAM_COST = 12;
    public static final int CATAPULT_COST = 24;
    public static final int LEGION_COST = 30;
    public static final int TREBUCHET_COST = 40;
    public static final int ASSASSIN_COST = 20;
    public static final int BURGLAR_COST = 18;
    public static final int THIEF_COST = 54;
    public static final int RAID_COST = 14;
    //region Magic Cards
    public static final int CREATE_STONES = 200; //+16 Stones
    //endregion
    public static final int CREATE_WEAPONS = 201; //+16 Weapons
    public static final int CREATE_GEMS = 202; //+16 Gems
    public static final int DESTROY_STONES = 203; //-16 Stones
    public static final int DESTROY_WEAPONS = 204; //-16 Weapons
    public static final int DESTROY_GEMS = 205; //-16 Gems
    public static final int MAGE = 206; //+1 Wizard
    public static final int HAT_TRICK = 207; //+4 all res
    public static final int LIGHTNING_STRIKE = 208; //+16 Attack
    public static final int BLAST = 209; //+8 Attack
    //region Magic Costs
    public static final int CREATE_STONES_COST = 10;
    //endregion
    public static final int CREATE_WEAPONS_COST = 10;
    public static final int CREATE_GEMS_COST = 10;
    public static final int DESTROY_STONES_COST = 10;
    public static final int DESTROY_WEAPONS_COST = 10;
    public static final int DESTROY_GEMS_COST = 10;
    public static final int MAGE_COST = 16;
    public static final int HAT_TRICK_COST = 4;
    public static final int LIGHTNING_STRIKE_COST = 32;
    public static final int BLAST_COST = 16;
    public static final int JERICHO = 300; //Destroy Enemy Wall (ATTACK)
    //endregion

    //region GoldenCards
    public static final int TROJAN_HORSE = 301; //Destroy 20 Castle (Ignores Wall) (ATTACK)
    public static final int BLACK_PLAGUE = 302; //-1 of All People (MAGIC)
    public static final int MERLIN = 303; //+32 Attack (MAGIC)
    public static final int DUPLICATE = 304; //Double Existing Wall Height
    //region Golden Costs
    public static final int JERICHO_COST = 64;

    //endRegion
    public static final int TROJAN_HORSE_COST = 56;
    public static final int BLACK_PLAGUE_COST = 72;
    public static final int MERLIN_COST = 64;
    public static final int DUPLICATE_COST = 52;
    private GameInterface gi;
    //endregion


    public CardActions(GameInterface gi) {
        this.gi = gi;
    }


    public void doCardAction(int cardID) {
        //region BUILD CARDS
        if (cardID == REINFORCE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(6);
                gi.getResources().subtractPlayerStones(REINFORCE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(6);
                gi.getResources().subtractComputerStones(REINFORCE_COST); //Cost
            }
        } else if (cardID == FORTIFY) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(12);
                gi.getResources().subtractPlayerStones(FORTIFY_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(12);
                gi.getResources().subtractComputerStones(FORTIFY_COST); //Cost
            }
        } else if (cardID == CASTLE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(20);
                gi.getResources().subtractPlayerStones(CASTLE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(20);
                gi.getResources().subtractComputerStones(CASTLE_COST); //Cost
            }
        } else if (cardID == BARRIER) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(4);
                gi.getResources().subtractPlayerStones(BARRIER_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(4);
                gi.getResources().subtractComputerStones(BARRIER_COST); //Cost
            }
        } else if (cardID == WALL) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(10);
                gi.getResources().subtractPlayerStones(WALL_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(10);
                gi.getResources().subtractComputerStones(WALL_COST); //Cost
            }
        } else if (cardID == GREATWALL) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(18);
                gi.getResources().subtractPlayerStones(GREATWALL_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(18);
                gi.getResources().subtractComputerStones(GREATWALL_COST); //Cost
            }
        } else if (cardID == ARCHITECT) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerBuilders(1);
                gi.getResources().subtractPlayerStones(ARCHITECT_COST); //Cost
            } else {
                gi.getResources().adjustComputerBuilders(1);
                gi.getResources().subtractComputerStones(ARCHITECT_COST); //Cost
            }
        } else if (cardID == RESERVE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(8);
                gi.getPlayer1().getCastle().getWall().doDamage(4, true);
                gi.getResources().subtractPlayerStones(RESERVE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(8);
                gi.getPlayer2().getCastle().getWall().doDamage(4, true);
                gi.getResources().subtractComputerStones(RESERVE_COST); //Cost
            }
        } else if (cardID == SABOTAGE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(10);
                gi.getPlayer2().getCastle().doDamage(4);
                gi.getResources().subtractPlayerStones(SABOTAGE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(10);
                gi.getPlayer1().getCastle().doDamage(4);
                gi.getResources().subtractComputerStones(SABOTAGE_COST); //Cost
            }
        } else if (cardID == STRONGHOLD) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(10);
                gi.getPlayer1().getCastle().getWall().repair(10);
                gi.getResources().subtractPlayerStones(STRONGHOLD_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(10);
                gi.getPlayer2().getCastle().getWall().repair(10);
                gi.getResources().subtractComputerStones(STRONGHOLD_COST); //Cost
            }
        }
        //ATTACK CARDS
        else if (cardID == RECRUITER) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerSoldiers(1);
                gi.getResources().subtractPlayerWeapons(RECRUITER_COST); //Cost
            } else {
                gi.getResources().adjustComputerSoldiers(1);
                gi.getResources().subtractComputerWeapons(RECRUITER_COST); //Cost
            }
        } else if (cardID == SPEARMAN) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(2);
                gi.getResources().subtractPlayerWeapons(SPEARMAN_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(2);
                gi.getResources().subtractComputerWeapons(SPEARMAN_COST); //Cost
            }
        } else if (cardID == RAM) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(6);
                gi.getResources().subtractPlayerWeapons(RAM_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(6);
                gi.getResources().subtractComputerWeapons(RAM_COST); //Cost
            }
        } else if (cardID == CATAPULT) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(12);
                gi.getResources().subtractPlayerWeapons(CATAPULT_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(12);
                gi.getResources().subtractComputerWeapons(CATAPULT_COST); //Cost
            }
        } else if (cardID == LEGION) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(16);
                gi.getResources().subtractPlayerWeapons(LEGION_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(16);
                gi.getResources().subtractComputerWeapons(LEGION_COST); //Cost
            }
        } else if (cardID == TREBUCHET) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(20);
                gi.getResources().subtractPlayerWeapons(TREBUCHET_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(20);
                gi.getResources().subtractComputerWeapons(TREBUCHET_COST); //Cost
            }
        } else if (cardID == ASSASSIN) { //This Method seems a little redundant, but it does this to try and kill a random person that is greater than 1 (so that it will actually subtract one since the minimum is 1)
            int randomInt = (int) (Math.random() * 2);
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().subtractPlayerWeapons(ASSASSIN_COST); //Cost
                if (randomInt == 0 && gi.getResources().getComputerBuilders() > 1) {
                    gi.getResources().adjustComputerBuilders(-1);
                } else if (randomInt == 1 && gi.getResources().getComputerSoldiers() > 1) {
                    gi.getResources().adjustComputerSoldiers(-1);
                } else if (randomInt == 2 && gi.getResources().getComputerWizards() > 1) {
                    gi.getResources().adjustComputerWizards(-1);
                } else if (gi.getResources().getComputerBuilders() > 1) {
                    gi.getResources().adjustComputerBuilders(-1);
                } else if (gi.getResources().getComputerSoldiers() > 1) {
                    gi.getResources().adjustComputerSoldiers(-1);
                } else if (gi.getResources().getComputerWizards() > 1) {
                    gi.getResources().adjustComputerWizards(-1);
                } else {
                    gi.getResources().adjustComputerBuilders(-1);
                }
            } else {
                gi.getResources().subtractComputerWeapons(ASSASSIN_COST); //Cost
                if (randomInt == 0 && gi.getResources().getPlayerBuilders() > 1) {
                    gi.getResources().adjustPlayerBuilders(-1);
                } else if (randomInt == 1 && gi.getResources().getPlayerSoldiers() > 1) {
                    gi.getResources().adjustPlayerSoldiers(-1);
                } else if (randomInt == 2 && gi.getResources().getPlayerWizards() > 1) {
                    gi.getResources().adjustPlayerWizards(-1);
                } else if (gi.getResources().getPlayerBuilders() > 1) {
                    gi.getResources().adjustPlayerBuilders(-1);
                } else if (gi.getResources().getPlayerSoldiers() > 1) {
                    gi.getResources().adjustPlayerSoldiers(-1);
                } else if (gi.getResources().getPlayerWizards() > 1) {
                    gi.getResources().adjustPlayerWizards(-1);
                } else {
                    gi.getResources().adjustPlayerBuilders(-1);
                }
            }
        } else if (cardID == BURGLAR) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().subtractPlayerWeapons(BURGLAR_COST); //Cost
                gi.getResources().adjustPlayerStones(Math.abs(gi.getResources().adjustComputerStones(-8)));
                gi.getResources().adjustPlayerWeapons(Math.abs(gi.getResources().adjustComputerWeapons(-8)));
                gi.getResources().adjustPlayerGems(Math.abs(gi.getResources().adjustComputerGems(-8)));
            } else {
                gi.getResources().subtractComputerWeapons(BURGLAR_COST); //Cost
                gi.getResources().adjustComputerStones(Math.abs(gi.getResources().adjustPlayerStones(-8)));
                gi.getResources().adjustComputerWeapons(Math.abs(gi.getResources().adjustPlayerWeapons(-8)));
                gi.getResources().adjustComputerGems(Math.abs(gi.getResources().adjustPlayerGems(-8)));

            }
        } else if (cardID == THIEF) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().subtractPlayerWeapons(THIEF_COST); //Cost
                gi.getResources().adjustPlayerStones(Math.abs(gi.getResources().adjustComputerStones(-24)));
                gi.getResources().adjustPlayerWeapons(Math.abs(gi.getResources().adjustComputerWeapons(-24)));
                gi.getResources().adjustPlayerGems(Math.abs(gi.getResources().adjustComputerGems(-24)));
            } else {
                gi.getResources().subtractComputerWeapons(THIEF_COST); //Cost
                gi.getResources().adjustComputerStones(Math.abs(gi.getResources().adjustPlayerStones(-24)));
                gi.getResources().adjustComputerWeapons(Math.abs(gi.getResources().adjustPlayerWeapons(-24)));
                gi.getResources().adjustComputerGems(Math.abs(gi.getResources().adjustPlayerGems(-24)));
            }
        } else if (cardID == RAID) {
            int randomInt = (int) (Math.random() * 2);
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().subtractPlayerWeapons(RAID_COST); //Cost
                if (randomInt == 0) {
                    gi.getResources().adjustComputerStones(-24);
                } else if (randomInt == 1) {
                    gi.getResources().adjustComputerWeapons(-24);
                } else {
                    gi.getResources().adjustComputerGems(-24);
                }
            } else {
                gi.getResources().subtractComputerWeapons(RAID_COST); //Cost
                if (randomInt == 0) {
                    gi.getResources().adjustPlayerStones(-24);
                } else if (randomInt == 1) {
                    gi.getResources().adjustPlayerWeapons(-24);
                } else {
                    gi.getResources().adjustPlayerGems(-24);
                }
            }
        }
        //MAGIC CARDS
        else if (cardID == CREATE_STONES) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerStones(16);
                gi.getResources().subtractPlayerGems(CREATE_STONES_COST); //Cost
            } else {
                gi.getResources().adjustComputerStones(16);
                gi.getResources().subtractComputerGems(CREATE_STONES_COST); //Cost
            }
        } else if (cardID == CREATE_WEAPONS) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(16);
                gi.getResources().subtractPlayerGems(CREATE_WEAPONS_COST); //Cost
            } else {
                gi.getResources().adjustComputerWeapons(16);
                gi.getResources().subtractComputerGems(CREATE_WEAPONS_COST); //Cost
            }
        } else if (cardID == CREATE_GEMS) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerGems(16);
                gi.getResources().subtractPlayerGems(CREATE_GEMS_COST); //Cost
            } else {
                gi.getResources().adjustComputerGems(16);
                gi.getResources().subtractComputerGems(CREATE_GEMS_COST); //Cost
            }
        } else if (cardID == DESTROY_STONES) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerStones(-16);
                gi.getResources().subtractPlayerGems(DESTROY_STONES_COST); //Cost
            } else {
                gi.getResources().adjustPlayerStones(-16);
                gi.getResources().subtractComputerGems(DESTROY_STONES_COST); //Cost
            }
        } else if (cardID == DESTROY_WEAPONS) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerWeapons(-16);
                gi.getResources().subtractPlayerGems(DESTROY_WEAPONS_COST); //Cost
            } else {
                gi.getResources().adjustPlayerWeapons(-16);
                gi.getResources().subtractComputerGems(DESTROY_WEAPONS_COST); //Cost
            }
        } else if (cardID == DESTROY_GEMS) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerGems(-16);
                gi.getResources().subtractPlayerGems(DESTROY_GEMS_COST); //Cost
            } else {
                gi.getResources().adjustPlayerGems(-16);
                gi.getResources().subtractComputerGems(DESTROY_GEMS_COST); //Cost
            }
        } else if (cardID == MAGE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWizards(1);
                gi.getResources().subtractPlayerGems(MAGE_COST); //Cost
            } else {
                gi.getResources().adjustComputerWizards(1);
                gi.getResources().subtractComputerGems(MAGE_COST); //Cost
            }
        } else if (cardID == HAT_TRICK) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerStones(4);
                gi.getResources().adjustPlayerWeapons(4);
                gi.getResources().adjustPlayerGems(4);
                gi.getResources().subtractPlayerGems(HAT_TRICK_COST); //Cost
            } else {
                gi.getResources().adjustComputerStones(4);
                gi.getResources().adjustComputerWeapons(4);
                gi.getResources().adjustComputerGems(4);
                gi.getResources().subtractComputerGems(HAT_TRICK_COST); //Cost
            }
        } else if (cardID == LIGHTNING_STRIKE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(16);
                gi.getResources().subtractPlayerGems(LIGHTNING_STRIKE_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(16);
                gi.getResources().subtractComputerGems(LIGHTNING_STRIKE_COST); //Cost
            }
        } else if (cardID == BLAST) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(8);
                gi.getResources().subtractPlayerGems(BLAST_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(8);
                gi.getResources().subtractComputerGems(BLAST_COST); //Cost
            }
        }

        //GOLDEN CARDS
        else if (cardID == JERICHO) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().getWall().doDamage(gi.getPlayer2().getCastle().getWall().getHealth(), true);
                gi.getResources().subtractPlayerWeapons(JERICHO_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().getWall().doDamage(gi.getPlayer1().getCastle().getWall().getHealth(), true);
                gi.getResources().subtractComputerWeapons(JERICHO_COST); //Cost
            }
        } else if (cardID == TROJAN_HORSE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().castleDamage(20);
                gi.getResources().subtractPlayerWeapons(TROJAN_HORSE_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().castleDamage(20);
                gi.getResources().subtractComputerWeapons(TROJAN_HORSE_COST); //Cost
            }
        } else if (cardID == BLACK_PLAGUE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerBuilders(-1);
                gi.getResources().adjustComputerSoldiers(-1);
                gi.getResources().adjustComputerWizards(-1);
                gi.getResources().subtractPlayerGems(BLACK_PLAGUE_COST); //Cost
            } else {
                gi.getResources().adjustPlayerBuilders(-1);
                gi.getResources().adjustPlayerSoldiers(-1);
                gi.getResources().adjustPlayerWizards(-1);
                gi.getResources().subtractComputerGems(BLACK_PLAGUE_COST); //Cost
            }
        } else if (cardID == MERLIN) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(32);
                gi.getResources().subtractPlayerGems(MERLIN_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(32);
                gi.getResources().subtractComputerGems(MERLIN_COST); //Cost
            }
        } else if (cardID == DUPLICATE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(gi.getPlayer1().getCastle().getWall().getHealth());
                gi.getResources().subtractPlayerStones(DUPLICATE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(gi.getPlayer2().getCastle().getWall().getHealth());
                gi.getResources().subtractComputerStones(DUPLICATE_COST); //Cost
            }
        }

        //region FlamePack

        if (cardID == FlamePack.WALL_OF_FIRE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(8);
                gi.getResources().subtractPlayerStones(FlamePack.WALL_OF_FIRE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(8);
                gi.getResources().subtractComputerStones(FlamePack.WALL_OF_FIRE_COST); //Cost
            }
        } else if (cardID == FlamePack.CAMPFIRE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(8);
                gi.getResources().subtractPlayerStones(FlamePack.CAMPFIRE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(8);
                gi.getResources().subtractComputerStones(FlamePack.CAMPFIRE_COST); //Cost
            }
        } else if (cardID == FlamePack.FORGE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerBuilders(2);
                gi.getResources().subtractPlayerStones(FlamePack.FORGE_COST); //Cost
            } else {
                gi.getResources().adjustComputerBuilders(2);
                gi.getResources().subtractComputerStones(FlamePack.FORGE_COST); //Cost
            }
        } else if (cardID == FlamePack.BOILING_OIL) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(15);
                gi.getResources().subtractPlayerStones(-FlamePack.BOILING_OIL_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(15);
                gi.getResources().subtractComputerStones(FlamePack.BOILING_OIL_COST); //Cost
            }
        } else if (cardID == FlamePack.BONFIRE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(15);
                gi.getResources().subtractPlayerStones(FlamePack.BONFIRE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(15);
                gi.getResources().subtractComputerStones(FlamePack.BONFIRE_COST); //Cost
            }
        }

        //ATTACK CARDS
        else if (cardID == FlamePack.FIRE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(6);
                gi.getResources().subtractPlayerWeapons(FlamePack.FIRE_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(6);
                gi.getResources().subtractComputerWeapons(FlamePack.FIRE_COST); //Cost
            }
        } else if (cardID == FlamePack.FIRE_ARROWS) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(10);
                gi.getResources().subtractPlayerWeapons(FlamePack.FIRE_ARROWS_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(10);
                gi.getResources().subtractComputerWeapons(FlamePack.FIRE_ARROWS_COST); //Cost
            }
        } else if (cardID == FlamePack.FLAMING_AXE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(12);
                gi.getResources().subtractPlayerWeapons(FlamePack.FLAMING_AXE_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(12);
                gi.getResources().subtractComputerWeapons(FlamePack.FLAMING_AXE_COST); //Cost
            }
        } else if (cardID == FlamePack.FLAMING_SHOT) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(18);
                gi.getResources().subtractPlayerWeapons(FlamePack.FLAMING_SHOT_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(18);
                gi.getResources().subtractComputerWeapons(FlamePack.FLAMING_SHOT_COST); //Cost
            }
        } else if (cardID == FlamePack.FLAME_LEGION) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerSoldiers(2);
                gi.getResources().subtractPlayerWeapons(FlamePack.FLAME_LEGION_COST); //Cost
            } else {
                gi.getResources().adjustComputerSoldiers(2);
                gi.getResources().subtractComputerWeapons(FlamePack.FLAME_LEGION_COST); //Cost
            }
        }

        //MAGIC CARDS
        else if (cardID == FlamePack.FIRE_SHAMAN) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWizards(2);
                gi.getResources().subtractPlayerGems(FlamePack.FIRE_SHAMAN_COST); //Cost
            } else {
                gi.getResources().adjustComputerWizards(2);
                gi.getResources().subtractComputerGems(FlamePack.FIRE_SHAMAN_COST); //Cost
            }
        } else if (cardID == FlamePack.LAVA_FLOW) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerStones(24);
                gi.unlockAchievement(CastleWars.LET_IT_FLOW); //Achievement
                gi.getResources().subtractPlayerGems(FlamePack.LAVA_FLOW_COST); //Cost
            } else {
                gi.getResources().adjustComputerStones(24);
                gi.getResources().subtractComputerGems(FlamePack.LAVA_FLOW_COST); //Cost
            }
        } else if (cardID == FlamePack.COAL) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerGems(24);
                gi.getResources().subtractPlayerGems(FlamePack.COAL_COST); //Cost
            } else {
                gi.getResources().adjustComputerGems(24);
                gi.getResources().adjustComputerGems(FlamePack.COAL_COST); //Cost
            }
        } else if (cardID == FlamePack.BLACKSMITH) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(24);
                gi.getResources().subtractPlayerGems(FlamePack.BLACKSMITH_COST); //Cost
            } else {
                gi.getResources().adjustComputerWeapons(24);
                gi.getResources().subtractComputerGems(FlamePack.BLACKSMITH_COST); //Cost
            }
        } else if (cardID == FlamePack.FIREBALL) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(16);
                gi.getResources().subtractPlayerGems(FlamePack.FIREBALL_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(16);
                gi.getResources().subtractComputerGems(FlamePack.FIREBALL_COST); //Cost
            }
        }

        //GOLDEN CARDS
        else if (cardID == FlamePack.PHEONIX) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerSoldiers(-1);
                gi.getResources().adjustPlayerSoldiers(2);
                gi.unlockAchievement(CastleWars.REBORN); //Achievement
                gi.getResources().subtractPlayerWeapons(FlamePack.PHEONIX_COST); //Cost
            } else {
                gi.getResources().adjustPlayerSoldiers(-1);
                gi.getResources().adjustComputerSoldiers(2);
                gi.getResources().subtractComputerWeapons(FlamePack.PHEONIX_COST); //Cost
            }
        } else if (cardID == FlamePack.METEORS) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(40);
                gi.unlockAchievement(CastleWars.DEATH_FROM_ABOVE); //Achievement
                gi.getResources().subtractPlayerGems(FlamePack.METEORS_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(40);
                gi.getResources().subtractComputerGems(FlamePack.METEORS_COST); //Cost
            }
        } else if (cardID == FlamePack.INFERNO) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerWeapons(-24);
                gi.getResources().adjustComputerStones(-24);
                gi.getResources().adjustComputerGems(-24);
                gi.getResources().subtractPlayerGems(FlamePack.INFERNO_COST); //Cost
            } else {
                gi.getResources().adjustPlayerWeapons(-24);
                gi.getResources().adjustPlayerStones(-24);
                gi.getResources().adjustPlayerGems(-24);
                gi.getResources().subtractComputerGems(FlamePack.INFERNO_COST); //Cost
            }
        }
        //endregion
        //region JapanesePack
        if (cardID == JapanesePack.STOCKADE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(8);
                gi.getResources().subtractPlayerStones(JapanesePack.STOCKADE_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(8);
                gi.getResources().subtractComputerStones(JapanesePack.STOCKADE_COST); //Cost
            }
        } else if (cardID == JapanesePack.FORTRESS) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(8);
                gi.getResources().subtractPlayerStones(JapanesePack.FORTRESS_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(8);
                gi.getResources().subtractComputerStones(JapanesePack.FORTRESS_COST); //Cost
            }
        } else if (cardID == JapanesePack.MONASTERY) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerBuilders(2);
                gi.getResources().subtractPlayerStones(JapanesePack.MONASTERY_COST); //Cost
            } else {
                gi.getResources().adjustComputerBuilders(2);
                gi.getResources().subtractComputerStones(JapanesePack.MONASTERY_COST); //Cost
            }
        } else if (cardID == JapanesePack.RAMPART) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().getWall().repair(15);
                gi.getResources().subtractPlayerStones(JapanesePack.RAMPART_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().getWall().repair(15);
                gi.getResources().subtractComputerStones(JapanesePack.RAMPART_COST); //Cost
            }
        } else if (cardID == JapanesePack.CITADEL) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer1().getCastle().repair(15);
                gi.getResources().subtractPlayerStones(JapanesePack.CITADEL_COST); //Cost
            } else {
                gi.getPlayer2().getCastle().repair(15);
                gi.getResources().subtractComputerStones(JapanesePack.CITADEL_COST); //Cost
            }
        }

        //ATTACK CARDS
        else if (cardID == JapanesePack.ASHIGARU) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(6);
                gi.getResources().subtractPlayerWeapons(JapanesePack.ASHIGARU_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(6);
                gi.getResources().subtractComputerWeapons(JapanesePack.ASHIGARU_COST); //Cost
            }
        } else if (cardID == JapanesePack.SHURIKEN) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(10);
                gi.getResources().subtractPlayerWeapons(JapanesePack.SHURIKEN_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(10);
                gi.getResources().subtractComputerWeapons(JapanesePack.SHURIKEN_COST); //Cost
            }
        } else if (cardID == JapanesePack.KATANA) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(12);
                gi.getResources().subtractPlayerWeapons(JapanesePack.KATANA_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(12);
                gi.getResources().subtractComputerWeapons(JapanesePack.KATANA_COST); //Cost
            }
        } else if (cardID == JapanesePack.SAMURAI) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(18);
                gi.getResources().subtractPlayerWeapons(JapanesePack.SAMURAI_COST); //Cost
            } else {
                gi.getPlayer1().getCastle().doDamage(18);
                gi.getResources().subtractComputerWeapons(JapanesePack.SAMURAI_COST); //Cost
            }
        } else if (cardID == JapanesePack.DOJO) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerSoldiers(2);
                gi.getResources().subtractPlayerWeapons(JapanesePack.DOJO_COST); //Cost
            } else {
                gi.getResources().adjustComputerSoldiers(2);
                gi.getResources().subtractComputerWeapons(JapanesePack.DOJO_COST); //Cost
            }
        }

        //MAGIC CARDS
        else if (cardID == JapanesePack.SHRINE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWizards(2);
                gi.getResources().subtractPlayerGems(JapanesePack.SHRINE_COST); //Cost
            } else {
                gi.getResources().adjustComputerWizards(2);
                gi.getResources().subtractComputerGems(JapanesePack.SHRINE_COST); //Cost
            }
        } else if (cardID == JapanesePack.QUARRY) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerStones(24);
                gi.getResources().subtractPlayerGems(JapanesePack.QUARRY_COST); //Cost
            } else {
                gi.getResources().adjustComputerStones(24);
                gi.getResources().subtractComputerGems(JapanesePack.QUARRY_COST); //Cost
            }
        } else if (cardID == JapanesePack.TEMPLE) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerGems(24);
                gi.getResources().subtractPlayerGems(JapanesePack.TEMPLE_COST); //Cost
            } else {
                gi.getResources().adjustComputerGems(24);
                gi.getResources().subtractComputerGems(JapanesePack.TEMPLE_COST); //Cost
            }
        } else if (cardID == JapanesePack.RICE_PADDY) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(24);
                gi.getResources().subtractPlayerGems(JapanesePack.RICE_PADDY_COST); //Cost
            } else {
                gi.getResources().adjustComputerWeapons(24);
                gi.getResources().subtractComputerGems(JapanesePack.RICE_PADDY_COST); //Cost
            }
        } else if (cardID == JapanesePack.SEPPUKU) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerSoldiers(-1);
                gi.getResources().adjustPlayerWizards(2);
                gi.getResources().subtractPlayerGems(JapanesePack.SEPPUKU_COST); //Cost
                gi.unlockAchievement(CastleWars.HONORABLE_SACRIFICE); //Achievement
            } else {
                gi.getResources().adjustComputerSoldiers(-1);
                gi.getResources().adjustComputerWizards(2);
                gi.getResources().subtractComputerGems(JapanesePack.SEPPUKU_COST); //Cost
            }
        }

        //GOLDEN CARDS
        else if (cardID == JapanesePack.NINJA) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustComputerWizards(-3);
                gi.getResources().subtractPlayerWeapons(JapanesePack.NINJA_COST); //Cost
                gi.unlockAchievement(CastleWars.NIGHT_KILLER); //Achievement
            } else {
                gi.getResources().adjustPlayerWizards(-3);
                gi.getResources().subtractComputerWeapons(JapanesePack.NINJA_COST); //Cost
            }
        } else if (cardID == JapanesePack.DRAGON) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getPlayer2().getCastle().doDamage(40);
                gi.getResources().subtractPlayerGems(JapanesePack.DRAGON_COST); //Cost
                gi.unlockAchievement(CastleWars.DEATH_BY_FIRE); //Achievement
            } else {
                gi.getPlayer1().getCastle().doDamage(40);
                gi.getResources().subtractComputerGems(JapanesePack.DRAGON_COST); //Cost
            }
        } else if (cardID == JapanesePack.SHOGUN) {
            if (gi.getPlayerTurn() == GameWorld.PLAYER) {
                gi.getResources().adjustPlayerWeapons(24);
                gi.getResources().adjustPlayerStones(24);
                gi.getResources().adjustPlayerGems(24);
                gi.getResources().subtractPlayerGems(JapanesePack.SHOGUN_COST); //Cost
            } else {
                gi.getResources().adjustComputerWeapons(24);
                gi.getResources().adjustComputerStones(24);
                gi.getResources().adjustComputerGems(24);
                gi.getResources().subtractComputerGems(JapanesePack.SHOGUN_COST); //Cost
            }
        }
        //endregion
    }

}

