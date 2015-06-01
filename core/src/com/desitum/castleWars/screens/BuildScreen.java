package com.desitum.castleWars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.menu.PopupImage;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupScrollArea;
import com.desitum.castleWars.libraries.menu.PopupSpinner;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.world.GameWorld;

import java.awt.Color;

/**
 * Created by Zmyth97 on 6/1/2015.
 */
public class BuildScreen implements Screen {

    public static final float SCREEN_WIDTH = 150;
    public static final float SCREEN_HEIGHT = 100;
    public static final float SPINNER_HEIGHT = 6;

    private CastleWars castleWars;

    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;

    private PopupScrollArea cardScrollArea;

    //region Basic PopupSpinners
    //Build Cards
    private PopupSpinner barrierSpinner;
    private PopupSpinner wallSpinner;
    private PopupSpinner greatWallSpinner;
    private PopupSpinner reinforceSpinner;
    private PopupSpinner fortifySpinner;
    private PopupSpinner castleSpinner;
    private PopupSpinner architectSpinner;
    private PopupSpinner reserveSpinner;
    private PopupSpinner sabotageSpinner;
    private PopupSpinner strongHoldSpinner;

    //Attack Cards
    private PopupSpinner recruiterSpinner;
    private PopupSpinner spearmanSpinner;
    private PopupSpinner ramSpinner;
    private PopupSpinner catapultSpinner;
    private PopupSpinner legionSpinner;
    private PopupSpinner trebuchetSpinner;
    private PopupSpinner assassinSpinner;
    private PopupSpinner burglarSpinner;
    private PopupSpinner thiefSpinner;
    private PopupSpinner raidSpinner;

    //Magic Cards
    private PopupSpinner createStonesSpinner;
    private PopupSpinner createWeaponsSpinner;
    private PopupSpinner createGemsSpinner;
    private PopupSpinner destroyStonesSpinner;
    private PopupSpinner destroyWeaponsSpinner;
    private PopupSpinner destroyGemsSpinner;
    private PopupSpinner mageSpinner;
    private PopupSpinner hatTrickSpinner;
    private PopupSpinner lightningSpinner;
    private PopupSpinner blastSpinner;

    //Golden Cards
    private PopupSpinner jerichoSpinner;
    private PopupSpinner trojanHorseSpinner;
    private PopupSpinner blackPlagueSpinner;
    private PopupSpinner merlinSpinner;
    private PopupSpinner duplicateSpinner;
    //endregion
    //region Basic PopupImages
    //Build Cards
    private PopupImage barrierImage;
    private PopupImage wallImage;
    private PopupImage greatWallImage;
    private PopupImage reinforceImage;
    private PopupImage fortifyImage;
    private PopupImage castleImage;
    private PopupImage architectImage;
    private PopupImage reserveImage;
    private PopupImage sabotageImage;
    private PopupImage strongHoldImage;

    //Attack Cards
    private PopupImage recruiterImage;
    private PopupImage spearmanImage;
    private PopupImage ramImage;
    private PopupImage catapultImage;
    private PopupImage legionImage;
    private PopupImage trebuchetImage;
    private PopupImage assassinImage;
    private PopupImage burglarImage;
    private PopupImage thiefImage;
    private PopupImage raidImage;

    //Magic Cards
    private PopupImage createStonesImage;
    private PopupImage createWeaponsImage;
    private PopupImage createGemsImage;
    private PopupImage destroyStonesImage;
    private PopupImage destroyWeaponsImage;
    private PopupImage destroyGemsImage;
    private PopupImage mageImage;
    private PopupImage hatTrickImage;
    private PopupImage lightningImage;
    private PopupImage blastImage;

    //Golden Cards
    private PopupImage jerichoImage;
    private PopupImage trojanHorseImage;
    private PopupImage blackPlagueImage;
    private PopupImage merlinImage;
    private PopupImage duplicateImage;
    //endregion

    //region FlamePack PopupSpinners
    //Build Cards
    private PopupSpinner wallOfFireSpinner;
    private PopupSpinner campfireSpinner;
    private PopupSpinner forgeSpinner;
    private PopupSpinner boilingOilSpinner;
    private PopupSpinner bonfireSpinner;

    //Attack Cards
    private PopupSpinner fireSpinner;
    private PopupSpinner fireArrowsSpinner;
    private PopupSpinner flamingAxeSpinner;
    private PopupSpinner flamingShotSpinner;
    private PopupSpinner flameLegionSpinner;

