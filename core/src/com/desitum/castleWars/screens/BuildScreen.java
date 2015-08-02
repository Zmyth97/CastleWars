package com.desitum.castleWars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.GooglePlayServicesInterface;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.libraries.ui.widgets.Image;
import com.desitum.castleWars.libraries.ui.layout.Layout;
import com.desitum.castleWars.libraries.ui.listeners.OnClickListener;
import com.desitum.castleWars.libraries.ui.widgets.ButtonMaterial;
import com.desitum.castleWars.libraries.ui.widgets.ScrollArea;
import com.desitum.castleWars.libraries.ui.widgets.Spinner;
import com.desitum.castleWars.libraries.ui.widgets.Widget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.world.GameWorld;

/**
 * Created by Zmyth97 on 6/1/2015.
 */
public class BuildScreen extends KodyWorld implements Screen {

    public static final float SCREEN_WIDTH = 150;
    public static final float SCREEN_HEIGHT = 100;
    public static final float SPINNER_HEIGHT = 12;
    GooglePlayServicesInterface gpgs;
    private CastleWars castleWars;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;
    private ScrollArea cardScrollArea;
    //region Basic PopupSpinners
    //Build Cards
    private Spinner barrierSpinner;
    private Spinner wallSpinner;
    private Spinner greatWallSpinner;
    private Spinner reinforceSpinner;
    private Spinner fortifySpinner;
    private Spinner castleSpinner;
    private Spinner architectSpinner;
    private Spinner reserveSpinner;
    private Spinner sabotageSpinner;
    private Spinner strongHoldSpinner;

    //Attack Cards
    private Spinner recruiterSpinner;
    private Spinner spearmanSpinner;
    private Spinner ramSpinner;
    private Spinner catapultSpinner;
    private Spinner legionSpinner;
    private Spinner trebuchetSpinner;
    private Spinner assassinSpinner;
    private Spinner burglarSpinner;
    private Spinner thiefSpinner;
    private Spinner raidSpinner;

    //Magic Cards
    private Spinner createStonesSpinner;
    private Spinner createWeaponsSpinner;
    private Spinner createGemsSpinner;
    private Spinner destroyStonesSpinner;
    private Spinner destroyWeaponsSpinner;
    private Spinner destroyGemsSpinner;
    private Spinner mageSpinner;
    private Spinner hatTrickSpinner;
    private Spinner lightningSpinner;
    private Spinner blastSpinner;

    //Golden Cards
    private Spinner jerichoSpinner;
    private Spinner trojanHorseSpinner;
    private Spinner blackPlagueSpinner;
    private Spinner merlinSpinner;
    private Spinner duplicateSpinner;
    //endregion
    //region Basic PopupImages
    //Build Cards
    private Image barrierImage;
    private Image wallImage;
    private Image greatWallImage;
    private Image reinforceImage;
    private Image fortifyImage;
    private Image castleImage;
    private Image architectImage;
    private Image reserveImage;
    private Image sabotageImage;
    private Image strongHoldImage;

    //Attack Cards
    private Image recruiterImage;
    private Image spearmanImage;
    private Image ramImage;
    private Image catapultImage;
    private Image legionImage;
    private Image trebuchetImage;
    private Image assassinImage;
    private Image burglarImage;
    private Image thiefImage;
    private Image raidImage;

    //Magic Cards
    private Image createStonesImage;
    private Image createWeaponsImage;
    private Image createGemsImage;
    private Image destroyStonesImage;
    private Image destroyWeaponsImage;
    private Image destroyGemsImage;
    private Image mageImage;
    private Image hatTrickImage;
    private Image lightningImage;
    private Image blastImage;

    //Golden Cards
    private Image jerichoImage;
    private Image trojanHorseImage;
    private Image blackPlagueImage;
    private Image merlinImage;
    private Image duplicateImage;
    //endregion

    //region FlamePack PopupSpinners
    //Build Cards
    private Spinner wallOfFireSpinner;
    private Spinner campfireSpinner;
    private Spinner forgeSpinner;
    private Spinner boilingOilSpinner;
    private Spinner bonfireSpinner;

    //Attack Cards
    private Spinner fireSpinner;
    private Spinner fireArrowsSpinner;
    private Spinner flamingAxeSpinner;
    private Spinner flamingShotSpinner;
    private Spinner flameLegionSpinner;

    //Magic Cards
    private Spinner fireShamanSpinner;
    private Spinner lavaFlowSpinner;
    private Spinner coalSpinner;
    private Spinner blacksmithSpinner;
    private Spinner fireballSpinner;

    //Golden Cards
    private Spinner phoenixSpinner;
    private Spinner meteorsSpinner;
    private Spinner infernoSpinner;
    //endregion
    //region FlamePack PopupImages
    //Build Cards
    private Image wallOfFireImage;
    private Image campfireImage;
    private Image forgeImage;
    private Image boilingOilImage;
    private Image bonfireImage;

    //Attack Cards
    private Image fireImage;
    private Image fireArrowsImage;
    private Image flamingAxeImage;
    private Image flamingShotImage;
    private Image flameLegionImage;

    //Magic Cards
    private Image fireShamanImage;
    private Image lavaFlowImage;
    private Image coalImage;
    private Image blacksmithImage;
    private Image fireballImage;

    //Golden Cards
    private Image phoenixImage;
    private Image meteorsImage;
    private Image infernoImage;
    //endregion

    //region JapanesePack PopupSpinners
    //Build Cards
    private Spinner stockadeSpinner;
    private Spinner fortressSpinner;
    private Spinner monasterySpinner;
    private Spinner rampartSpinner;
    private Spinner citadelSpinner;

    //Attack Cards
    private Spinner ashigaruSpinner;
    private Spinner shurikenSpinner;
    private Spinner katanaSpinner;
    private Spinner samuraiSpinner;
    private Spinner dojoSpinner;

    //Magic Cards
    private Spinner shrineSpinner;
    private Spinner quarrySpinner;
    private Spinner templeSpinner;
    private Spinner ricePaddySpinner;
    private Spinner seppukuSpinner;

    //Golden Cards
    private Spinner ninjaSpinner;
    private Spinner dragonSpinner;
    private Spinner shogunSpinner;
    //endregion
    //region JapanesePack PopupImages
    //Build Cards
    private Image stockadeImage;
    private Image fortressImage;
    private Image monasteryImage;
    private Image rampartImage;
    private Image citadelImage;

    //Attack Cards
    private Image ashigaruImage;
    private Image shurikenImage;
    private Image katanaImage;
    private Image samuraiImage;
    private Image dojoImage;

