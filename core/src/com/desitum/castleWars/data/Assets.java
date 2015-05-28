package com.desitum.castleWars.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.desitum.castleWars.libraries.Drawing;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class Assets {

    public static Texture invisible;
    public static Texture discard;
    public static Texture trashCan;
    public static Texture trashCanSelected;

    //Button Textures
    public static Texture playButtonUp;
    public static Texture playButtonDown;
    public static Texture settingsButtonUp;
    public static Texture settingsButtonDown;
    public static Texture cancelButtonUp;
    public static Texture cancelButtonDown;
    public static Texture okButtonUp;
    public static Texture okButtonDown;
    public static Texture exitButtonUp;
    public static Texture exitButtonDown;

    //Menu Textures
    public static Texture textFieldBackground;
    public static Texture menuBackground;

    //Game Textures
    public static Texture playerCastle;
    public static Texture computerCastle;
    public static Texture gameGround;
    public static Texture gameSky;
    public static Texture menuArea;
    public static Texture cardBack;
    public static Texture cardBlank;

    public static Texture buildCardReinforce;
    public static Texture buildCardFortify;
    public static Texture buildCardCastle;
    public static Texture buildCardBarrier;
    public static Texture buildCardWall;
    public static Texture buildCardGreatWall;
    public static Texture buildCardArchitect;
    public static Texture buildCardReserve;
    public static Texture buildCardSabotage;
    public static Texture buildCardStronghold;

    public static Texture gemCardCreateStones;
    public static Texture gemCardCreateWeapons;
    public static Texture gemCardCreateGems;
    public static Texture gemCardDestroyStones;
    public static Texture gemCardDestroyWeapons;
    public static Texture gemCardDestroyGems;
    public static Texture gemCardMage;
    public static Texture gemCardHatTrick;
    public static Texture gemCardLightningStrike;
    public static Texture gemCardBlast;

    public static Texture attackCardRecruiter;
    public static Texture attackCardSpearman;
    public static Texture attackCardRam;
    public static Texture attackCardCatapult;
    public static Texture attackCardLegion;
    public static Texture attackCardTrebuchet;

    public static Texture hammer;
    public static Texture stone;
    public static Texture spear;
    public static Texture steak;
    public static Texture wand;
    public static Texture gem;

    //Misc Textures
    public static BitmapFont textFieldFont;

    public static void loadMenuTextures() {
        invisible = Drawing.getHollowRectangle(10, 10, 1, new Color(0, 0, 0, 0));
        playButtonUp = new Texture("menu/play_button_up.png");
        playButtonDown = new Texture("menu/play_button_down.png");
        settingsButtonUp = new Texture("menu/settings_button_up.png");
        settingsButtonDown = new Texture("menu/settings_button_down.png");
        cancelButtonUp = new Texture("menu/cancel_button_up.png");
        cancelButtonDown = new Texture("menu/cancel_button_down.png");
        okButtonUp = new Texture("menu/ok_button_up.png");
        okButtonDown = new Texture("menu/ok_button_down.png");
        exitButtonUp = new Texture("menu/exit_button_up.png");
        exitButtonDown = new Texture("menu/exit_button_down.png");

        textFieldBackground = Drawing.getTextureRoundedRectangle(50, 50, 5, new Color(0.5f, 0.2f, 0.1f, 0.8f));
        menuBackground = new Texture("menu/menu_bg.png");

        textFieldFont = new BitmapFont(Gdx.files.internal("font/cartoon.fnt"), Gdx.files.internal("font/cartoon.png"), false);
        textFieldFont.setScale(0.15f);
        textFieldFont.setColor(Color.WHITE);
    }

    public static void loadGameTextures() {
        playerCastle = new Texture("game/castle1.png");
        computerCastle = new Texture("game/castle2.png");
        gameGround = new Texture("game/ground.png");
        gameSky = new Texture("game/sky.png");
        menuArea = new Texture("game/area.png");
        cardBack = new Texture("game/card_back.png");
        cardBlank = new Texture("game/card_blank.png");

        hammer = new Texture("game/hammer.png");
        stone = new Texture("game/stone.png");
        spear = new Texture("game/spear.png");
        steak = new Texture("game/steak.png");
        wand = new Texture("game/wand.png");
        gem = new Texture("game/gem.png");

        discard = new Texture("game/cards/discard.png");
        trashCan = new Texture("game/trash_unselected.png");
        trashCanSelected = new Texture("game/trash_selected.png");
    }

    public static void loadCards() {
        loadBuildCards();
        loadAttackCards();
        loadGemCards();
    }

    private static void loadBuildCards() {
        buildCardReinforce = new Texture("game/cards/reinforce.png");
        buildCardFortify = new Texture("game/cards/fortify.png");
        buildCardCastle = new Texture("game/cards/castle.png");
        buildCardBarrier = new Texture("game/cards/barrier.png");
        buildCardWall = new Texture("game/cards/wall.png");
        buildCardGreatWall = new Texture("game/cards/great_wall.png");
        buildCardArchitect = new Texture("game/cards/architect.png");
        buildCardReserve = new Texture("game/cards/reserve.png");
        buildCardSabotage = new Texture("game/cards/sabotage.png");
        buildCardStronghold = new Texture("game/cards/stronghold.png");
    }

    private static void loadAttackCards() {
        attackCardRecruiter = new Texture("game/cards/recruiter.png");
        attackCardSpearman = new Texture("game/cards/spearman.png");
        attackCardRam = new Texture("game/cards/ram.png");
        attackCardCatapult = new Texture("game/cards/catapult.png");
        attackCardLegion = new Texture("game/cards/legion.png");
        attackCardTrebuchet = new Texture("game/cards/trebuchet.png");
    }

    private static void loadGemCards() {
        gemCardCreateStones = new Texture("game/cards/create_stones.png");
        gemCardCreateWeapons = new Texture("game/cards/create_weapons.png");
        gemCardCreateGems = new Texture("game/cards/create_gems.png");
        gemCardDestroyStones = new Texture("game/cards/destroy_stones.png");
        gemCardDestroyWeapons = new Texture("game/cards/destroy_weapons.png");
        gemCardDestroyGems = new Texture("game/cards/destroy_gems.png");
        gemCardMage = new Texture("game/cards/mage.png");
        gemCardHatTrick = new Texture("game/cards/top_hat.png");
        gemCardLightningStrike = new Texture("game/cards/lightning_strike.png");
        gemCardBlast = new Texture("game/cards/blast.png");
    }

    public static void loadSounds() {

    }

    public static void dispose() {
        //TODO Add Disposals
    }
}