    //Magic Cards
    private PopupSpinner fireShamanSpinner;
    private PopupSpinner lavaFlowSpinner;
    private PopupSpinner coalSpinner;
    private PopupSpinner blacksmithSpinner;
    private PopupSpinner fireballSpinner;

    //Golden Cards
    private PopupSpinner phoenixSpinner;
    private PopupSpinner meteorsSpinner;
    private PopupSpinner infernoSpinner;
    //endregion
    //region FlamePack PopupImages
    //Build Cards
    private PopupImage wallOfFireImage;
    private PopupImage campfireImage;
    private PopupImage forgeImage;
    private PopupImage boilingOilImage;
    private PopupImage bonfireImage;

    //Attack Cards
    private PopupImage fireImage;
    private PopupImage fireArrowsImage;
    private PopupImage flamingAxeImage;
    private PopupImage flamingShotImage;
    private PopupImage flameLegionImage;

    //Magic Cards
    private PopupImage fireShamanImage;
    private PopupImage lavaFlowImage;
    private PopupImage coalImage;
    private PopupImage blacksmithImage;
    private PopupImage fireballImage;

    //Golden Cards
    private PopupImage phoenixImage;
    private PopupImage meteorsImage;
    private PopupImage infernoImage;
    //endregion

    //region JapanesePack PopupSpinners
    //Build Cards
    private PopupSpinner stockadeSpinner;
    private PopupSpinner fortressSpinner;
    private PopupSpinner monasterySpinner;
    private PopupSpinner rampartSpinner;
    private PopupSpinner citadelSpinner;

    //Attack Cards
    private PopupSpinner ashigaruSpinner;
    private PopupSpinner shurikenSpinner;
    private PopupSpinner katanaSpinner;
    private PopupSpinner samuraiSpinner;
    private PopupSpinner dojoSpinner;

    //Magic Cards
    private PopupSpinner shrineSpinner;
    private PopupSpinner quarrySpinner;
    private PopupSpinner templeSpinner;
    private PopupSpinner ricePaddySpinner;
    private PopupSpinner seppukuSpinner;

    //Golden Cards
    private PopupSpinner ninjaSpinner;
    private PopupSpinner dragonSpinner;
    private PopupSpinner shogunSpinner;
    //endregion
    //region JapanesePack PopupImages
    //Build Cards
    private PopupImage stockadeImage;
    private PopupImage fortressImage;
    private PopupImage monasteryImage;
    private PopupImage rampartImage;
    private PopupImage citadelImage;

    //Attack Cards
    private PopupImage ashigaruImage;
    private PopupImage shurikenImage;
    private PopupImage katanaImage;
    private PopupImage samuraiImage;
    private PopupImage dojoImage;

    //Magic Cards
    private PopupImage shrineImage;
    private PopupImage quarryImage;
    private PopupImage templeImage;
    private PopupImage ricePaddyImage;
    private PopupImage seppukuImage;

    //Golden Cards
    private PopupImage ninjaImage;
    private PopupImage dragonImage;
    private PopupImage shogunImage;
    //endregion


