package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.MovementAnimator;

import java.util.ArrayList;

/**
 * Created by kody on 4/18/15.
 * can be used by kody and people in [Zack, Kody]
 */
public class PopupMenu extends PopupWidget {

    private ArrayList<PopupWidget> widgets;
    private ArrayList<Animator> incomingAnimators;
    private ArrayList<Animator> outgoingAnimators;
    private ArrayList<Animator> incomingAnimatorsToAdd;
    private ArrayList<Animator> outgoingAnimatorsToAdd;

    private Texture background;

    private int commandToSend;

    /**
     * Create new Popup Menu with a blank canvas
     * use PopupWidgets to create and add them to the PopupMenu
     * PopupWidgets will inherit all Animators from the PopupMenu, and can be overriden
     *
     * @param background background texture for the image
     * @param x          left x position of the PopupMenu
     * @param y          bottom y position of the PopupMenu
     * @param width      the width of the PopupMenu
     * @param height     the height of the PopupMenu
     */
    public PopupMenu(Texture background, float x, float y, float width, float height) {
        super(background, 0, 0, background.getWidth(), background.getHeight());
        widgets = new ArrayList<PopupWidget>();
        incomingAnimators = new ArrayList<Animator>();
        outgoingAnimators = new ArrayList<Animator>();
        incomingAnimatorsToAdd = new ArrayList<Animator>();
        outgoingAnimatorsToAdd = new ArrayList<Animator>();

        this.background = background;

        setPosition(x, y);
        this.setSize(width, height);
    }

