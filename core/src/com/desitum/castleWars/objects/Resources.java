package com.desitum.castleWars.objects;

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
        playerStones = 8;
        playerWeapons = 8;
        playerGems = 8;

        computerBuilders = 2;
        computerSoldiers = 2;
        computerWizards = 2;
        computerStones = 8;
        computerWeapons = 8;
        computerGems = 8;
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

    public void setPlayerBuilders(int playerBuilders) {
        this.playerBuilders = playerBuilders;
    }

    public void setPlayerSoldiers(int playerSoldiers) {
        this.playerSoldiers = playerSoldiers;
    }

    public void setPlayerWizards(int playerWizards) {
        this.playerWizards = playerWizards;
    }

    public void setPlayerStones(int playerStones) {
        this.playerStones = playerStones;
    }

    public void setPlayerWeapons(int playerWeapons) {
        this.playerWeapons = playerWeapons;
    }

    public void setPlayerGems(int playerGems) {
        this.playerGems = playerGems;
    }

    public void setComputerBuilders(int computerBuilders) {
        this.computerBuilders = computerBuilders;
    }

    public void setComputerSoldiers(int computerSoldiers) {
        this.computerSoldiers = computerSoldiers;
    }

    public void setComputerWizards(int computerWizards) {
        this.computerWizards = computerWizards;
    }

    public void setComputerStones(int computerStones) {
        this.computerStones = computerStones;
    }

    public void setComputerWeapons(int computerWeapons) {
        this.computerWeapons = computerWeapons;
    }

    public void setComputerGems(int computerGems) {
        this.computerGems = computerGems;
    }
}
