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

    //region Flame CardAmounts
    public static int WALL_OF_FIRE_AMOUNT = 3;
    public static int CAMPFIRE_AMOUNT = 3;
    public static int FORGE_AMOUNT = 2;
    public static int BOILING_OIL_AMOUNT = 1;
    public static int BONFIRE_AMOUNT = 1;

    public static int FIRE_AMOUNT = 3;
    public static int FIRE_ARROWS_AMOUNT = 3;
    public static int FLAMING_AXE_AMOUNT = 2;
    public static int FLAMING_SHOT_AMOUNT = 1;
    public static int FLAME_LEGION_AMOUNT = 2;

    public static int FIRE_SHAMAN_AMOUNT = 2;
    public static int LAVA_FLOW_AMOUNT = 2;
    public static int COAL_AMOUNT = 2;
    public static int BLACKSMITH_AMOUNT = 2;
    public static int FIREBALL_AMOUNT = 1;

    public static int PHOENIX_AMOUNT = 1;
    public static int METEORS_AMOUNT = 1;
    public static int INFERNO_AMOUNT = 1;
    //endregion

    public FlamePack(GameInterface gi){
        this.gi = gi;
    }
}
