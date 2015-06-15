package com.desitum.castleWars.libraries.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.castleWars.libraries.animation.Animator;

import java.util.ArrayList;

/**
 * Created by kody on 4/7/15.
 * can be used by kody and people in []
 */
public class Particle extends Sprite {

    private ArrayList<Animator> animators;

    private float lifetime;

    private ParticleEmitter particleEmitter;

    public Particle(Texture texture, ParticleEmitter pe, ParticleSettings ps) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        this.setSize(ps.getHeight(), ps.getWidth());
        this.setPosition(ps.getXAnimator().getAmount(), ps.getYAnimator().getAmount());

        this.setOriginCenter();

        this.animators = ps.getAnimators();

        for (Animator animator : animators) {
            animator.setSprite(this, animator.updateX(), animator.updateY());
        }

        this.particleEmitter = pe;

        startAllAnimators();

        this.lifetime = ps.getDuration();
    }


    public void update(float delta) {
        for (Animator anim : animators) {
            anim.update(delta);
        }

        lifetime -= delta;

        if (lifetime < 0) {
            particleEmitter.remove(this);
        }
    }

    public float getLifetime() {
        return lifetime;
    }

    public void startAllAnimators() {
        for (Animator anim : animators) {
            anim.start(false);
        }
    }
}
