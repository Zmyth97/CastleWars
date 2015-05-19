package com.desitum.castleWars.objects;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/19/2015.
 */
public class Resources {

    private static final int BUILDER = 1;
    private static final int SOLDIER = 2; //Army? Warrior?
    private static final int MAGE = 3; //Wizard?

    private static final int TOOLS = 4; //Bricks? Stones?
    private static final int WEAPONS = 5; //Swords? Spears? Supplies?
    private static final int GEMS = 6;

    private int playerBuilders;
    private int playerSoldiers;
    private int playerMages;
    private int playerTools;
    private int playerWeapons;
    private int playerGems;

    private int computerBuilders;
    private int computerSoldiers;
    private int computerMages;
    private int computerTools;
    private int computerWeapons;
    private int computerGems;

    public Resources(){
        playerBuilders = 2;
        playerSoldiers = 2;
        playerMages = 2;
        playerTools = 8;
        playerWeapons = 8;
        playerGems = 8;

        computerBuilders = 2;
        computerSoldiers = 2;
        computerMages = 2;
        computerTools = 8;
        computerWeapons = 8;
        computerGems = 8;
    }

    public void changePlayerResources(int builderAmount, int soldierAmount, int mageAmount, int toolsAmount, int weaponsAmount, int gemsAmount){
        playerBuilders += builderAmount;
        playerSoldiers += soldierAmount;
        playerMages += mageAmount;

        playerTools += toolsAmount;
        playerWeapons += weaponsAmount;
        playerGems += gemsAmount;
    }

    public void changeComputerResources(int builderAmount, int soldierAmount, int mageAmount, int toolsAmount, int weaponsAmount, int gemsAmount){
        computerBuilders += builderAmount;
        computerSoldiers += soldierAmount;
        computerMages += mageAmount;

        computerTools += toolsAmount;
        computerWeapons += weaponsAmount;
        computerGems += gemsAmount;
    }

    public int getPlayerBuilders() {
        return playerBuilders;
    }

    public int getPlayerSoldiers() {
        return playerSoldiers;
    }

    public int getPlayerMages() {
        return playerMages;
    }

    public int getPlayerTools() {
        return playerTools;
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

    public int getComputerMages() {
        return computerMages;
    }

    public int getComputerTools() {
        return computerTools;
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

    public void setPlayerMages(int playerMages) {
        this.playerMages = playerMages;
    }

    public void setPlayerTools(int playerTools) {
        this.playerTools = playerTools;
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

    public void setComputerMages(int computerMages) {
        this.computerMages = computerMages;
    }

    public void setComputerTools(int computerTools) {
        this.computerTools = computerTools;
    }

    public void setComputerWeapons(int computerWeapons) {
        this.computerWeapons = computerWeapons;
    }

    public void setComputerGems(int computerGems) {
        this.computerGems = computerGems;
    }
}