    //Magic Cards
    private Image shrineImage;
    private Image quarryImage;
    private Image templeImage;
    private Image ricePaddyImage;
    private Image seppukuImage;

    //Golden Cards
    private Image ninjaImage;
    private Image dragonImage;
    private Image shogunImage;
    //endregion


    public BuildScreen(final GooglePlayServicesInterface gpgs, CastleWars cw) {
        super();

        Gdx.input.setInputProcessor(this);

        batch = new SpriteBatch();
        cam = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        cam.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);

        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, cam);

        setCam(viewport);

        castleWars = cw;
        this.gpgs = gpgs;

        cardScrollArea = new ScrollArea(Assets.invisible, 10, -30, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT, ScrollArea.VERTICAL, 4, GameWorld.CARD_SPACING, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        //region BasicMenus
        Layout barrierLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout wallLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout greatWallLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout reinforceLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout fortifyLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout castlesLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout architectLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout reserveLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout sabotageLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout strongholdLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout recruiterLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout spearmanLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout ramLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout catapultLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout legionLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout trebuchetLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout assassinLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout burglarLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout thiefLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout raidLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout createStonesLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout createWeaponsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout createGemsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout destroyStonesLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout destroyWeaponsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout destroyGemsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout mageLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout hatTrickLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout lightningLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout blastLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout jerichoLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout trojanHorseLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout blackPlagueLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout merlinLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout duplicateLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        //endregion
        //region Basic Spinners
        barrierSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        wallSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        greatWallSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        reinforceSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fortifySpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        castleSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        architectSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        reserveSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        sabotageSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        strongHoldSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());


        recruiterSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        spearmanSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        ramSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        catapultSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        legionSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        trebuchetSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        assassinSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        burglarSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        thiefSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        raidSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        createStonesSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        createWeaponsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        createGemsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        destroyStonesSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        destroyWeaponsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        destroyGemsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        mageSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        hatTrickSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        lightningSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        blastSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        jerichoSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        trojanHorseSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        blackPlagueSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        merlinSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        duplicateSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        barrierLayout.addPopupWidget(barrierSpinner);
        wallLayout.addPopupWidget(wallSpinner);
        greatWallLayout.addPopupWidget(greatWallSpinner);
        reinforceLayout.addPopupWidget(reinforceSpinner);
        fortifyLayout.addPopupWidget(fortifySpinner);
        castlesLayout.addPopupWidget(castleSpinner);
        architectLayout.addPopupWidget(architectSpinner);
        reserveLayout.addPopupWidget(reserveSpinner);
        sabotageLayout.addPopupWidget(sabotageSpinner);
        strongholdLayout.addPopupWidget(strongHoldSpinner);

        recruiterLayout.addPopupWidget(recruiterSpinner);
        spearmanLayout.addPopupWidget(spearmanSpinner);
        ramLayout.addPopupWidget(ramSpinner);
        catapultLayout.addPopupWidget(catapultSpinner);
        legionLayout.addPopupWidget(legionSpinner);
        trebuchetLayout.addPopupWidget(trebuchetSpinner);
        assassinLayout.addPopupWidget(assassinSpinner);
        burglarLayout.addPopupWidget(burglarSpinner);
        thiefLayout.addPopupWidget(thiefSpinner);
        raidLayout.addPopupWidget(raidSpinner);

        createStonesLayout.addPopupWidget(createStonesSpinner);
        createWeaponsLayout.addPopupWidget(createWeaponsSpinner);
        createGemsLayout.addPopupWidget(createGemsSpinner);
        destroyStonesLayout.addPopupWidget(destroyStonesSpinner);
        destroyWeaponsLayout.addPopupWidget(destroyWeaponsSpinner);
        destroyGemsLayout.addPopupWidget(destroyGemsSpinner);
        mageLayout.addPopupWidget(mageSpinner);
        hatTrickLayout.addPopupWidget(hatTrickSpinner);
        lightningLayout.addPopupWidget(lightningSpinner);
        blastLayout.addPopupWidget(blastSpinner);

        jerichoLayout.addPopupWidget(jerichoSpinner);
        trojanHorseLayout.addPopupWidget(trojanHorseSpinner);
        blackPlagueLayout.addPopupWidget(blackPlagueSpinner);
        merlinLayout.addPopupWidget(merlinSpinner);
        duplicateLayout.addPopupWidget(duplicateSpinner);

        //endregion
        //region Basic PopupImages
        barrierImage = new Image(Assets.buildCardBarrier, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        wallImage = new Image(Assets.buildCardWall, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        greatWallImage = new Image(Assets.buildCardGreatWall, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        reinforceImage = new Image(Assets.buildCardReinforce, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fortifyImage = new Image(Assets.buildCardFortify, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        castleImage = new Image(Assets.buildCardCastle, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        architectImage = new Image(Assets.buildCardArchitect, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        reserveImage = new Image(Assets.buildCardReserve, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        sabotageImage = new Image(Assets.buildCardSabotage, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        strongHoldImage = new Image(Assets.buildCardStronghold, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        recruiterImage = new Image(Assets.attackCardRecruiter, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        spearmanImage = new Image(Assets.attackCardSpearman, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ramImage = new Image(Assets.attackCardRam, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        catapultImage = new Image(Assets.attackCardCatapult, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        legionImage = new Image(Assets.attackCardLegion, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        trebuchetImage = new Image(Assets.attackCardTrebuchet, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        assassinImage = new Image(Assets.attackCardAssassin, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        burglarImage = new Image(Assets.attackCardBurglar, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        thiefImage = new Image(Assets.attackCardThief, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        raidImage = new Image(Assets.attackCardRaid, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        createStonesImage = new Image(Assets.gemCardCreateStones, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        createWeaponsImage = new Image(Assets.gemCardCreateWeapons, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        createGemsImage = new Image(Assets.gemCardCreateGems, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyStonesImage = new Image(Assets.gemCardDestroyStones, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyWeaponsImage = new Image(Assets.gemCardDestroyWeapons, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyGemsImage = new Image(Assets.gemCardDestroyGems, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        mageImage = new Image(Assets.gemCardMage, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        hatTrickImage = new Image(Assets.gemCardHatTrick, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        lightningImage = new Image(Assets.gemCardLightningStrike, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blastImage = new Image(Assets.gemCardBlast, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        jerichoImage = new Image(Assets.goldCardJericho, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        trojanHorseImage = new Image(Assets.goldCardTrojanHorse, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blackPlagueImage = new Image(Assets.goldCardBlackPlague, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        merlinImage = new Image(Assets.goldCardMerlin, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        duplicateImage = new Image(Assets.goldCardDuplicate, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        barrierLayout.addPopupWidget(barrierImage);
        wallLayout.addPopupWidget(wallImage);
        greatWallLayout.addPopupWidget(greatWallImage);
        reinforceLayout.addPopupWidget(reinforceImage);
        fortifyLayout.addPopupWidget(fortifyImage);
        castlesLayout.addPopupWidget(castleImage);
        architectLayout.addPopupWidget(architectImage);
        reserveLayout.addPopupWidget(reserveImage);
        sabotageLayout.addPopupWidget(sabotageImage);
        strongholdLayout.addPopupWidget(strongHoldImage);

        recruiterLayout.addPopupWidget(recruiterImage);
        spearmanLayout.addPopupWidget(spearmanImage);
        ramLayout.addPopupWidget(ramImage);
        catapultLayout.addPopupWidget(catapultImage);
        legionLayout.addPopupWidget(legionImage);
        trebuchetLayout.addPopupWidget(trebuchetImage);
        assassinLayout.addPopupWidget(assassinImage);
        burglarLayout.addPopupWidget(burglarImage);
        thiefLayout.addPopupWidget(thiefImage);
        raidLayout.addPopupWidget(raidImage);

        createStonesLayout.addPopupWidget(createStonesImage);
        createWeaponsLayout.addPopupWidget(createWeaponsImage);
        createGemsLayout.addPopupWidget(createGemsImage);
        destroyStonesLayout.addPopupWidget(destroyStonesImage);
        destroyWeaponsLayout.addPopupWidget(destroyWeaponsImage);
        destroyGemsLayout.addPopupWidget(destroyGemsImage);
        mageLayout.addPopupWidget(mageImage);
        hatTrickLayout.addPopupWidget(hatTrickImage);
        lightningLayout.addPopupWidget(lightningImage);
        blastLayout.addPopupWidget(blastImage);

        jerichoLayout.addPopupWidget(jerichoImage);
        trojanHorseLayout.addPopupWidget(trojanHorseImage);
        blackPlagueLayout.addPopupWidget(blackPlagueImage);
        merlinLayout.addPopupWidget(merlinImage);
        duplicateLayout.addPopupWidget(duplicateImage);
        //endregion

        //region FlamePack Menus
        Layout wallOfFireLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout campfireLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout forgeLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout boilingOilLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout bonfireLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout fireLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout fireArrowsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout flamingAxeLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout flamingShotLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout flameLegionLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout fireShamanLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout lavaFlowLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout coalLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout blacksmithLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout fireballLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout phoenixLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout meteorsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout infernoLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        //endregion
        //region FlamePack Spinners
        wallOfFireSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        campfireSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        forgeSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        boilingOilSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        bonfireSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fireSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fireArrowsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        flamingAxeSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        flamingShotSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        flameLegionSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fireShamanSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        lavaFlowSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        coalSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        blacksmithSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fireballSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        phoenixSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        meteorsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        infernoSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        wallOfFireLayout.addPopupWidget(wallOfFireSpinner);
        campfireLayout.addPopupWidget(campfireSpinner);
        forgeLayout.addPopupWidget(forgeSpinner);
        boilingOilLayout.addPopupWidget(boilingOilSpinner);
        bonfireLayout.addPopupWidget(bonfireSpinner);

        fireLayout.addPopupWidget(fireSpinner);
        fireArrowsLayout.addPopupWidget(fireArrowsSpinner);
        flamingAxeLayout.addPopupWidget(flamingAxeSpinner);
        flamingShotLayout.addPopupWidget(flamingShotSpinner);
        flameLegionLayout.addPopupWidget(flameLegionSpinner);

        fireShamanLayout.addPopupWidget(fireShamanSpinner);
        lavaFlowLayout.addPopupWidget(lavaFlowSpinner);
        coalLayout.addPopupWidget(coalSpinner);
        blacksmithLayout.addPopupWidget(blacksmithSpinner);
        fireballLayout.addPopupWidget(fireballSpinner);

        phoenixLayout.addPopupWidget(phoenixSpinner);
        meteorsLayout.addPopupWidget(meteorsSpinner);
        infernoLayout.addPopupWidget(infernoSpinner);
        //endregion
        //region FlamePack PopupImages
        wallOfFireImage = new Image(Assets.flameBuildCardWallOfFire, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        campfireImage = new Image(Assets.flameBuildCardCampfire, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        forgeImage = new Image(Assets.flameBuildCardForge, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        boilingOilImage = new Image(Assets.flameBuildCardBoilingOil, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        bonfireImage = new Image(Assets.flameBuildCardBonfire, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireImage = new Image(Assets.flameAttackCardFire, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireArrowsImage = new Image(Assets.flameAttackCardFireArrows, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flamingAxeImage = new Image(Assets.flameAttackCardFlamingAxe, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flamingShotImage = new Image(Assets.flameAttackCardFlamingShot, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flameLegionImage = new Image(Assets.flameAttackCardFlameLegion, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        fireShamanImage = new Image(Assets.flameGemCardFireShaman, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        lavaFlowImage = new Image(Assets.flameGemCardLavaFlow, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        coalImage = new Image(Assets.flameGemCardCoal, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blacksmithImage = new Image(Assets.flameGemCardBlacksmith, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireballImage = new Image(Assets.flameGemCardFireball, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        phoenixImage = new Image(Assets.flameGoldCardPhoenix, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        meteorsImage = new Image(Assets.flameGoldCardMeteors, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        infernoImage = new Image(Assets.flameGoldCardInferno, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        wallOfFireLayout.addPopupWidget(wallOfFireImage);
        campfireLayout.addPopupWidget(campfireImage);
        forgeLayout.addPopupWidget(forgeImage);
        boilingOilLayout.addPopupWidget(boilingOilImage);
        bonfireLayout.addPopupWidget(bonfireImage);

        fireLayout.addPopupWidget(fireImage);
        fireArrowsLayout.addPopupWidget(fireArrowsImage);
        flamingAxeLayout.addPopupWidget(flamingAxeImage);
        flamingShotLayout.addPopupWidget(flamingShotImage);
        flameLegionLayout.addPopupWidget(flameLegionImage);

        fireShamanLayout.addPopupWidget(fireShamanImage);
        lavaFlowLayout.addPopupWidget(lavaFlowImage);
        coalLayout.addPopupWidget(coalImage);
        blacksmithLayout.addPopupWidget(blacksmithImage);
        fireballLayout.addPopupWidget(fireballImage);

        phoenixLayout.addPopupWidget(phoenixImage);
        meteorsLayout.addPopupWidget(meteorsImage);
        infernoLayout.addPopupWidget(infernoImage);
        //endregion

        //region JapanesePack Menus
        Layout stockadeLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout fortressLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout monasteryLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout rampartLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout citadelLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout ashigaruLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout shurikenLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout katanaLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout samuraiLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout dojoLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout shrineLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout quarryLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout templeLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout ricePaddyLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout seppukuLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout ninjaLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout dragonLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout shogunLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        //endregion
        //region JapanesePack Spinners
        stockadeSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fortressSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        monasterySpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        rampartSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        citadelSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        ashigaruSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        shurikenSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        katanaSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        samuraiSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        dojoSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        shrineSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        quarrySpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        templeSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        ricePaddySpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        seppukuSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        ninjaSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        dragonSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        shogunSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        stockadeLayout.addPopupWidget(stockadeSpinner);
        fortressLayout.addPopupWidget(fortressSpinner);
        monasteryLayout.addPopupWidget(monasterySpinner);
        rampartLayout.addPopupWidget(rampartSpinner);
        citadelLayout.addPopupWidget(citadelSpinner);

        ashigaruLayout.addPopupWidget(ashigaruSpinner);
        shurikenLayout.addPopupWidget(shurikenSpinner);
        katanaLayout.addPopupWidget(katanaSpinner);
        samuraiLayout.addPopupWidget(samuraiSpinner);
        dojoLayout.addPopupWidget(dojoSpinner);

        shrineLayout.addPopupWidget(shrineSpinner);
        quarryLayout.addPopupWidget(quarrySpinner);
        templeLayout.addPopupWidget(templeSpinner);
        ricePaddyLayout.addPopupWidget(ricePaddySpinner);
        seppukuLayout.addPopupWidget(seppukuSpinner);

        ninjaLayout.addPopupWidget(ninjaSpinner);
        dragonLayout.addPopupWidget(dragonSpinner);
        shogunLayout.addPopupWidget(shogunSpinner);
        //endregion
        //region JapanesePack PopupImages
        stockadeImage = new Image(Assets.japaneseBuildCardStockade, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fortressImage = new Image(Assets.japaneseBuildCardFortress, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        monasteryImage = new Image(Assets.japaneseBuildCardMonastery, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        rampartImage = new Image(Assets.japaneseBuildCardRampart, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        citadelImage = new Image(Assets.japaneseBuildCardCitadel, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ashigaruImage = new Image(Assets.japaneseAttackCardAshigaru, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        shurikenImage = new Image(Assets.japaneseAttackCardShuriken, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        katanaImage = new Image(Assets.japaneseAttackCardKatana, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        samuraiImage = new Image(Assets.japaneseAttackCardSamurai, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        dojoImage = new Image(Assets.japaneseAttackCardDojo, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        shrineImage = new Image(Assets.japaneseGemCardShrine, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        quarryImage = new Image(Assets.japaneseGemCardQuarry, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        templeImage = new Image(Assets.japaneseGemCardTemple, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ricePaddyImage = new Image(Assets.japaneseGemCardRicePaddy, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        seppukuImage = new Image(Assets.japaneseGemCardSeppuku, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ninjaImage = new Image(Assets.japaneseGoldCardNinja, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        dragonImage = new Image(Assets.japaneseGoldCardDragon, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        shogunImage = new Image(Assets.japaneseGoldCardShogun, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        stockadeLayout.addPopupWidget(stockadeImage);
        fortressLayout.addPopupWidget(fortressImage);
        monasteryLayout.addPopupWidget(monasteryImage);
        rampartLayout.addPopupWidget(rampartImage);
        citadelLayout.addPopupWidget(citadelImage);

        ashigaruLayout.addPopupWidget(ashigaruImage);
        shurikenLayout.addPopupWidget(shurikenImage);
        katanaLayout.addPopupWidget(katanaImage);
        samuraiLayout.addPopupWidget(samuraiImage);
        dojoLayout.addPopupWidget(dojoImage);

        shrineLayout.addPopupWidget(shrineImage);
        quarryLayout.addPopupWidget(quarryImage);
        templeLayout.addPopupWidget(templeImage);
        ricePaddyLayout.addPopupWidget(ricePaddyImage);
        seppukuLayout.addPopupWidget(seppukuImage);

        ninjaLayout.addPopupWidget(ninjaImage);
        dragonLayout.addPopupWidget(dragonImage);
        shogunLayout.addPopupWidget(shogunImage);
        //endregion

        //region Add Basic Menus To Card Scroll Area
        cardScrollArea.addPopupWidget(barrierLayout);
        cardScrollArea.addPopupWidget(wallLayout);
        cardScrollArea.addPopupWidget(greatWallLayout);
        cardScrollArea.addPopupWidget(reinforceLayout);
        cardScrollArea.addPopupWidget(fortifyLayout);
        cardScrollArea.addPopupWidget(castlesLayout);
        cardScrollArea.addPopupWidget(architectLayout);
        cardScrollArea.addPopupWidget(reserveLayout);
        cardScrollArea.addPopupWidget(sabotageLayout);
        cardScrollArea.addPopupWidget(strongholdLayout);
        cardScrollArea.addPopupWidget(recruiterLayout);
        cardScrollArea.addPopupWidget(spearmanLayout);
        cardScrollArea.addPopupWidget(ramLayout);
        cardScrollArea.addPopupWidget(catapultLayout);
        cardScrollArea.addPopupWidget(legionLayout);
        cardScrollArea.addPopupWidget(trebuchetLayout);
        cardScrollArea.addPopupWidget(assassinLayout);
        cardScrollArea.addPopupWidget(burglarLayout);
        cardScrollArea.addPopupWidget(thiefLayout);
        cardScrollArea.addPopupWidget(raidLayout);
        cardScrollArea.addPopupWidget(createStonesLayout);
        cardScrollArea.addPopupWidget(createWeaponsLayout);
        cardScrollArea.addPopupWidget(createGemsLayout);
        cardScrollArea.addPopupWidget(destroyStonesLayout);
        cardScrollArea.addPopupWidget(destroyWeaponsLayout);
        cardScrollArea.addPopupWidget(destroyGemsLayout);
        cardScrollArea.addPopupWidget(mageLayout);
        cardScrollArea.addPopupWidget(hatTrickLayout);
        cardScrollArea.addPopupWidget(lightningLayout);
        cardScrollArea.addPopupWidget(blastLayout);
        cardScrollArea.addPopupWidget(jerichoLayout);
        cardScrollArea.addPopupWidget(trojanHorseLayout);
        cardScrollArea.addPopupWidget(blackPlagueLayout);
        cardScrollArea.addPopupWidget(merlinLayout);
        cardScrollArea.addPopupWidget(duplicateLayout);
        //endregion
        //region Add Japanese Menus To Card Scoll Area
        if (Settings.BOUGHT_JAPANESE_PACK) {
            cardScrollArea.addPopupWidget(stockadeLayout);
            cardScrollArea.addPopupWidget(fortressLayout);
            cardScrollArea.addPopupWidget(monasteryLayout);
            cardScrollArea.addPopupWidget(rampartLayout);
            cardScrollArea.addPopupWidget(citadelLayout);

            cardScrollArea.addPopupWidget(ashigaruLayout);
            cardScrollArea.addPopupWidget(shurikenLayout);
            cardScrollArea.addPopupWidget(katanaLayout);
            cardScrollArea.addPopupWidget(samuraiLayout);
            cardScrollArea.addPopupWidget(dojoLayout);

            cardScrollArea.addPopupWidget(shrineLayout);
            cardScrollArea.addPopupWidget(quarryLayout);
            cardScrollArea.addPopupWidget(templeLayout);
            cardScrollArea.addPopupWidget(ricePaddyLayout);
            cardScrollArea.addPopupWidget(seppukuLayout);

            cardScrollArea.addPopupWidget(ninjaLayout);
            cardScrollArea.addPopupWidget(dragonLayout);
            cardScrollArea.addPopupWidget(shogunLayout);
        }
        //endregion
        //region Add Flame Menus To Card Scroll Area
        if (Settings.BOUGHT_FlAME_PACK) {
            cardScrollArea.addPopupWidget(wallOfFireLayout);
            cardScrollArea.addPopupWidget(campfireLayout);
            cardScrollArea.addPopupWidget(forgeLayout);
            cardScrollArea.addPopupWidget(boilingOilLayout);
            cardScrollArea.addPopupWidget(bonfireLayout);

            cardScrollArea.addPopupWidget(fireLayout);
            cardScrollArea.addPopupWidget(fireArrowsLayout);
            cardScrollArea.addPopupWidget(flamingAxeLayout);
            cardScrollArea.addPopupWidget(flamingShotLayout);
            cardScrollArea.addPopupWidget(flameLegionLayout);

            cardScrollArea.addPopupWidget(fireShamanLayout);
            cardScrollArea.addPopupWidget(lavaFlowLayout);
            cardScrollArea.addPopupWidget(coalLayout);
            cardScrollArea.addPopupWidget(blacksmithLayout);
            cardScrollArea.addPopupWidget(fireballLayout);

            cardScrollArea.addPopupWidget(phoenixLayout);
            cardScrollArea.addPopupWidget(meteorsLayout);
            cardScrollArea.addPopupWidget(infernoLayout);
        }
        //endregion

        cardScrollArea.moveIn();
        cardScrollArea.enable();

        addWidget(cardScrollArea);

        ButtonMaterial cancelButton = new ButtonMaterial(Assets.cancelButtonRound, SCREEN_WIDTH - 22, 2, Settings.BUTTON_HEIGHT, 20, 20);
        cancelButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                moveToMenuScreen();
                //DISPOSE STUFF HERE?
            }
        });
        addWidget(cancelButton);

        ButtonMaterial saveButton = new ButtonMaterial(Assets.okButtonRound, 2, 2, Settings.BUTTON_HEIGHT, 20, 20);
        saveButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                gpgs.unlockAchievement(CastleWars.DO_IT_YOURSELF); //First time Building Deck
                //KODY SAVE LOGIC HERE
                moveToMenuScreen();
                //DISPOSE STUFF HERE?
            }
        });
        addWidget(saveButton);

        for (Widget widget : cardScrollArea.getChildren(true)) {
            if (widget instanceof Spinner) {
                Spinner spinner = (Spinner) widget;
                spinner.setMin(0);
                spinner.setMax(9);
            }
        }
    }

    private void rebuildUI() {
        cardScrollArea.clearChildren();

        //region BasicMenus
        Layout barrierLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout wallLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout greatWallLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout reinforceLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout fortifyLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout castlesLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout architectLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout reserveLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout sabotageLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout strongholdLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout recruiterLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout spearmanLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout ramLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout catapultLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout legionLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout trebuchetLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout assassinLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout burglarLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout thiefLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout raidLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout createStonesLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout createWeaponsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout createGemsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout destroyStonesLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout destroyWeaponsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout destroyGemsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout mageLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout hatTrickLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout lightningLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout blastLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout jerichoLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout trojanHorseLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout blackPlagueLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout merlinLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout duplicateLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        //endregion
        //region Basic Spinners
        barrierSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        wallSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        greatWallSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        reinforceSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fortifySpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        castleSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        architectSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        reserveSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        sabotageSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        strongHoldSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());


        recruiterSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        spearmanSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        ramSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        catapultSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        legionSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        trebuchetSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        assassinSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        burglarSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        thiefSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        raidSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        createStonesSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        createWeaponsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        createGemsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        destroyStonesSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        destroyWeaponsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        destroyGemsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        mageSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        hatTrickSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        lightningSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        blastSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        jerichoSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        trojanHorseSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        blackPlagueSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        merlinSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        duplicateSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        barrierLayout.addPopupWidget(barrierSpinner);
        wallLayout.addPopupWidget(wallSpinner);
        greatWallLayout.addPopupWidget(greatWallSpinner);
        reinforceLayout.addPopupWidget(reinforceSpinner);
        fortifyLayout.addPopupWidget(fortifySpinner);
        castlesLayout.addPopupWidget(castleSpinner);
        architectLayout.addPopupWidget(architectSpinner);
        reserveLayout.addPopupWidget(reserveSpinner);
        sabotageLayout.addPopupWidget(sabotageSpinner);
        strongholdLayout.addPopupWidget(strongHoldSpinner);

        recruiterLayout.addPopupWidget(recruiterSpinner);
        spearmanLayout.addPopupWidget(spearmanSpinner);
        ramLayout.addPopupWidget(ramSpinner);
        catapultLayout.addPopupWidget(catapultSpinner);
        legionLayout.addPopupWidget(legionSpinner);
        trebuchetLayout.addPopupWidget(trebuchetSpinner);
        assassinLayout.addPopupWidget(assassinSpinner);
        burglarLayout.addPopupWidget(burglarSpinner);
        thiefLayout.addPopupWidget(thiefSpinner);
        raidLayout.addPopupWidget(raidSpinner);

        createStonesLayout.addPopupWidget(createStonesSpinner);
        createWeaponsLayout.addPopupWidget(createWeaponsSpinner);
        createGemsLayout.addPopupWidget(createGemsSpinner);
        destroyStonesLayout.addPopupWidget(destroyStonesSpinner);
        destroyWeaponsLayout.addPopupWidget(destroyWeaponsSpinner);
        destroyGemsLayout.addPopupWidget(destroyGemsSpinner);
        mageLayout.addPopupWidget(mageSpinner);
        hatTrickLayout.addPopupWidget(hatTrickSpinner);
        lightningLayout.addPopupWidget(lightningSpinner);
        blastLayout.addPopupWidget(blastSpinner);

        jerichoLayout.addPopupWidget(jerichoSpinner);
        trojanHorseLayout.addPopupWidget(trojanHorseSpinner);
        blackPlagueLayout.addPopupWidget(blackPlagueSpinner);
        merlinLayout.addPopupWidget(merlinSpinner);
        duplicateLayout.addPopupWidget(duplicateSpinner);

        //endregion
        //region Basic PopupImages
        barrierImage = new Image(Assets.buildCardBarrier, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        wallImage = new Image(Assets.buildCardWall, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        greatWallImage = new Image(Assets.buildCardGreatWall, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        reinforceImage = new Image(Assets.buildCardReinforce, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fortifyImage = new Image(Assets.buildCardFortify, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        castleImage = new Image(Assets.buildCardCastle, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        architectImage = new Image(Assets.buildCardArchitect, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        reserveImage = new Image(Assets.buildCardReserve, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        sabotageImage = new Image(Assets.buildCardSabotage, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        strongHoldImage = new Image(Assets.buildCardStronghold, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        recruiterImage = new Image(Assets.attackCardRecruiter, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        spearmanImage = new Image(Assets.attackCardSpearman, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ramImage = new Image(Assets.attackCardRam, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        catapultImage = new Image(Assets.attackCardCatapult, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        legionImage = new Image(Assets.attackCardLegion, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        trebuchetImage = new Image(Assets.attackCardTrebuchet, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        assassinImage = new Image(Assets.attackCardAssassin, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        burglarImage = new Image(Assets.attackCardBurglar, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        thiefImage = new Image(Assets.attackCardThief, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        raidImage = new Image(Assets.attackCardRaid, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        createStonesImage = new Image(Assets.gemCardCreateStones, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        createWeaponsImage = new Image(Assets.gemCardCreateWeapons, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        createGemsImage = new Image(Assets.gemCardCreateGems, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyStonesImage = new Image(Assets.gemCardDestroyStones, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyWeaponsImage = new Image(Assets.gemCardDestroyWeapons, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyGemsImage = new Image(Assets.gemCardDestroyGems, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        mageImage = new Image(Assets.gemCardMage, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        hatTrickImage = new Image(Assets.gemCardHatTrick, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        lightningImage = new Image(Assets.gemCardLightningStrike, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blastImage = new Image(Assets.gemCardBlast, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        jerichoImage = new Image(Assets.goldCardJericho, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        trojanHorseImage = new Image(Assets.goldCardTrojanHorse, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blackPlagueImage = new Image(Assets.goldCardBlackPlague, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        merlinImage = new Image(Assets.goldCardMerlin, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        duplicateImage = new Image(Assets.goldCardDuplicate, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        barrierLayout.addPopupWidget(barrierImage);
        wallLayout.addPopupWidget(wallImage);
        greatWallLayout.addPopupWidget(greatWallImage);
        reinforceLayout.addPopupWidget(reinforceImage);
        fortifyLayout.addPopupWidget(fortifyImage);
        castlesLayout.addPopupWidget(castleImage);
        architectLayout.addPopupWidget(architectImage);
        reserveLayout.addPopupWidget(reserveImage);
        sabotageLayout.addPopupWidget(sabotageImage);
        strongholdLayout.addPopupWidget(strongHoldImage);

        recruiterLayout.addPopupWidget(recruiterImage);
        spearmanLayout.addPopupWidget(spearmanImage);
        ramLayout.addPopupWidget(ramImage);
        catapultLayout.addPopupWidget(catapultImage);
        legionLayout.addPopupWidget(legionImage);
        trebuchetLayout.addPopupWidget(trebuchetImage);
        assassinLayout.addPopupWidget(assassinImage);
        burglarLayout.addPopupWidget(burglarImage);
        thiefLayout.addPopupWidget(thiefImage);
        raidLayout.addPopupWidget(raidImage);

        createStonesLayout.addPopupWidget(createStonesImage);
        createWeaponsLayout.addPopupWidget(createWeaponsImage);
        createGemsLayout.addPopupWidget(createGemsImage);
        destroyStonesLayout.addPopupWidget(destroyStonesImage);
        destroyWeaponsLayout.addPopupWidget(destroyWeaponsImage);
        destroyGemsLayout.addPopupWidget(destroyGemsImage);
        mageLayout.addPopupWidget(mageImage);
        hatTrickLayout.addPopupWidget(hatTrickImage);
        lightningLayout.addPopupWidget(lightningImage);
        blastLayout.addPopupWidget(blastImage);

        jerichoLayout.addPopupWidget(jerichoImage);
        trojanHorseLayout.addPopupWidget(trojanHorseImage);
        blackPlagueLayout.addPopupWidget(blackPlagueImage);
        merlinLayout.addPopupWidget(merlinImage);
        duplicateLayout.addPopupWidget(duplicateImage);
        //endregion

        //region FlamePack Menus
        Layout wallOfFireLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout campfireLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout forgeLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout boilingOilLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout bonfireLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout fireLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout fireArrowsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout flamingAxeLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout flamingShotLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout flameLegionLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout fireShamanLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout lavaFlowLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout coalLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout blacksmithLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout fireballLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout phoenixLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout meteorsLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout infernoLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        //endregion
        //region FlamePack Spinners
        wallOfFireSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        campfireSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        forgeSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        boilingOilSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        bonfireSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fireSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fireArrowsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        flamingAxeSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        flamingShotSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        flameLegionSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fireShamanSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        lavaFlowSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        coalSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        blacksmithSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fireballSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        phoenixSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        meteorsSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        infernoSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        wallOfFireLayout.addPopupWidget(wallOfFireSpinner);
        campfireLayout.addPopupWidget(campfireSpinner);
        forgeLayout.addPopupWidget(forgeSpinner);
        boilingOilLayout.addPopupWidget(boilingOilSpinner);
        bonfireLayout.addPopupWidget(bonfireSpinner);

        fireLayout.addPopupWidget(fireSpinner);
        fireArrowsLayout.addPopupWidget(fireArrowsSpinner);
        flamingAxeLayout.addPopupWidget(flamingAxeSpinner);
        flamingShotLayout.addPopupWidget(flamingShotSpinner);
        flameLegionLayout.addPopupWidget(flameLegionSpinner);

        fireShamanLayout.addPopupWidget(fireShamanSpinner);
        lavaFlowLayout.addPopupWidget(lavaFlowSpinner);
        coalLayout.addPopupWidget(coalSpinner);
        blacksmithLayout.addPopupWidget(blacksmithSpinner);
        fireballLayout.addPopupWidget(fireballSpinner);

        phoenixLayout.addPopupWidget(phoenixSpinner);
        meteorsLayout.addPopupWidget(meteorsSpinner);
        infernoLayout.addPopupWidget(infernoSpinner);
        //endregion
        //region FlamePack PopupImages
        wallOfFireImage = new Image(Assets.flameBuildCardWallOfFire, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        campfireImage = new Image(Assets.flameBuildCardCampfire, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        forgeImage = new Image(Assets.flameBuildCardForge, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        boilingOilImage = new Image(Assets.flameBuildCardBoilingOil, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        bonfireImage = new Image(Assets.flameBuildCardBonfire, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireImage = new Image(Assets.flameAttackCardFire, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireArrowsImage = new Image(Assets.flameAttackCardFireArrows, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flamingAxeImage = new Image(Assets.flameAttackCardFlamingAxe, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flamingShotImage = new Image(Assets.flameAttackCardFlamingShot, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flameLegionImage = new Image(Assets.flameAttackCardFlameLegion, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        fireShamanImage = new Image(Assets.flameGemCardFireShaman, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        lavaFlowImage = new Image(Assets.flameGemCardLavaFlow, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        coalImage = new Image(Assets.flameGemCardCoal, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blacksmithImage = new Image(Assets.flameGemCardBlacksmith, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireballImage = new Image(Assets.flameGemCardFireball, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        phoenixImage = new Image(Assets.flameGoldCardPhoenix, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        meteorsImage = new Image(Assets.flameGoldCardMeteors, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        infernoImage = new Image(Assets.flameGoldCardInferno, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        wallOfFireLayout.addPopupWidget(wallOfFireImage);
        campfireLayout.addPopupWidget(campfireImage);
        forgeLayout.addPopupWidget(forgeImage);
        boilingOilLayout.addPopupWidget(boilingOilImage);
        bonfireLayout.addPopupWidget(bonfireImage);

        fireLayout.addPopupWidget(fireImage);
        fireArrowsLayout.addPopupWidget(fireArrowsImage);
        flamingAxeLayout.addPopupWidget(flamingAxeImage);
        flamingShotLayout.addPopupWidget(flamingShotImage);
        flameLegionLayout.addPopupWidget(flameLegionImage);

        fireShamanLayout.addPopupWidget(fireShamanImage);
        lavaFlowLayout.addPopupWidget(lavaFlowImage);
        coalLayout.addPopupWidget(coalImage);
        blacksmithLayout.addPopupWidget(blacksmithImage);
        fireballLayout.addPopupWidget(fireballImage);

        phoenixLayout.addPopupWidget(phoenixImage);
        meteorsLayout.addPopupWidget(meteorsImage);
        infernoLayout.addPopupWidget(infernoImage);
        //endregion

        //region JapanesePack Menus
        Layout stockadeLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout fortressLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout monasteryLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout rampartLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout citadelLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout ashigaruLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout shurikenLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout katanaLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout samuraiLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout dojoLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout shrineLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout quarryLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout templeLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout ricePaddyLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout seppukuLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        Layout ninjaLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout dragonLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());
        Layout shogunLayout = new Layout(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT, getCam());

        //endregion
        //region JapanesePack Spinners
        stockadeSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        fortressSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        monasterySpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        rampartSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        citadelSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        ashigaruSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        shurikenSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        katanaSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        samuraiSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        dojoSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        shrineSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        quarrySpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        templeSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        ricePaddySpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        seppukuSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        ninjaSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        dragonSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());
        shogunSpinner = new Spinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT, getCam());

        stockadeLayout.addPopupWidget(stockadeSpinner);
        fortressLayout.addPopupWidget(fortressSpinner);
        monasteryLayout.addPopupWidget(monasterySpinner);
        rampartLayout.addPopupWidget(rampartSpinner);
        citadelLayout.addPopupWidget(citadelSpinner);

        ashigaruLayout.addPopupWidget(ashigaruSpinner);
        shurikenLayout.addPopupWidget(shurikenSpinner);
        katanaLayout.addPopupWidget(katanaSpinner);
        samuraiLayout.addPopupWidget(samuraiSpinner);
        dojoLayout.addPopupWidget(dojoSpinner);

        shrineLayout.addPopupWidget(shrineSpinner);
        quarryLayout.addPopupWidget(quarrySpinner);
        templeLayout.addPopupWidget(templeSpinner);
        ricePaddyLayout.addPopupWidget(ricePaddySpinner);
        seppukuLayout.addPopupWidget(seppukuSpinner);

        ninjaLayout.addPopupWidget(ninjaSpinner);
        dragonLayout.addPopupWidget(dragonSpinner);
        shogunLayout.addPopupWidget(shogunSpinner);
        //endregion
        //region JapanesePack PopupImages
        stockadeImage = new Image(Assets.japaneseBuildCardStockade, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fortressImage = new Image(Assets.japaneseBuildCardFortress, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        monasteryImage = new Image(Assets.japaneseBuildCardMonastery, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        rampartImage = new Image(Assets.japaneseBuildCardRampart, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        citadelImage = new Image(Assets.japaneseBuildCardCitadel, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ashigaruImage = new Image(Assets.japaneseAttackCardAshigaru, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        shurikenImage = new Image(Assets.japaneseAttackCardShuriken, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        katanaImage = new Image(Assets.japaneseAttackCardKatana, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        samuraiImage = new Image(Assets.japaneseAttackCardSamurai, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        dojoImage = new Image(Assets.japaneseAttackCardDojo, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        shrineImage = new Image(Assets.japaneseGemCardShrine, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        quarryImage = new Image(Assets.japaneseGemCardQuarry, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        templeImage = new Image(Assets.japaneseGemCardTemple, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ricePaddyImage = new Image(Assets.japaneseGemCardRicePaddy, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        seppukuImage = new Image(Assets.japaneseGemCardSeppuku, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ninjaImage = new Image(Assets.japaneseGoldCardNinja, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        dragonImage = new Image(Assets.japaneseGoldCardDragon, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        shogunImage = new Image(Assets.japaneseGoldCardShogun, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        stockadeLayout.addPopupWidget(stockadeImage);
        fortressLayout.addPopupWidget(fortressImage);
        monasteryLayout.addPopupWidget(monasteryImage);
        rampartLayout.addPopupWidget(rampartImage);
        citadelLayout.addPopupWidget(citadelImage);

        ashigaruLayout.addPopupWidget(ashigaruImage);
        shurikenLayout.addPopupWidget(shurikenImage);
        katanaLayout.addPopupWidget(katanaImage);
        samuraiLayout.addPopupWidget(samuraiImage);
        dojoLayout.addPopupWidget(dojoImage);

        shrineLayout.addPopupWidget(shrineImage);
        quarryLayout.addPopupWidget(quarryImage);
        templeLayout.addPopupWidget(templeImage);
        ricePaddyLayout.addPopupWidget(ricePaddyImage);
        seppukuLayout.addPopupWidget(seppukuImage);

        ninjaLayout.addPopupWidget(ninjaImage);
        dragonLayout.addPopupWidget(dragonImage);
        shogunLayout.addPopupWidget(shogunImage);
        //endregion

        //region Add Basic Menus To Card Scroll Area
        cardScrollArea.addPopupWidget(barrierLayout);
        cardScrollArea.addPopupWidget(wallLayout);
        cardScrollArea.addPopupWidget(greatWallLayout);
        cardScrollArea.addPopupWidget(reinforceLayout);
        cardScrollArea.addPopupWidget(fortifyLayout);
        cardScrollArea.addPopupWidget(castlesLayout);
        cardScrollArea.addPopupWidget(architectLayout);
        cardScrollArea.addPopupWidget(reserveLayout);
        cardScrollArea.addPopupWidget(sabotageLayout);
        cardScrollArea.addPopupWidget(strongholdLayout);
        cardScrollArea.addPopupWidget(recruiterLayout);
        cardScrollArea.addPopupWidget(spearmanLayout);
        cardScrollArea.addPopupWidget(ramLayout);
        cardScrollArea.addPopupWidget(catapultLayout);
        cardScrollArea.addPopupWidget(legionLayout);
        cardScrollArea.addPopupWidget(trebuchetLayout);
        cardScrollArea.addPopupWidget(assassinLayout);
        cardScrollArea.addPopupWidget(burglarLayout);
        cardScrollArea.addPopupWidget(thiefLayout);
        cardScrollArea.addPopupWidget(raidLayout);
        cardScrollArea.addPopupWidget(createStonesLayout);
        cardScrollArea.addPopupWidget(createWeaponsLayout);
        cardScrollArea.addPopupWidget(createGemsLayout);
        cardScrollArea.addPopupWidget(destroyStonesLayout);
        cardScrollArea.addPopupWidget(destroyWeaponsLayout);
        cardScrollArea.addPopupWidget(destroyGemsLayout);
        cardScrollArea.addPopupWidget(mageLayout);
        cardScrollArea.addPopupWidget(hatTrickLayout);
        cardScrollArea.addPopupWidget(lightningLayout);
        cardScrollArea.addPopupWidget(blastLayout);
        cardScrollArea.addPopupWidget(jerichoLayout);
        cardScrollArea.addPopupWidget(trojanHorseLayout);
        cardScrollArea.addPopupWidget(blackPlagueLayout);
        cardScrollArea.addPopupWidget(merlinLayout);
        cardScrollArea.addPopupWidget(duplicateLayout);
        //endregion
        //region Add Japanese Menus To Card Scoll Area
        if (Settings.BOUGHT_JAPANESE_PACK) {
            cardScrollArea.addPopupWidget(stockadeLayout);
            cardScrollArea.addPopupWidget(fortressLayout);
            cardScrollArea.addPopupWidget(monasteryLayout);
            cardScrollArea.addPopupWidget(rampartLayout);
            cardScrollArea.addPopupWidget(citadelLayout);

            cardScrollArea.addPopupWidget(ashigaruLayout);
            cardScrollArea.addPopupWidget(shurikenLayout);
            cardScrollArea.addPopupWidget(katanaLayout);
            cardScrollArea.addPopupWidget(samuraiLayout);
            cardScrollArea.addPopupWidget(dojoLayout);

            cardScrollArea.addPopupWidget(shrineLayout);
            cardScrollArea.addPopupWidget(quarryLayout);
            cardScrollArea.addPopupWidget(templeLayout);
            cardScrollArea.addPopupWidget(ricePaddyLayout);
            cardScrollArea.addPopupWidget(seppukuLayout);

            cardScrollArea.addPopupWidget(ninjaLayout);
            cardScrollArea.addPopupWidget(dragonLayout);
            cardScrollArea.addPopupWidget(shogunLayout);
        }
        //endregion
        //region Add Flame Menus To Card Scroll Area
        if (Settings.BOUGHT_FlAME_PACK) {
            cardScrollArea.addPopupWidget(wallOfFireLayout);
            cardScrollArea.addPopupWidget(campfireLayout);
            cardScrollArea.addPopupWidget(forgeLayout);
            cardScrollArea.addPopupWidget(boilingOilLayout);
            cardScrollArea.addPopupWidget(bonfireLayout);

            cardScrollArea.addPopupWidget(fireLayout);
            cardScrollArea.addPopupWidget(fireArrowsLayout);
            cardScrollArea.addPopupWidget(flamingAxeLayout);
            cardScrollArea.addPopupWidget(flamingShotLayout);
            cardScrollArea.addPopupWidget(flameLegionLayout);

            cardScrollArea.addPopupWidget(fireShamanLayout);
            cardScrollArea.addPopupWidget(lavaFlowLayout);
            cardScrollArea.addPopupWidget(coalLayout);
            cardScrollArea.addPopupWidget(blacksmithLayout);
            cardScrollArea.addPopupWidget(fireballLayout);

            cardScrollArea.addPopupWidget(phoenixLayout);
            cardScrollArea.addPopupWidget(meteorsLayout);
            cardScrollArea.addPopupWidget(infernoLayout);
        }
        //endregion

        cardScrollArea.moveIn();
        cardScrollArea.enable();
    }

    private void moveToMenuScreen() {
        castleWars.setScreen(new MenuScreen(gpgs, castleWars));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    public void update(float delta) {
        super.update(delta);
    }

    public void draw() {
        Gdx.gl.glClearColor(0, 0, .196f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        cam.position.set(BuildScreen.SCREEN_WIDTH / 2, BuildScreen.SCREEN_HEIGHT / 2, 0);
        cam.update();
        super.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}

