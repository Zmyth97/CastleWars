package com.desitum.castleWars.data;

import com.badlogic.gdx.graphics.Color;
import com.desitum.castleWars.libraries.animation.ColorEffects;
import com.desitum.castleWars.libraries.menu.PopupTextLabel;
import com.desitum.castleWars.world.GameInterface;

/**
 * Created by Zmyth97 on 5/19/2015.
 */
public class Resources {
    private GameInterface gi;

    private int playerBuilders;
    private int playerSoldiers;
    private int playerWizards;
    private int playerStones;
    private int playerWeapons;
    private int playerGems;

    private int computerBuilders;
    private int computerSoldiers;
    private int computerWizards;
    private int computerStones;
    private int computerWeapons;
    private int computerGems;

    public Resources(GameInterface gameInterface){
        this.gi = gameInterface;

        playerBuilders = 2;
        playerSoldiers = 2;
        playerWizards = 2;
        playerStones = 16;
        playerWeapons = 16;
        playerGems = 16;

        computerBuilders = 2;
        computerSoldiers = 2;
        computerWizards = 2;
        computerStones = 16;
        computerWeapons = 16;
        computerGems = 16;
    }

    public int getPlayerBuilders() {
        return playerBuilders;
    }

    public int getPlayerSoldiers() {
        return playerSoldiers;
    }

    public int getPlayerWizards() {
        return playerWizards;
    }

    public int getPlayerStones() {
        return playerStones;
    }

    public int getPlayerWeapons() {
        return playerWeapons;
    }

    public int getPlayerGems() {
        return playerGems;
    }

    public int getComputerBuilders() {
        return computerBuilders;
    }

    public int getComputerSoldiers() {
        return computerSoldiers;
    }

    public int getComputerWizards() {
        return computerWizards;
    }

    public int getComputerStones() {
        return computerStones;
    }

    public int getComputerWeapons() {
        return computerWeapons;
    }

    public int getComputerGems() {
        return computerGems;
    }


    public int adjustPlayerBuilders(int amount) {
        int difference = amount;
        if (playerBuilders > Math.abs(difference) && amount < 0) {
            difference = -playerBuilders;
            playerBuilders = 0;
        } else {
            playerBuilders += amount;
        }
        if (difference != 0) gi.setPlayerBuildersLabelChangeText(difference);
        return difference;
    }

    public int adjustPlayerSoldiers(int amount) {
        int difference = amount;
        if (playerSoldiers > Math.abs(difference) && amount < 0) {
            difference = -playerSoldiers;
            playerSoldiers = 0;
        } else {
            playerSoldiers += amount;
        }
        if (difference != 0)  gi.setPlayerSoldiersLabelChangeText(difference);
        return difference;
    }

    public int adjustPlayerWizards(int amount) {
        int difference = amount;
        if (playerWizards > Math.abs(difference) && amount < 0) {
            difference = -playerWizards;
            playerWizards = 0;
        } else {
            playerWizards += amount;
        }
        if (difference != 0)  gi.setPlayerWizardsLabelChangeText(difference);
        return difference;
    }

    public int adjustPlayerStones(int amount) {
        int difference = amount;
        if (playerStones > Math.abs(difference) && amount < 0) {
            difference = -playerStones;
            playerStones = 0;
        } else {
            playerStones += amount;
        }
        if (difference != 0)  gi.setPlayerStoneLabelChangeText(difference);
        return difference;
    }

    public int adjustPlayerWeapons(int amount) {
        int difference = amount;
        if (playerWeapons > Math.abs(difference) && amount < 0) {
            difference = -playerWeapons;
            playerWeapons = 0;
        } else {
            playerWeapons += amount;
        }
        if (difference != 0)  gi.setPlayerWeaponLabelChangeText(difference);
        return difference;
    }

    public int adjustPlayerGems(int amount) {
        int difference = amount;
        if (playerGems > Math.abs(difference) && amount < 0) {
            difference = -playerGems;
            playerGems = 0;
        } else {
            playerGems += amount;
        }
        if (difference != 0)  gi.setPlayerGemLabelChangeText(difference);
        return difference;
    }

    public int adjustComputerBuilders(int amount) {
        int difference = amount;
        if (computerBuilders > Math.abs(difference) && amount < 0) {
            difference = -computerBuilders;
            computerBuilders = 0;
        } else {
            computerBuilders += amount;
        }
        if (difference != 0)  gi.setComputerBuildersLabelChangeText(difference);
        return difference;
    }

    public int adjustComputerSoldiers(int amount) {
        int difference = amount;
        if (computerSoldiers > Math.abs(difference) && amount < 0) {
            difference = -computerSoldiers;
            computerSoldiers = 0;
        } else {
            computerSoldiers += amount;
        }
        if (difference != 0)  gi.setComputerSoldiersLabelChangeText(difference);
        return difference;
    }

    public int adjustComputerWizards(int amount) {
        int difference = amount;
        if (computerWizards > Math.abs(difference) && amount < 0) {
            difference = -computerWizards;
            computerWizards = 0;
        } else {
            computerWizards += amount;
        }
        if (difference != 0)  gi.setComputerWizardsLabelChangeText(difference);
        return difference;
    }

    public int adjustComputerStones(int amount) {
        int difference = amount;
        if (computerStones > Math.abs(difference) && amount < 0) {
            difference = -computerStones;
            computerStones = 0;
        } else {
            computerStones += amount;
        }
        if (difference != 0)  gi.setComputerStoneLabelChangeText(difference);
        return difference;
    }

    public int adjustComputerWeapons(int amount) {
        int difference = amount;
        if (computerWeapons > Math.abs(difference) && amount < 0) {
            difference = -computerWeapons;
            computerWeapons = 0;
        } else {
            computerWeapons += amount;
        }
        if (difference != 0)  gi.setComputerWeaponLabelChangeText(difference);
        return difference;
    }

    public int adjustComputerGems(int amount) {
        int difference = amount;
        if (computerGems > Math.abs(difference) && amount < 0) {
            difference = -computerGems;
            computerGems = 0;
        } else {
            computerGems += amount;
        }
        if (difference != 0)  gi.setComputerGemLabelChangeText(difference);
        return difference;
    }
}
