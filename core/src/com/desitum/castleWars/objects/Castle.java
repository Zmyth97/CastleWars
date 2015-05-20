package com.desitum.castleWars.objects;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Castle {
    private float health; //Health = height in this game haha

    public Castle(){
        health = 30;
    }

    public void doDamage(float damage){
        health -= damage;
        if(health <= 0){
            //End game!
        }
    }

    public void repair(float amount){
        health += amount;
        if(health >= 100){
            //End Game!
        }
    }
}
