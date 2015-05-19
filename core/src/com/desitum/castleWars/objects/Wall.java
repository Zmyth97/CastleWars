package com.desitum.castleWars.objects;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Wall {
    private float health; //Health = height in this game haha

    public Wall(){
        health = 30;
    }

    public void doDamage(float damage){
        health -= damage;
        if(health <= 0){
            health = 0;
        }
    }

    public void repair(float amount){
        health += amount;
        if(health >= 100){
            health = 100;
        }
    }
}
