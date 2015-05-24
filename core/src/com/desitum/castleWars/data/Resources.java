package com.desitum.castleWars.data;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/19/2015.
 */
public class Resources {

    private static final int BUILDER = 1;
    private static final int SOLDIER = 2;
    private static final int WIZARD = 3;

    private static final int STONES = 4;
    private static final int SUPPLIES = 5;
    private static final int GEMS = 6;

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

    public Resources(){
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

    public void changePlayerResources(int builderAmount, int soldierAmount, int wizardAmount, int stoneAmount, int weaponsAmount, int gemsAmount){
        playerBuilders += builderAmount;
        playerSoldiers += soldierAmount;
        playerWizards += wizardAmount;

        playerStones += stoneAmount;
        playerWeapons += weaponsAmount;
        playerGems += gemsAmount;
    }

    public void changeComputerResources(int builderAmount, int soldierAmount, int wizardAmount, int stoneAmount, int weaponsAmount, int gemsAmount){
        computerBuilders += builderAmount;
        computerSoldiers += soldierAmount;
        computerWizards += wizardAmount;

        computerStones += stoneAmount;
        computerWeapons += weaponsAmount;
        computerGems += gemsAmount;
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
        return difference;
    }
}