    public BuildScreen(CastleWars cw) {
        batch = new SpriteBatch();
        cam = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        cam.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);

        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, cam);

        castleWars = cw;

        cardScrollArea = new PopupScrollArea(Assets.invisible, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT, PopupScrollArea.VERTICAL, 8, GameWorld.CARD_SPACING, Card.CARD_WIDTH);

        //region BasicMenus
        PopupMenu barrierMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu wallMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu greatWallMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu reinforceMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu fortifyMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu castlesMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu architectMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu reserveMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu sabotageMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu strongholdMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu recruiterMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu spearmanMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu ramMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu catapultMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu legionMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu trebuchetMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu assassinMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu burglarMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu thiefMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu raidMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu createStonesMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu createWeaponsMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu createGemsMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu destroyStonesMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu destroyWeaponsMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu destroyGemsMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu mageMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu hatTrickMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu lightningMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu blastMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu jerichoMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu trojanHorseMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu blackPlagueMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu merlinMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu duplicateMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        //endregion
        //region Basic Spinners
        barrierSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, barrierMenu.getX(), barrierMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        wallSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, wallMenu.getX(), wallMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        greatWallSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, greatWallMenu.getX(), greatWallMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        reinforceSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, reinforceMenu.getX(), reinforceMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        fortifySpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, fortifyMenu.getX(), fortifyMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        castleSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, castlesMenu.getX(), castlesMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        architectSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, architectMenu.getX(), architectMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        reserveSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, reserveMenu.getX(), reserveMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        sabotageSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, sabotageMenu.getX(), sabotageMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        strongHoldSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, strongholdMenu.getX(), strongholdMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);

        recruiterSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, recruiterMenu.getX(), recruiterMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        spearmanSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, spearmanMenu.getX(), spearmanMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        ramSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, ramMenu.getX(), ramMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        catapultSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, catapultMenu.getX(), catapultMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        legionSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, legionMenu.getX(), legionMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        trebuchetSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, trebuchetMenu.getX(), trebuchetMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        assassinSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, assassinMenu.getX(), assassinMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        burglarSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, burglarMenu.getX(), burglarMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        thiefSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, thiefMenu.getX(), thiefMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        raidSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, raidMenu.getX(), raidMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);

        createStonesSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, createStonesMenu.getX(), createStonesMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        createWeaponsSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, createWeaponsMenu.getX(), createWeaponsMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        createGemsSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, createGemsMenu.getX(), createGemsMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        destroyStonesSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, destroyStonesMenu.getX(), destroyStonesMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        destroyWeaponsSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, destroyWeaponsMenu.getX(), destroyWeaponsMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        destroyGemsSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, destroyGemsMenu.getX(), destroyGemsMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        mageSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, mageMenu.getX(), mageMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        hatTrickSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, hatTrickMenu.getX(), hatTrickMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        lightningSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, lightningMenu.getX(), lightningMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        blastSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, blastMenu.getX(), blastMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);

        jerichoSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, jerichoMenu.getX(), jerichoMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        trojanHorseSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, trojanHorseMenu.getX(), trojanHorseMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        blackPlagueSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, blackPlagueMenu.getX(), blackPlagueMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        merlinSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, merlinMenu.getX(), merlinMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        duplicateSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, duplicateMenu.getX(), duplicateMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        //endregion
        //region Basic PopupImages
        barrierImage = new PopupImage(Assets.buildCardBarrier, Assets.invisible, barrierMenu.getX(), barrierMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        wallImage = new PopupImage(Assets.buildCardWall, Assets.invisible, wallMenu.getX(), wallMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        greatWallImage = new PopupImage(Assets.buildCardGreatWall, Assets.invisible, greatWallMenu.getX(), greatWallMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        reinforceImage = new PopupImage(Assets.buildCardReinforce, Assets.invisible, reinforceMenu.getX(), reinforceMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fortifyImage = new PopupImage(Assets.buildCardFortify, Assets.invisible, fortifyMenu.getX(), fortifyMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        castleImage = new PopupImage(Assets.buildCardCastle, Assets.invisible, castlesMenu.getX(), castlesMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        architectImage = new PopupImage(Assets.buildCardArchitect, Assets.invisible, architectMenu.getX(), architectMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        reserveImage = new PopupImage(Assets.buildCardReserve, Assets.invisible, reserveMenu.getX(), reserveMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        sabotageImage = new PopupImage(Assets.buildCardSabotage, Assets.invisible, sabotageMenu.getX(), sabotageMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        strongHoldImage = new PopupImage(Assets.buildCardStronghold, Assets.invisible, strongholdMenu.getX(), strongholdMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        recruiterImage = new PopupImage(Assets.attackCardRecruiter, Assets.invisible, recruiterMenu.getX(), recruiterMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        spearmanImage = new PopupImage(Assets.attackCardSpearman, Assets.invisible, spearmanMenu.getX(), spearmanMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ramImage = new PopupImage(Assets.attackCardRam, Assets.invisible, ramMenu.getX(), ramMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        catapultImage = new PopupImage(Assets.attackCardCatapult, Assets.invisible, catapultMenu.getX(), catapultMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        legionImage = new PopupImage(Assets.attackCardLegion, Assets.invisible, legionMenu.getX(), legionMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        trebuchetImage = new PopupImage(Assets.attackCardTrebuchet, Assets.invisible, trebuchetMenu.getX(), trebuchetMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        assassinImage = new PopupImage(Assets.cardBlank, Assets.invisible, assassinMenu.getX(), assassinMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        burglarImage = new PopupImage(Assets.cardBlank, Assets.invisible, burglarMenu.getX(), burglarMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        thiefImage = new PopupImage(Assets.cardBlank, Assets.invisible, thiefMenu.getX(), thiefMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        raidImage = new PopupImage(Assets.cardBlank, Assets.invisible, raidMenu.getX(), raidMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        createStonesImage = new PopupImage(Assets.gemCardCreateStones, Assets.invisible, createStonesMenu.getX(), createStonesMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        createWeaponsImage = new PopupImage(Assets.gemCardCreateWeapons, Assets.invisible, createWeaponsMenu.getX(), createWeaponsMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        createGemsImage = new PopupImage(Assets.gemCardCreateGems, Assets.invisible, createGemsMenu.getX(), createGemsMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyStonesImage = new PopupImage(Assets.gemCardDestroyStones, Assets.invisible, destroyStonesMenu.getX(), destroyStonesMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyWeaponsImage = new PopupImage(Assets.gemCardDestroyWeapons, Assets.invisible, destroyWeaponsMenu.getX(), destroyWeaponsMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyGemsImage = new PopupImage(Assets.gemCardDestroyGems, Assets.invisible, destroyGemsMenu.getX(), destroyGemsMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        mageImage = new PopupImage(Assets.gemCardMage, Assets.invisible, mageMenu.getX(), mageMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        hatTrickImage = new PopupImage(Assets.gemCardHatTrick, Assets.invisible, hatTrickMenu.getX(), hatTrickMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        lightningImage = new PopupImage(Assets.gemCardLightningStrike, Assets.invisible, lightningMenu.getX(), lightningMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blastImage = new PopupImage(Assets.gemCardBlast, Assets.invisible, blastMenu.getX(), blastMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        jerichoImage = new PopupImage(Assets.cardBlank, Assets.invisible, jerichoMenu.getX(), jerichoMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        trojanHorseImage = new PopupImage(Assets.cardBlank, Assets.invisible, trojanHorseMenu.getX(), trojanHorseMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blackPlagueImage = new PopupImage(Assets.cardBlank, Assets.invisible, blackPlagueMenu.getX(), blackPlagueMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        merlinImage = new PopupImage(Assets.cardBlank, Assets.invisible, merlinMenu.getX(), merlinMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        duplicateImage = new PopupImage(Assets.cardBlank, Assets.invisible, duplicateMenu.getX(), duplicateMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        //endregion

        //region FlamePack Menus
        PopupMenu wallOfFireMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu campfireMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu forgeMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu boilingOilMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu bonfireMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu fireMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu fireArrowsMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu flamingAxeMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu flamingShotMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu flameLegionMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu fireShamanMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu lavaFlowMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu coalMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu blacksmithMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu fireballMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu phoenixMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu meteorsMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu infernoMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        //endregion
        //region FlamePack Spinners
        wallOfFireSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, wallOfFireMenu.getX(), wallOfFireMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        campfireSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, campfireMenu.getX(), campfireMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        forgeSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, forgeMenu.getX(), forgeMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        boilingOilSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, boilingOilMenu.getX(), boilingOilMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        bonfireSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, bonfireMenu.getX(), bonfireMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);

        fireSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, fireMenu.getX(), fireMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        fireArrowsSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, fireArrowsMenu.getX(), fireArrowsMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        flamingAxeSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, flamingAxeMenu.getX(), flamingAxeMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        flamingShotSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, flamingShotMenu.getX(), flamingShotMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        flameLegionSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, flameLegionMenu.getX(), flameLegionMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);

        fireShamanSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, fireShamanMenu.getX(), fireShamanMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        lavaFlowSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, lavaFlowMenu.getX(), lavaFlowMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        coalSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, coalMenu.getX(), coalMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        blacksmithSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, blacksmithMenu.getX(), blacksmithMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        fireballSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, fireballMenu.getX(), fireballMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);

        phoenixSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, phoenixMenu.getX(), phoenixMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        meteorsSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, meteorsMenu.getX(), meteorsMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        infernoSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, infernoMenu.getX(), infernoMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        //endregion
        //region FlamePack PopupImages
        wallOfFireImage = new PopupImage(Assets.flameBuildCardWallOfFire, Assets.invisible, wallOfFireMenu.getX(), wallOfFireMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        campfireImage = new PopupImage(Assets.flameBuildCardCampfire, Assets.invisible, campfireMenu.getX(), campfireMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        forgeImage = new PopupImage(Assets.flameBuildCardForge, Assets.invisible, forgeMenu.getX(), forgeMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        boilingOilImage = new PopupImage(Assets.flameBuildCardBoilingOil, Assets.invisible, boilingOilMenu.getX(), boilingOilMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        bonfireImage = new PopupImage(Assets.flameBuildCardBonfire, Assets.invisible, bonfireMenu.getX(), bonfireMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        fireImage = new PopupImage(Assets.flameAttackCardFire, Assets.invisible, fireMenu.getX(), fireMenu.getY(), Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireArrowsImage = new PopupImage(Assets.flameAttackCardFireArrows, Assets.invisible, fireArrowsMenu.getX(), fireArrowsMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flamingAxeImage = new PopupImage(Assets.flameAttackCardFlamingAxe, Assets.invisible, flamingAxeMenu.getX(), flamingAxeMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flamingShotImage = new PopupImage(Assets.flameAttackCardFlamingShot, Assets.invisible, flamingShotMenu.getX(), flamingShotMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flameLegionImage = new PopupImage(Assets.flameAttackCardFlameLegion, Assets.invisible, flameLegionMenu.getX(), flameLegionMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        fireShamanImage = new PopupImage(Assets.flameGemCardFireShaman, Assets.invisible, fireShamanMenu.getX(), fireShamanMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        lavaFlowImage = new PopupImage(Assets.flameGemCardLavaFlow, Assets.invisible, lavaFlowMenu.getX(), lavaFlowMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        coalImage = new PopupImage(Assets.flameGemCardCoal, Assets.invisible, coalMenu.getX(), coalMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blacksmithImage = new PopupImage(Assets.flameGemCardBlacksmith, Assets.invisible, blacksmithMenu.getX(), blacksmithMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireballImage = new PopupImage(Assets.flameGemCardFireball, Assets.invisible, fireballMenu.getX(), fireballMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        phoenixImage = new PopupImage(Assets.flameGoldCardPhoenix, Assets.invisible, phoenixMenu.getX(), phoenixMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        meteorsImage = new PopupImage(Assets.flameGoldCardMeteors, Assets.invisible, meteorsMenu.getX(), meteorsMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        infernoImage = new PopupImage(Assets.flameGoldCardInferno, Assets.invisible, infernoMenu.getX(), infernoMenu.getY() + SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        //endregion

        //region JapanesePack Menus
        PopupMenu stockadeMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu fortressMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu monasteryMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu rampartMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu citadelMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu ashigaruMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu shurikenMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu katanaMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu samuraiMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu dojoMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu shrineMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu quarryMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu templeMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu ricePaddyMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu seppukuMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu ninjaMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu dragonMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu shogunMenu = new PopupMenu(Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        //endregion
        //region JapanesePack Spinners
        stockadeSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, stockadeMenu.getX(), stockadeMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        fortressSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, fortressMenu.getX(), fortressMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        monasterySpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, monasteryMenu.getX(), monasteryMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        rampartSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, rampartMenu.getX(), rampartMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        citadelSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, citadelMenu.getX(), citadelMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);

        ashigaruSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, ashigaruMenu.getX(), ashigaruMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        shurikenSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, shurikenMenu.getX(), shurikenMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        katanaSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, katanaMenu.getX(), katanaMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        samuraiSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, samuraiMenu.getX(), samuraiMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        dojoSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, dojoMenu.getX(), dojoMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);

        shrineSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, shrineMenu.getX(), shrineMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        quarrySpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, quarryMenu.getX(), quarryMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        templeSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, templeMenu.getX(), templeMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        ricePaddySpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, ricePaddyMenu.getX(), ricePaddyMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        seppukuSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, seppukuMenu.getX(), seppukuMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);

        ninjaSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, ninjaMenu.getX(), ninjaMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        dragonSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, dragonMenu.getX(), dragonMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        shogunSpinner = new PopupSpinner(Assets.invisible, Assets.okButtonUp, Assets.okButtonDown, Assets.textFieldFont, shogunMenu.getX(), shogunMenu.getY(), Card.CARD_WIDTH, SPINNER_HEIGHT);
        //endregion
        //region JapanesePack PopupImages
        stockadeImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fortressImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        monasteryImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        rampartImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        citadelImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        ashigaruImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        shurikenImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        katanaImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        samuraiImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        dojoImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        shrineImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        quarryImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        templeImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ricePaddyImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        seppukuImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        ninjaImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        dragonImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        shogunImage = new PopupImage(Assets.cardBlank, Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        //endregion

    }

    private void buildScreen(SpriteBatch batch){

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    public void update(float delta){

    }

    public void draw(){
        Gdx.gl.glClearColor(0, 0, .196f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        buildScreen(batch);
        batch.setProjectionMatrix(cam.combined);
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

