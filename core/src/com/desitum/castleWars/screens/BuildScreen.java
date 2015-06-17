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
import com.desitum.castleWars.libraries.menu.OnClickListener;
import com.desitum.castleWars.libraries.menu.PopupButtonMaterial;
import com.desitum.castleWars.libraries.menu.PopupImage;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupScrollArea;
import com.desitum.castleWars.libraries.menu.PopupSpinner;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.world.GameWorld;
import com.desitum.castleWars.world.MenuWorld;

/**
 * Created by Zmyth97 on 6/1/2015.
 */
public class BuildScreen extends KodyWorld implements Screen {

    public static final float SCREEN_WIDTH = 150;
    public static final float SCREEN_HEIGHT = 100;
    public static final float SPINNER_HEIGHT = 12;

    private CastleWars castleWars;

    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;

    private PopupScrollArea cardScrollArea;

    GooglePlayServicesInterface gpgs;

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

        cardScrollArea = new PopupScrollArea(Assets.invisible, 10, 0, SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT, PopupScrollArea.VERTICAL, 4, GameWorld.CARD_SPACING, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        //region BasicMenus
        PopupMenu barrierMenu = new PopupMenu         (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu wallMenu = new PopupMenu            (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu greatWallMenu = new PopupMenu       (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu reinforceMenu = new PopupMenu       (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu fortifyMenu = new PopupMenu         (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu castlesMenu = new PopupMenu         (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu architectMenu = new PopupMenu       (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu reserveMenu = new PopupMenu         (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu sabotageMenu = new PopupMenu        (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu strongholdMenu = new PopupMenu      (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu recruiterMenu = new PopupMenu       (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu spearmanMenu = new PopupMenu        (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu ramMenu = new PopupMenu             (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu catapultMenu = new PopupMenu        (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu legionMenu = new PopupMenu          (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu trebuchetMenu = new PopupMenu       (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu assassinMenu = new PopupMenu        (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu burglarMenu = new PopupMenu         (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu thiefMenu = new PopupMenu           (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu raidMenu = new PopupMenu            (Assets.invisible,  0, 0, Card.CARD_WIDTH,  Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu createStonesMenu = new PopupMenu    (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu createWeaponsMenu = new PopupMenu   (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu createGemsMenu = new PopupMenu      (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu destroyStonesMenu = new PopupMenu   (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu destroyWeaponsMenu = new PopupMenu  (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu destroyGemsMenu = new PopupMenu     (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu mageMenu = new PopupMenu            (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu hatTrickMenu = new PopupMenu        (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu lightningMenu = new PopupMenu       (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu blastMenu = new PopupMenu           (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu jerichoMenu = new PopupMenu         (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu trojanHorseMenu = new PopupMenu     (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu blackPlagueMenu = new PopupMenu     (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu merlinMenu = new PopupMenu          (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu duplicateMenu = new PopupMenu       (Assets.invisible,  0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        //endregion
        //region Basic Spinners
        barrierSpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        wallSpinner = new PopupSpinner          (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        greatWallSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        reinforceSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        fortifySpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        castleSpinner = new PopupSpinner        (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        architectSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        reserveSpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        sabotageSpinner = new PopupSpinner      (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        strongHoldSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);


        recruiterSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        spearmanSpinner = new PopupSpinner      (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        ramSpinner = new PopupSpinner           (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        catapultSpinner = new PopupSpinner      (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        legionSpinner = new PopupSpinner        (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        trebuchetSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        assassinSpinner = new PopupSpinner      (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        burglarSpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        thiefSpinner = new PopupSpinner         (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        raidSpinner = new PopupSpinner          (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);

        createStonesSpinner = new PopupSpinner  (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        createWeaponsSpinner = new PopupSpinner (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        createGemsSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        destroyStonesSpinner = new PopupSpinner (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        destroyWeaponsSpinner = new PopupSpinner(Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        destroyGemsSpinner = new PopupSpinner   (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        mageSpinner = new PopupSpinner          (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        hatTrickSpinner = new PopupSpinner      (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        lightningSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        blastSpinner = new PopupSpinner         (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);

        jerichoSpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        trojanHorseSpinner = new PopupSpinner   (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        blackPlagueSpinner = new PopupSpinner   (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        merlinSpinner = new PopupSpinner        (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        duplicateSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);

        barrierMenu.addPopupWidget(barrierSpinner);
        wallMenu.addPopupWidget(wallSpinner);
        greatWallMenu.addPopupWidget(greatWallSpinner);
        reinforceMenu.addPopupWidget(reinforceSpinner);
        fortifyMenu.addPopupWidget(fortifySpinner);
        castlesMenu.addPopupWidget(castleSpinner);
        architectMenu.addPopupWidget(architectSpinner);
        reserveMenu.addPopupWidget(reserveSpinner);
        sabotageMenu.addPopupWidget(sabotageSpinner);
        strongholdMenu.addPopupWidget(strongHoldSpinner);

        recruiterMenu.addPopupWidget(recruiterSpinner);
        spearmanMenu.addPopupWidget(spearmanSpinner);
        ramMenu.addPopupWidget(ramSpinner);
        catapultMenu.addPopupWidget(catapultSpinner);
        legionMenu.addPopupWidget(legionSpinner);
        trebuchetMenu.addPopupWidget(trebuchetSpinner);
        assassinMenu.addPopupWidget(assassinSpinner);
        burglarMenu.addPopupWidget(burglarSpinner);
        thiefMenu.addPopupWidget(thiefSpinner);
        raidMenu.addPopupWidget(raidSpinner);

        createStonesMenu.addPopupWidget(createStonesSpinner);
        createWeaponsMenu.addPopupWidget(createWeaponsSpinner);
        createGemsMenu.addPopupWidget(createGemsSpinner);
        destroyStonesMenu.addPopupWidget(destroyStonesSpinner);
        destroyWeaponsMenu.addPopupWidget(destroyWeaponsSpinner);
        destroyGemsMenu.addPopupWidget(destroyGemsSpinner);
        mageMenu.addPopupWidget(mageSpinner);
        hatTrickMenu.addPopupWidget(hatTrickSpinner);
        lightningMenu.addPopupWidget(lightningSpinner);
        blastMenu.addPopupWidget(blastSpinner);

        jerichoMenu.addPopupWidget(jerichoSpinner);
        trojanHorseMenu.addPopupWidget(trojanHorseSpinner);
        blackPlagueMenu.addPopupWidget(blackPlagueSpinner);
        merlinMenu.addPopupWidget(merlinSpinner);
        duplicateMenu.addPopupWidget(duplicateSpinner);

        //endregion
        //region Basic PopupImages
        barrierImage = new PopupImage        (Assets.buildCardBarrier,       Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        wallImage = new PopupImage           (Assets.buildCardWall,          Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        greatWallImage = new PopupImage      (Assets.buildCardGreatWall,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        reinforceImage = new PopupImage      (Assets.buildCardReinforce,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fortifyImage = new PopupImage        (Assets.buildCardFortify,       Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        castleImage = new PopupImage         (Assets.buildCardCastle,        Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        architectImage = new PopupImage      (Assets.buildCardArchitect,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        reserveImage = new PopupImage        (Assets.buildCardReserve,       Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        sabotageImage = new PopupImage       (Assets.buildCardSabotage,      Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        strongHoldImage = new PopupImage     (Assets.buildCardStronghold,    Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        recruiterImage = new PopupImage      (Assets.attackCardRecruiter,    Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        spearmanImage = new PopupImage       (Assets.attackCardSpearman,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        ramImage = new PopupImage            (Assets.attackCardRam,          Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        catapultImage = new PopupImage       (Assets.attackCardCatapult,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        legionImage = new PopupImage         (Assets.attackCardLegion,       Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        trebuchetImage = new PopupImage      (Assets.attackCardTrebuchet,    Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        assassinImage = new PopupImage       (Assets.attackCardAssassin,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        burglarImage = new PopupImage        (Assets.attackCardBurglar,      Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        thiefImage = new PopupImage          (Assets.attackCardThief,        Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        raidImage = new PopupImage           (Assets.attackCardRaid,         Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        createStonesImage = new PopupImage   (Assets.gemCardCreateStones,    Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        createWeaponsImage = new PopupImage  (Assets.gemCardCreateWeapons,   Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        createGemsImage = new PopupImage     (Assets.gemCardCreateGems,      Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyStonesImage = new PopupImage  (Assets.gemCardDestroyStones,   Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyWeaponsImage = new PopupImage (Assets.gemCardDestroyWeapons,  Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        destroyGemsImage = new PopupImage    (Assets.gemCardDestroyGems,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        mageImage = new PopupImage           (Assets.gemCardMage,            Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        hatTrickImage = new PopupImage       (Assets.gemCardHatTrick,        Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        lightningImage = new PopupImage      (Assets.gemCardLightningStrike, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blastImage = new PopupImage          (Assets.gemCardBlast,           Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        jerichoImage = new PopupImage        (Assets.goldCardJericho,        Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        trojanHorseImage = new PopupImage    (Assets.goldCardTrojanHorse,    Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blackPlagueImage = new PopupImage    (Assets.goldCardBlackPlague,    Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        merlinImage = new PopupImage         (Assets.goldCardMerlin,         Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        duplicateImage = new PopupImage      (Assets.goldCardDuplicate,      Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        barrierMenu.addPopupWidget(barrierImage);
        wallMenu.addPopupWidget(wallImage);
        greatWallMenu.addPopupWidget(greatWallImage);
        reinforceMenu.addPopupWidget(reinforceImage);
        fortifyMenu.addPopupWidget(fortifyImage);
        castlesMenu.addPopupWidget(castleImage);
        architectMenu.addPopupWidget(architectImage);
        reserveMenu.addPopupWidget(reserveImage);
        sabotageMenu.addPopupWidget(sabotageImage);
        strongholdMenu.addPopupWidget(strongHoldImage);

        recruiterMenu.addPopupWidget(recruiterImage);
        spearmanMenu.addPopupWidget(spearmanImage);
        ramMenu.addPopupWidget(ramImage);
        catapultMenu.addPopupWidget(catapultImage);
        legionMenu.addPopupWidget(legionImage);
        trebuchetMenu.addPopupWidget(trebuchetImage);
        assassinMenu.addPopupWidget(assassinImage);
        burglarMenu.addPopupWidget(burglarImage);
        thiefMenu.addPopupWidget(thiefImage);
        raidMenu.addPopupWidget(raidImage);

        createStonesMenu.addPopupWidget(createStonesImage);
        createWeaponsMenu.addPopupWidget(createWeaponsImage);
        createGemsMenu.addPopupWidget(createGemsImage);
        destroyStonesMenu.addPopupWidget(destroyStonesImage);
        destroyWeaponsMenu.addPopupWidget(destroyWeaponsImage);
        destroyGemsMenu.addPopupWidget(destroyGemsImage);
        mageMenu.addPopupWidget(mageImage);
        hatTrickMenu.addPopupWidget(hatTrickImage);
        lightningMenu.addPopupWidget(lightningImage);
        blastMenu.addPopupWidget(blastImage);

        jerichoMenu.addPopupWidget(jerichoImage);
        trojanHorseMenu.addPopupWidget(trojanHorseImage);
        blackPlagueMenu.addPopupWidget(blackPlagueImage);
        merlinMenu.addPopupWidget(merlinImage);
        duplicateMenu.addPopupWidget(duplicateImage);
        //endregion
        //region FlamePack Menus
        PopupMenu wallOfFireMenu = new PopupMenu      (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu campfireMenu = new PopupMenu        (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu forgeMenu = new PopupMenu           (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu boilingOilMenu = new PopupMenu      (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu bonfireMenu = new PopupMenu         (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu fireMenu = new PopupMenu            (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu fireArrowsMenu = new PopupMenu      (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu flamingAxeMenu = new PopupMenu      (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu flamingShotMenu = new PopupMenu     (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu flameLegionMenu = new PopupMenu     (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu fireShamanMenu = new PopupMenu      (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu lavaFlowMenu = new PopupMenu        (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu coalMenu = new PopupMenu            (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu blacksmithMenu = new PopupMenu      (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu fireballMenu = new PopupMenu        (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu phoenixMenu = new PopupMenu         (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu meteorsMenu = new PopupMenu         (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu infernoMenu = new PopupMenu         (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        //endregion
        //region FlamePack Spinners
        wallOfFireSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        campfireSpinner = new PopupSpinner      (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        forgeSpinner = new PopupSpinner         (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        boilingOilSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        bonfireSpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        fireSpinner = new PopupSpinner          (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        fireArrowsSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        flamingAxeSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        flamingShotSpinner = new PopupSpinner   (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        flameLegionSpinner = new PopupSpinner   (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        fireShamanSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        lavaFlowSpinner = new PopupSpinner      (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        coalSpinner = new PopupSpinner          (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        blacksmithSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        fireballSpinner = new PopupSpinner      (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        phoenixSpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        meteorsSpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);
        infernoSpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0,  Card.CARD_WIDTH, SPINNER_HEIGHT);

        wallOfFireMenu.addPopupWidget(wallOfFireSpinner);
        campfireMenu.addPopupWidget(campfireSpinner);
        forgeMenu.addPopupWidget(forgeSpinner);
        boilingOilMenu.addPopupWidget(boilingOilSpinner);
        bonfireMenu.addPopupWidget(bonfireSpinner);

        fireMenu.addPopupWidget(fireSpinner);
        fireArrowsMenu.addPopupWidget(fireArrowsSpinner);
        flamingAxeMenu.addPopupWidget(flamingAxeSpinner);
        flamingShotMenu.addPopupWidget(flamingShotSpinner);
        flameLegionMenu.addPopupWidget(flameLegionSpinner);

        fireShamanMenu.addPopupWidget(fireShamanSpinner);
        lavaFlowMenu.addPopupWidget(lavaFlowSpinner);
        coalMenu.addPopupWidget(coalSpinner);
        blacksmithMenu.addPopupWidget(blacksmithSpinner);
        fireballMenu.addPopupWidget(fireballSpinner);

        phoenixMenu.addPopupWidget(phoenixSpinner);
        meteorsMenu.addPopupWidget(meteorsSpinner);
        infernoMenu.addPopupWidget(infernoSpinner);
        //endregion
        //region FlamePack PopupImages
        wallOfFireImage = new PopupImage    (Assets.flameBuildCardWallOfFire,   Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        campfireImage = new PopupImage      (Assets.flameBuildCardCampfire,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        forgeImage = new PopupImage         (Assets.flameBuildCardForge,        Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        boilingOilImage = new PopupImage    (Assets.flameBuildCardBoilingOil,   Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        bonfireImage = new PopupImage       (Assets.flameBuildCardBonfire,      Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireImage = new PopupImage          (Assets.flameAttackCardFire,        Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireArrowsImage = new PopupImage    (Assets.flameAttackCardFireArrows,  Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flamingAxeImage = new PopupImage    (Assets.flameAttackCardFlamingAxe,  Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flamingShotImage = new PopupImage   (Assets.flameAttackCardFlamingShot, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        flameLegionImage = new PopupImage   (Assets.flameAttackCardFlameLegion, Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        fireShamanImage = new PopupImage    (Assets.flameGemCardFireShaman,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        lavaFlowImage = new PopupImage      (Assets.flameGemCardLavaFlow,       Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        coalImage = new PopupImage          (Assets.flameGemCardCoal,           Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        blacksmithImage = new PopupImage    (Assets.flameGemCardBlacksmith,     Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        fireballImage = new PopupImage      (Assets.flameGemCardFireball,       Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        phoenixImage = new PopupImage       (Assets.flameGoldCardPhoenix,       Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        meteorsImage = new PopupImage       (Assets.flameGoldCardMeteors,       Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);
        infernoImage = new PopupImage       (Assets.flameGoldCardInferno,       Assets.invisible, 0, SPINNER_HEIGHT, Card.CARD_WIDTH, Card.CARD_HEIGHT, false);

        wallOfFireMenu.addPopupWidget(wallOfFireImage);
        campfireMenu.addPopupWidget(campfireImage);
        forgeMenu.addPopupWidget(forgeImage);
        boilingOilMenu.addPopupWidget(boilingOilImage);
        bonfireMenu.addPopupWidget(bonfireImage);

        fireMenu.addPopupWidget(fireImage);
        fireArrowsMenu.addPopupWidget(fireArrowsImage);
        flamingAxeMenu.addPopupWidget(flamingAxeImage);
        flamingShotMenu.addPopupWidget(flamingShotImage);
        flameLegionMenu.addPopupWidget(flameLegionImage);

        fireShamanMenu.addPopupWidget(fireShamanImage);
        lavaFlowMenu.addPopupWidget(lavaFlowImage);
        coalMenu.addPopupWidget(coalImage);
        blacksmithMenu.addPopupWidget(blacksmithImage);
        fireballMenu.addPopupWidget(fireballImage);

        phoenixMenu.addPopupWidget(phoenixImage);
        meteorsMenu.addPopupWidget(meteorsImage);
        infernoMenu.addPopupWidget(infernoImage);
        //endregion
        //region JapanesePack Menus
        PopupMenu stockadeMenu = new PopupMenu   (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu fortressMenu = new PopupMenu   (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu monasteryMenu = new PopupMenu  (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu rampartMenu = new PopupMenu    (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu citadelMenu = new PopupMenu    (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu ashigaruMenu = new PopupMenu   (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu shurikenMenu = new PopupMenu   (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu katanaMenu = new PopupMenu     (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu samuraiMenu = new PopupMenu    (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu dojoMenu = new PopupMenu       (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu shrineMenu = new PopupMenu     (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu quarryMenu = new PopupMenu     (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu templeMenu = new PopupMenu     (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu ricePaddyMenu = new PopupMenu  (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu seppukuMenu = new PopupMenu    (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        PopupMenu ninjaMenu = new PopupMenu      (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu dragonMenu = new PopupMenu     (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);
        PopupMenu shogunMenu = new PopupMenu     (Assets.invisible, 0, 0, Card.CARD_WIDTH, Card.CARD_HEIGHT + SPINNER_HEIGHT);

        //endregion
        //region JapanesePack Spinners
        stockadeSpinner = new PopupSpinner   (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        fortressSpinner = new PopupSpinner   (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        monasterySpinner = new PopupSpinner  (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        rampartSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        citadelSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);

        ashigaruSpinner = new PopupSpinner   (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        shurikenSpinner = new PopupSpinner   (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        katanaSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        samuraiSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        dojoSpinner = new PopupSpinner       (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);

        shrineSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        quarrySpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        templeSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        ricePaddySpinner = new PopupSpinner  (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        seppukuSpinner = new PopupSpinner    (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);

        ninjaSpinner = new PopupSpinner      (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        dragonSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);
        shogunSpinner = new PopupSpinner     (Assets.invisible, Assets.upArrow, Assets.downArrow, Assets.textFieldFont, 0, 0, Card.CARD_WIDTH, SPINNER_HEIGHT);

        stockadeMenu.addPopupWidget(stockadeSpinner);
        fortressMenu.addPopupWidget(fortressSpinner);
        monasteryMenu.addPopupWidget(monasterySpinner);
        rampartMenu.addPopupWidget(rampartSpinner);
        citadelMenu.addPopupWidget(citadelSpinner);

        ashigaruMenu.addPopupWidget(ashigaruSpinner);
        shurikenMenu.addPopupWidget(shurikenSpinner);
        katanaMenu.addPopupWidget(katanaSpinner);
        samuraiMenu.addPopupWidget(samuraiSpinner);
        dojoMenu.addPopupWidget(dojoSpinner);

        shrineMenu.addPopupWidget(shrineSpinner);
        quarryMenu.addPopupWidget(quarrySpinner);
        templeMenu.addPopupWidget(templeSpinner);
        ricePaddyMenu.addPopupWidget(ricePaddySpinner);
        seppukuMenu.addPopupWidget(seppukuSpinner);

        ninjaMenu.addPopupWidget(ninjaSpinner);
        dragonMenu.addPopupWidget(dragonSpinner);
        shogunMenu.addPopupWidget(shogunSpinner);
        //endregion
        //region JapanesePack PopupImages
        stockadeImage = new PopupImage   (Assets.japaneseBuildCardStockade,  Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        fortressImage = new PopupImage   (Assets.japaneseBuildCardFortress,  Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        monasteryImage = new PopupImage  (Assets.japaneseBuildCardMonastery, Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        rampartImage = new PopupImage    (Assets.japaneseBuildCardRampart,   Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        citadelImage = new PopupImage    (Assets.japaneseBuildCardCitadel,   Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        ashigaruImage = new PopupImage   (Assets.japaneseAttackCardAshigaru, Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        shurikenImage = new PopupImage   (Assets.japaneseAttackCardShuriken, Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        katanaImage = new PopupImage     (Assets.japaneseAttackCardKatana,   Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        samuraiImage = new PopupImage    (Assets.japaneseAttackCardSamurai,  Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        dojoImage = new PopupImage       (Assets.japaneseAttackCardDojo,     Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        shrineImage = new PopupImage     (Assets.japaneseGemCardShrine,      Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        quarryImage = new PopupImage     (Assets.japaneseGemCardQuarry,      Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        templeImage = new PopupImage     (Assets.japaneseGemCardTemple,      Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        ricePaddyImage = new PopupImage  (Assets.japaneseGemCardRicePaddy,   Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        seppukuImage = new PopupImage    (Assets.japaneseGemCardSeppuku,     Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        ninjaImage = new PopupImage      (Assets.japaneseGoldCardNinja,      Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        dragonImage = new PopupImage     (Assets.japaneseGoldCardDragon,     Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);
        shogunImage = new PopupImage     (Assets.japaneseGoldCardShogun,     Assets.invisible, 0, SPINNER_HEIGHT,  Card.CARD_WIDTH,  Card.CARD_HEIGHT, false);

        stockadeMenu.addPopupWidget(stockadeImage);
        fortressMenu.addPopupWidget(fortressImage);
        monasteryMenu.addPopupWidget(monasteryImage);
        rampartMenu.addPopupWidget(rampartImage);
        citadelMenu.addPopupWidget(citadelImage);

        ashigaruMenu.addPopupWidget(ashigaruImage);
        shurikenMenu.addPopupWidget(shurikenImage);
        katanaMenu.addPopupWidget(katanaImage);
        samuraiMenu.addPopupWidget(samuraiImage);
        dojoMenu.addPopupWidget(dojoImage);

        shrineMenu.addPopupWidget(shrineImage);
        quarryMenu.addPopupWidget(quarryImage);
        templeMenu.addPopupWidget(templeImage);
        ricePaddyMenu.addPopupWidget(ricePaddyImage);
        seppukuMenu.addPopupWidget(seppukuImage);

        ninjaMenu.addPopupWidget(ninjaImage);
        dragonMenu.addPopupWidget(dragonImage);
        shogunMenu.addPopupWidget(shogunImage);
        //endregion
        //region Add Basic Menus To Card Scroll Area
        cardScrollArea.addPopupWidget(barrierMenu);
        cardScrollArea.addPopupWidget(wallMenu);
        cardScrollArea.addPopupWidget(greatWallMenu);
        cardScrollArea.addPopupWidget(reinforceMenu);
        cardScrollArea.addPopupWidget(fortifyMenu);
        cardScrollArea.addPopupWidget(castlesMenu);
        cardScrollArea.addPopupWidget(architectMenu);
        cardScrollArea.addPopupWidget(reserveMenu);
        cardScrollArea.addPopupWidget(sabotageMenu);
        cardScrollArea.addPopupWidget(strongholdMenu);
        cardScrollArea.addPopupWidget(recruiterMenu);
        cardScrollArea.addPopupWidget(spearmanMenu);
        cardScrollArea.addPopupWidget(ramMenu);
        cardScrollArea.addPopupWidget(catapultMenu);
        cardScrollArea.addPopupWidget(legionMenu);
        cardScrollArea.addPopupWidget(trebuchetMenu);
        cardScrollArea.addPopupWidget(assassinMenu);
        cardScrollArea.addPopupWidget(burglarMenu);
        cardScrollArea.addPopupWidget(thiefMenu);
        cardScrollArea.addPopupWidget(raidMenu);
        cardScrollArea.addPopupWidget(createStonesMenu);
        cardScrollArea.addPopupWidget(createWeaponsMenu);
        cardScrollArea.addPopupWidget(createGemsMenu);
        cardScrollArea.addPopupWidget(destroyStonesMenu);
        cardScrollArea.addPopupWidget(destroyWeaponsMenu);
        cardScrollArea.addPopupWidget(destroyGemsMenu);
        cardScrollArea.addPopupWidget(mageMenu);
        cardScrollArea.addPopupWidget(hatTrickMenu);
        cardScrollArea.addPopupWidget(lightningMenu);
        cardScrollArea.addPopupWidget(blastMenu);
        cardScrollArea.addPopupWidget(jerichoMenu);
        cardScrollArea.addPopupWidget(trojanHorseMenu);
        cardScrollArea.addPopupWidget(blackPlagueMenu);
        cardScrollArea.addPopupWidget(merlinMenu);
        cardScrollArea.addPopupWidget(duplicateMenu);
        //endregion
        //region Add Japanese Menus To Card Scoll Area
        if (Settings.BOUGHT_JAPANESE_PACK) {
            cardScrollArea.addPopupWidget(stockadeMenu);
            cardScrollArea.addPopupWidget(fortressMenu);
            cardScrollArea.addPopupWidget(monasteryMenu);
            cardScrollArea.addPopupWidget(rampartMenu);
            cardScrollArea.addPopupWidget(citadelMenu);

            cardScrollArea.addPopupWidget(ashigaruMenu);
            cardScrollArea.addPopupWidget(shurikenMenu);
            cardScrollArea.addPopupWidget(katanaMenu);
            cardScrollArea.addPopupWidget(samuraiMenu);
            cardScrollArea.addPopupWidget(dojoMenu);

            cardScrollArea.addPopupWidget(shrineMenu);
            cardScrollArea.addPopupWidget(quarryMenu);
            cardScrollArea.addPopupWidget(templeMenu);
            cardScrollArea.addPopupWidget(ricePaddyMenu);
            cardScrollArea.addPopupWidget(seppukuMenu);

            cardScrollArea.addPopupWidget(ninjaMenu);
            cardScrollArea.addPopupWidget(dragonMenu);
            cardScrollArea.addPopupWidget(shogunMenu);
        }
        //endregion
        //region Add Flame Menus To Card Scroll Area
        if (Settings.BOUGHT_FlAME_PACK) {
            cardScrollArea.addPopupWidget(wallOfFireMenu);
            cardScrollArea.addPopupWidget(campfireMenu);
            cardScrollArea.addPopupWidget(forgeMenu);
            cardScrollArea.addPopupWidget(boilingOilMenu);
            cardScrollArea.addPopupWidget(bonfireMenu);

            cardScrollArea.addPopupWidget(fireMenu);
            cardScrollArea.addPopupWidget(fireArrowsMenu);
            cardScrollArea.addPopupWidget(flamingAxeMenu);
            cardScrollArea.addPopupWidget(flamingShotMenu);
            cardScrollArea.addPopupWidget(flameLegionMenu);

            cardScrollArea.addPopupWidget(fireShamanMenu);
            cardScrollArea.addPopupWidget(lavaFlowMenu);
            cardScrollArea.addPopupWidget(coalMenu);
            cardScrollArea.addPopupWidget(blacksmithMenu);
            cardScrollArea.addPopupWidget(fireballMenu);

            cardScrollArea.addPopupWidget(phoenixMenu);
            cardScrollArea.addPopupWidget(meteorsMenu);
            cardScrollArea.addPopupWidget(infernoMenu);
        }
        //endregion

        cardScrollArea.moveIn();
        cardScrollArea.enable();

        addWidget(cardScrollArea);

        PopupButtonMaterial saveButton = new PopupButtonMaterial(Assets.okButtonRound, SCREEN_WIDTH - 22, 2, MenuWorld.BUTTON_HEIGHT, 20, 20);
        saveButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                gpgs.unlockAchievement(CastleWars.DO_IT_YOURSELF); //First time Building Deck
                //KODY SAVE LOGIC HERE
                moveToMenuScreen();
                //DISPOSE STUFF HERE?
            }
        });
        addWidget(saveButton);

        PopupButtonMaterial cancelButton = new PopupButtonMaterial(Assets.cancelButtonRound, 2, 2, MenuWorld.BUTTON_HEIGHT, 20, 20);
        cancelButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                moveToMenuScreen();
                //DISPOSE STUFF HERE?
            }
        });
        addWidget(cancelButton);
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
        cam.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
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

