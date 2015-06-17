package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.ColorEffects;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.animation.ScaleAnimator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kody on 4/18/15.
 * can be used by kody and people in [Zack, Kody]
 */
public class PopupMenu extends PopupWidget {

    private ArrayList<PopupWidget> widgets;

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
        incomingAnimatorsToAdd = new ArrayList<Animator>();
        outgoingAnimatorsToAdd = new ArrayList<Animator>();

        this.background = background;

        setPosition(x, y);
        this.setSize(width, height);

        disable();
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
     * @param amount   gives either 0, 1, or -1
     * @param mousePos pos of the cursor
     */
    @Override
    public void updateScroll(int amount, Vector3 mousePos) {
        for (PopupWidget widget : widgets) {
            if (!widget.getClass().equals(PopupScrollArea.class)) {
                continue;
            }
            if (widget.scrollPosMatters() && isEnabled()) {
                if (CollisionDetection.pointInRectangle(widget.getBoundingRectangle(), mousePos)) {
                    PopupScrollArea scrollArea = (PopupScrollArea) widget;
                    scrollArea.updateScrollInput(amount);
                }
            } else if (isEnabled()) {
                PopupScrollArea scrollArea = (PopupScrollArea) widget;
                scrollArea.updateScrollInput(amount);
            }
        }
    }

    @Override
    boolean scrollPosMatters() {
        return true;
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
        super.update(delta);
    }

    /**
     * adds an animator that should be run when called
     *
     * @param anim - animator to add
     */
    public void addIncomingAnimator(Animator anim) {
        super.addIncomingAnimator(anim);
        incomingAnimatorsToAdd.add(anim);
    }

    /**
     * adds an animator that should be run when called
     *
     * @param anim - animator to add
     */
    public void addOutgoingAnimator(Animator anim) {
        super.addOutgoingAnimator(anim);
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
            if (dupAnim instanceof MovementAnimator) {
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
            } else if (dupAnim instanceof ColorEffects) {
                System.out.println(((ColorEffects) dupAnim).getCurrentColor().a);
                dupAnim.start(false);
                dupAnim.update(0.5f);
                System.out.println(((ColorEffects) dupAnim).getCurrentColor().a);
                dupAnim.setSprite(toAdd, false, false);
                toAdd.addIncomingAnimator(dupAnim);
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
            } else if (dupAnim instanceof ColorEffects) {
                dupAnim.setSprite(toAdd, false, false);
                toAdd.addOutgoingAnimator(dupAnim);
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
    public void moveIn() {
        super.moveIn();
        for (PopupWidget widget : widgets) {
            widget.moveIn();
        }
    }

    /**
     * start animators added to outgoingAnimators
     */
    @Override
    public void moveOut() {
        super.moveOut();
        for (PopupWidget widget : widgets) {
            widget.moveOut();
        }
    }

    @Override
    public void clearColorEffects() {
        super.clearColorEffects();
        Iterator<Animator> iter = incomingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ColorEffects) {
                iter.remove();
            }
        }
        iter = outgoingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ColorEffects) {
                iter.remove();
            }
        }
    }

    @Override
    public void clearMovementAnimators() {
        super.clearColorEffects();
        Iterator<Animator> iter = incomingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof MovementAnimator) {
                iter.remove();
            }
        }
        iter = outgoingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof MovementAnimator) {
                iter.remove();
            }
        }
    }

    @Override
    public void clearScaleAnimators() {
        super.clearColorEffects();
        Iterator<Animator> iter = incomingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ScaleAnimator) {
                iter.remove();
            }
        }
        iter = outgoingAnimatorsToAdd.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ScaleAnimator) {
                iter.remove();
            }
        }
    }

    protected ArrayList<Animator> getIncomingAnimatorsToAdd() {
        return incomingAnimatorsToAdd;
    }

    protected ArrayList<Animator> getOutgoingAnimatorsToAdd() {
        return outgoingAnimatorsToAdd;
    }

    @Override
    public void setX(float x) {
        if (widgets != null) {
            for (PopupWidget widget : widgets) {
                widget.setX(widget.getX() - getX() + x);
            }
        }
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        if (widgets != null) {
            for (PopupWidget widget : widgets) {
                widget.setY(widget.getY() - getY() + y);
            }
        }
        super.setY(y);
    }

    public ArrayList<PopupWidget> getChildren() {
        return widgets;
    }

    public void setWidgets(ArrayList<PopupWidget> widgetsToSet) {
        this.widgets = new ArrayList<PopupWidget>();
        for (PopupWidget widget : widgetsToSet) {
            this.addPopupWidget(widget);
        }
    }
}