    /**
     * Draws the PopupMenu with widgets on the screen
     *
     * @param batch batch to draw with
     */
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        for (PopupWidget widget : widgets) {
            widget.draw(batch);
        }
    }

    /**
     * updates the PopupMenu with touch, done automatically in KodyWorld
     *
     * @param touchPos  - touchPos in relation to the world
     * @param clickDown - whether or not the mouse is down or not
     */
    public void updateTouchInput(Vector3 touchPos, boolean clickDown) {
        for (PopupWidget widget : widgets) { // Go through each PopupWidget in the menu's widgets

            boolean clickInArea = CollisionDetection.pointInRectangle(widget.getBoundingRectangle(), touchPos);
            if (widget instanceof PopupButton) { // if Widget is a PopupButton
                PopupButton button = (PopupButton) widget;
                if (clickInArea && clickDown) {
                    button.onClickDown();
                } else if (clickInArea) {
                    button.onClickUp(true);
                } else {
                    button.onClickUp(false);
                }
            } else if (widget instanceof PopupButtonMaterial) {
                PopupButtonMaterial button = (PopupButtonMaterial) widget;
                if (clickInArea && clickDown) {
                    button.onClickDown(touchPos);
                } else if (clickInArea) {
                    button.onClickUp(true);
                } else {
                    button.onClickUp(false);
                }
            } else if (widget instanceof PopupSlider) { // if widget is a Slider
                PopupSlider slider = (PopupSlider) widget;
                if (clickInArea && clickDown) {
                    slider.onClickDown(touchPos);
                } else if (clickInArea) {
                    slider.onClickUp();
                } else {
                    slider.onClickUp(); // handles if not in area
                }
            } else if (widget instanceof PopupScrollArea) { // if widget is a PopupScrollArea
                PopupScrollArea popupScrollArea = (PopupScrollArea) widget;
                popupScrollArea.updateTouchInput(touchPos, clickDown);
            } else if (widget instanceof PopupImage) {
                PopupImage image = (PopupImage) widget;
                if (clickInArea && clickDown) {
                    image.onClickDown();
                } else if (clickInArea) {
                    image.onClickUp(true);
                } else {
                    image.onClickUp(false);
                }
            } else if (widget instanceof PopupToggleButton) {
                PopupToggleButton button = (PopupToggleButton) widget;
                if (clickInArea && clickDown) {
                    button.onClickDown();
                } else if (clickInArea) {
                    button.onClickUp(true);
                } else {
                    button.onClickUp(false);
                }
            }
        }
    }

    /**
     * Updates information from the scroll wheel
     * all handled in KodyWorld
     *
     * @param amount     gives either 0, 1, or -1
     * @param mousePos   pos of the cursor
     * @param posMatters if your mouse position matters
     */
    public void udpateScrollInput(int amount, Vector3 mousePos, boolean posMatters) {
        for (PopupWidget widget : widgets) {
            if (!widget.getClass().equals(PopupScrollArea.class)) {
                continue;
            }
            if (posMatters) {
                if (CollisionDetection.pointInRectangle(widget.getBoundingRectangle(), mousePos)) {
                    PopupScrollArea scrollArea = (PopupScrollArea) widget;
                    scrollArea.updateScrollInput(amount);
                }
            } else {
                PopupScrollArea scrollArea = (PopupScrollArea) widget;
                scrollArea.updateScrollInput(amount);
            }
        }
    }

    /**
     * update animation and widgets and their animations associated with the PopupMenu
     *
     * @param delta - time since last frame
     */
    public void update(float delta) {
        for (PopupWidget widget : widgets) {
            widget.update(delta);
        }

        updateAnimation(delta);
    }

    /**
     * updates animation for the PopupMenu
     *
     * @param delta - time since last frame
     */
    private void updateAnimation(float delta) {
        for (Animator anim : incomingAnimators) {
            anim.update(delta);
        }

        for (Animator anim : outgoingAnimators) {
            anim.update(delta);
        }
    }

    /**
     * adds an animator that should be run when called
     *
     * @param anim - animator to add
     */
    public void addIncomingAnimator(Animator anim) {
        incomingAnimators.add(anim);
        incomingAnimatorsToAdd.add(anim);
    }

    /**
     * adds an animator that should be run when called
     *
     * @param anim - animator to add
     */
    public void addOutgoingAnimator(Animator anim) {
        outgoingAnimators.add(anim);
        outgoingAnimatorsToAdd.add(anim);
    }

    /**
     * add widget to the PopupMenu
     * widget location is in relation to PopupMenu
     *
     * @param toAdd
     */
    public void addPopupWidget(PopupWidget toAdd) {
        for (Animator anim : incomingAnimatorsToAdd) {
            Animator dupAnim = anim.duplicate();
            if (dupAnim.getClass().equals(MovementAnimator.class)) {
                MovementAnimator dupMov = (MovementAnimator) dupAnim;
                if (dupMov.isControllingX()) {
                    dupMov.setStartPos(toAdd.getX() + dupMov.getStartPos());
                    dupMov.setEndPos(toAdd.getX() + dupMov.getEndPos());
                }
                if (dupMov.isControllingY()) {
                    dupMov.setStartPos(toAdd.getY() + dupMov.getStartPos());
                    dupMov.setEndPos(toAdd.getY() + dupMov.getEndPos());
                }
                toAdd.addIncomingAnimator(dupMov);
            }
        }
        for (Animator anim : outgoingAnimatorsToAdd) {
            Animator dupAnim = anim.duplicate();
            if (dupAnim.getClass().equals(MovementAnimator.class)) {
                MovementAnimator dupMov = (MovementAnimator) dupAnim;
                if (dupMov.isControllingX()) {
                    dupMov.setStartPos(toAdd.getX() + dupMov.getStartPos());
                    dupMov.setEndPos(toAdd.getX() + dupMov.getEndPos());
                }
                if (dupMov.isControllingY()) {
                    dupMov.setStartPos(toAdd.getY() + dupMov.getStartPos());
                    dupMov.setEndPos(toAdd.getY() + dupMov.getEndPos());
                }
                toAdd.addOutgoingAnimator(dupMov);
            }
        }

        toAdd.setX(getX() + toAdd.getX());
        toAdd.setY(getY() + toAdd.getY());
        widgets.add(toAdd);
    }

    /**
     * start animators added to incomingAnimators
     */
    @Override
    public void startIncomingAnimators() {
        for (PopupWidget widget : widgets) {
            widget.startIncomingAnimators();
        }
        for (Animator anim : incomingAnimators) {
            anim.start(false);
        }
    }

    /**
     * start animators added to outgoingAnimators
     */
    @Override
    public void startOutgoingAnimators() {
        for (PopupWidget widget : widgets) {
            widget.startOutgoingAnimators();
        }
        for (Animator anim : outgoingAnimators) {
            anim.start(false);
        }
    }
}

