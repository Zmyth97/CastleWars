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
    public static int STOCKADE_AMOUNT = 3;
    public static int FORTRESS_AMOUNT = 2;
    public static int MONASTERY_AMOUNT = 2;
    public static int RAMPART_AMOUNT = 1;
    public static int CITADEL_AMOUNT = 1;

    public static int ASHIGARU_AMOUNT = 3;
    public static int SHURIKEN_AMOUNT = 3;
    public static int KATANA_AMOUNT = 2;
    public static int SAMURAI_AMOUNT = 1;
    public static int DOJO_AMOUNT = 2;

    public static int SHRINE_AMOUNT = 2;
    public static int QUARRY_AMOUNT = 2;
    public static int TEMPLE_AMOUNT = 2;
    public static int RICE_PADDY_AMOUNT = 2;
    public static int SEPPUKU_AMOUNT = 1;

    public static int NINJA_AMOUNT = 1;
    public static int DRAGON_AMOUNT = 1;
    public static int SHOGUN_AMOUNT = 1;
    //endregion

    public JapanesePack(GameInterface gi) {
        this.gi = gi;
    }
}
