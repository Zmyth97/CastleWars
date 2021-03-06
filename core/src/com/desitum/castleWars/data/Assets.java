package com.desitum.castleWars.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.desitum.castleWars.libraries.Drawing;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class Assets {

    public static Texture invisible;
    public static Texture blur;
    public static Texture discard;
    public static Texture max;
    public static Texture sold;
    public static Texture trashCan;
    public static Texture trashCanSelected;

    //Store
    public static Texture flameAd;
    public static Texture japaneseAd;
    public static Texture extraSlotAd;

    //Button Textures
    public static Texture playButton;
    public static Texture settings;
    public static Texture leaderboardButton;
    public static Texture cancelButton;
    public static Texture okButton;
    public static Texture buildDeckButton;
    public static Texture buyButton;
    public static Texture storeButton;

    //Round Button Textures
    public static Texture settingsButtonRound;
    public static Texture okButtonRound;
    public static Texture cancelButtonRound;
    public static Texture exitButtonRound;

    //Layout Textures
    public static Texture menuBackground;
    public static Texture toggleButtonOff;
    public static Texture toggleButtonOn;
    public static Texture upArrow;
    public static Texture downArrow;
    public static Texture leftArrow;
    public static Texture rightArrow;
    public static Texture popupMenuBackground;

    //Game Textures
    public static Texture playerFlameCastle;
    public static Texture playerJapaneseCastle;
    public static Texture playerCastle;
    public static Texture playerWall;
    public static Texture computerJapaneseCastle;
    public static Texture computerFlameCastle;
    public static Texture computerCastle;
    public static Texture computerWall;
    public static Texture flameGround;
    public static Texture gameGround;
    public static Texture flameSky;
    public static Texture japaneseSky;
    public static Texture gameSky;
    public static Texture cloud1;
    public static Texture cloud2;
    public static Texture cloud3;
    public static Texture menuArea;
    public static Texture cardBack;

    //Basic Build Cards
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

    //Basic Gem Cards
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

    //Basic Attack Cards
    public static Texture attackCardRecruiter;
    public static Texture attackCardSpearman;
    public static Texture attackCardRam;
    public static Texture attackCardCatapult;
    public static Texture attackCardLegion;
    public static Texture attackCardTrebuchet;
    public static Texture attackCardAssassin;
    public static Texture attackCardBurglar;
    public static Texture attackCardThief;
    public static Texture attackCardRaid;

    //Basic Gold Cards
    public static Texture goldCardJericho;
    public static Texture goldCardTrojanHorse;
    public static Texture goldCardBlackPlague;
    public static Texture goldCardMerlin;
    public static Texture goldCardDuplicate;

    //Resource Images
    public static Texture hammer;
    public static Texture stone;
    public static Texture spear;
    public static Texture wand;
    public static Texture gem;
    public static Texture helmet;
    public static Texture castle;
    public static Texture wall;

    //region Flame Cards
    public static Texture flameBuildCardWallOfFire;
    public static Texture flameBuildCardCampfire;
    public static Texture flameBuildCardForge;
    public static Texture flameBuildCardBoilingOil;
    public static Texture flameBuildCardBonfire;

    public static Texture flameAttackCardFire;
    public static Texture flameAttackCardFireArrows;
    public static Texture flameAttackCardFlamingAxe;
    public static Texture flameAttackCardFlamingShot;
    public static Texture flameAttackCardFlameLegion;

    public static Texture flameGemCardFireShaman;
    public static Texture flameGemCardLavaFlow;
    public static Texture flameGemCardCoal;
    public static Texture flameGemCardBlacksmith;
    public static Texture flameGemCardFireball;

    public static Texture flameGoldCardPhoenix;
    public static Texture flameGoldCardMeteors;
    public static Texture flameGoldCardInferno;
    //endregion
    //region Japanese Cards
    public static Texture japaneseBuildCardStockade;
    public static Texture japaneseBuildCardFortress;
    public static Texture japaneseBuildCardMonastery;
    public static Texture japaneseBuildCardRampart;
    public static Texture japaneseBuildCardCitadel;

    public static Texture japaneseAttackCardAshigaru;
    public static Texture japaneseAttackCardShuriken;
    public static Texture japaneseAttackCardKatana;
    public static Texture japaneseAttackCardSamurai;
    public static Texture japaneseAttackCardDojo;

    public static Texture japaneseGemCardShrine;
    public static Texture japaneseGemCardQuarry;
    public static Texture japaneseGemCardTemple;
    public static Texture japaneseGemCardRicePaddy;
    public static Texture japaneseGemCardSeppuku;

    public static Texture japaneseGoldCardNinja;
    public static Texture japaneseGoldCardDragon;
    public static Texture japaneseGoldCardShogun;
    //endregion

    //Misc Textures
    public static BitmapFont textFieldFont;

    //Music & Sounds
    public static Music menuMusic;
    public static Sound buttonSound;


    public static void loadMenuTextures() {
        invisible = Drawing.getHollowRectangle(10, 10, 1, new Color(0, 0, 0, 0));
        blur = new Texture("menu/blur.png");
        sold = new Texture("menu/sold.png");

        //Store
        flameAd = new Texture("menu/flame_ad.png");
        japaneseAd = new Texture("menu/japan_ad.png");
        extraSlotAd = new Texture("menu/extra_slot_ad.png");

        //Button Textures
        playButton = new Texture("menu/play_button.png");
        settings = new Texture("menu/settings.png");
        leaderboardButton = new Texture("menu/leaderboard_button.png");
        cancelButton = new Texture("menu/cancel_button.png");
        okButton = new Texture("menu/ok_button.png");
        buildDeckButton = new Texture("menu/build_deck_button.png");
        buyButton = new Texture("menu/buy_button.png");
        storeButton = new Texture("menu/shopping_button.png");

        //Round Button Textures
        settingsButtonRound = new Texture("menu/settings_button_round.png");
        okButtonRound = new Texture("menu/ok_button_round.png");
        cancelButtonRound = new Texture("menu/cancel_button_round.png");
        exitButtonRound = new Texture("menu/exit_button_round.png");

        menuBackground = new Texture("menu/menu_bg.png");

        toggleButtonOff = new Texture("menu/toggleButtonOff.png");
        toggleButtonOn = new Texture("menu/toggleButtonOn.png");
        upArrow = new Texture("menu/up_arrow_button.png");
        downArrow = new Texture("menu/down_arrow_button.png");
        leftArrow = new Texture("menu/left_arrow_button.png");
        rightArrow = new Texture("menu/right_arrow_button.png");
        popupMenuBackground = new Texture("menu/popupMenuBackground.png");

        textFieldFont = new BitmapFont(Gdx.files.internal("font/cartoon.fnt"), Gdx.files.internal("font/cartoon.png"), false);
        textFieldFont.setScale(0.15f);
        textFieldFont.setColor(Color.WHITE);
    }

    public static void loadGameTextures() {
        playerCastle = new Texture("game/player_castle.png");
        playerFlameCastle = new Texture("game/player_flame_castle.png");
        playerJapaneseCastle = new Texture("game/player_japanese_castle.png");
        playerWall = new Texture("game/player_wall.png");
        computerCastle = new Texture("game/computer_castle.png");
        computerFlameCastle = new Texture("game/computer_flame_castle.png");
        computerJapaneseCastle = new Texture("game/computer_japanese_castle.png");
        computerWall = new Texture("game/computer_wall.png");
        gameGround = new Texture("game/ground.png");
        flameGround = new Texture("game/flame_ground.png");
        gameSky = new Texture("game/sky.png");
        japaneseSky = new Texture("game/japanese_sky.png");
        flameSky = new Texture("game/flame_sky.png");
        cloud1 = new Texture("game/cloud1.png");
        cloud2 = new Texture("game/cloud2.png");
        cloud3 = new Texture("game/cloud3.png");
        menuArea = new Texture("game/area.png");
        cardBack = new Texture("game/card_back.png");

        hammer = new Texture("game/hammer.png");
        stone = new Texture("game/stone.png");
        spear = new Texture("game/spear.png");
        wand = new Texture("game/wand.png");
        gem = new Texture("game/gem.png");
        helmet = new Texture("game/helmet.png");
        castle = new Texture("game/castle.png");
        wall = new Texture("game/wall.png");

        discard = new Texture("game/cards/discard.png");
        max = new Texture("game/max.png");
        trashCan = new Texture("game/trash_unselected.png");
        trashCanSelected = new Texture("game/trash_selected.png");
    }

    public static void loadCards() {
        loadBuildCards();
        loadAttackCards();
        loadGemCards();
        loadGoldCards();
        loadFlameCards();
        loadJapaneseCards();
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
        attackCardAssassin = new Texture("game/cards/assassin.png");
        attackCardBurglar = new Texture("game/cards/burglar.png");
        attackCardThief = new Texture("game/cards/thief.png");
        attackCardRaid = new Texture("game/cards/raid.png");
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

    private static void loadGoldCards() {
        goldCardJericho = new Texture("game/cards/jericho.png");
        goldCardTrojanHorse = new Texture("game/cards/trojan_horse.png");
        goldCardBlackPlague = new Texture("game/cards/black_plague.png");
        goldCardMerlin = new Texture("game/cards/merlin.png");
        goldCardDuplicate = new Texture("game/cards/duplicate.png");
    }

    private static void loadFlameCards() {
        flameBuildCardWallOfFire = new Texture("game/flame_pack/wall_of_fire.png");
        flameBuildCardCampfire = new Texture("game/flame_pack/campfire.png");
        flameBuildCardForge = new Texture("game/flame_pack/forge.png");
        flameBuildCardBoilingOil = new Texture("game/flame_pack/boiling_oil.png");
        flameBuildCardBonfire = new Texture("game/flame_pack/bonfire.png");

        flameAttackCardFire = new Texture("game/flame_pack/fire.png");
        flameAttackCardFireArrows = new Texture("game/flame_pack/fire_arrows.png");
        flameAttackCardFlamingAxe = new Texture("game/flame_pack/flaming_axe.png");
        flameAttackCardFlamingShot = new Texture("game/flame_pack/flaming_shot.png");
        flameAttackCardFlameLegion = new Texture("game/flame_pack/flame_legion.png");

        flameGemCardFireShaman = new Texture("game/flame_pack/fire_shaman.png");
        flameGemCardLavaFlow = new Texture("game/flame_pack/lava_flow.png");
        flameGemCardCoal = new Texture("game/flame_pack/coal.png");
        flameGemCardBlacksmith = new Texture("game/flame_pack/blacksmith.png");
        flameGemCardFireball = new Texture("game/flame_pack/fireball.png");

        flameGoldCardPhoenix = new Texture("game/flame_pack/phoenix.png");
        flameGoldCardMeteors = new Texture("game/flame_pack/meteors.png");
        flameGoldCardInferno = new Texture("game/flame_pack/inferno.png");
    }

    private static void loadJapaneseCards() {
        japaneseBuildCardStockade = new Texture("game/japanese_pack/stockade.png");
        japaneseBuildCardFortress = new Texture("game/japanese_pack/fortress.png");
        japaneseBuildCardMonastery = new Texture("game/japanese_pack/monastery.png");
        japaneseBuildCardRampart = new Texture("game/japanese_pack/rampart.png");
        japaneseBuildCardCitadel = new Texture("game/japanese_pack/citadel.png");

        japaneseAttackCardAshigaru = new Texture("game/japanese_pack/ashigaru.png");
        japaneseAttackCardShuriken = new Texture("game/japanese_pack/shuriken.png");
        japaneseAttackCardKatana = new Texture("game/japanese_pack/katana.png");
        japaneseAttackCardSamurai = new Texture("game/japanese_pack/samurai.png");
        japaneseAttackCardDojo = new Texture("game/japanese_pack/dojo.png");

        japaneseGemCardShrine = new Texture("game/japanese_pack/shrine.png");
        japaneseGemCardQuarry = new Texture("game/japanese_pack/quarry.png");
        japaneseGemCardTemple = new Texture("game/japanese_pack/temple.png");
        japaneseGemCardRicePaddy = new Texture("game/japanese_pack/rice_paddy.png");
        japaneseGemCardSeppuku = new Texture("game/japanese_pack/seppuku.png");

        japaneseGoldCardNinja = new Texture("game/japanese_pack/ninja.png");
        japaneseGoldCardDragon = new Texture("game/japanese_pack/dragon.png");
        japaneseGoldCardShogun = new Texture("game/japanese_pack/shogun.png");
    }

    public static void loadSounds() {
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("sound/Click.mp3"));
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/menu_loop1.wav"));
        menuMusic.setLooping(true);
    }

    public static void dispose() {
        //STOPPED HERE BECAUSE I REALIZED IT MIGHT NOT BE NEEDED. TALK TO MEH!
    }
}
